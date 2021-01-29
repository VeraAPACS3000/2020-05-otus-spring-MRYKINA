package ru.otus.spring.homework15;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.homework15.domain.OrderInternetShop;
import ru.otus.spring.homework15.services.OrderProcess;
import ru.otus.spring.homework15.services.OrderServices;

import java.util.List;

@SpringBootApplication
public class App {

    //входной канал
    @Bean
    public QueueChannel inChannel() {
        return MessageChannels.queue(5).get();
    }

    //выходной канал
    @Bean
    public QueueChannel outChannel() {
        return MessageChannels.queue(5).get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow orderFlow() {
        return IntegrationFlows.from("inChannel")
                .split()
                .<OrderInternetShop, String>route(OrderInternetShop::getCountryProduct,
                        mapping -> mapping
                                .subFlowMapping(
                                        "Canada", sf -> sf
                                                .channel(c -> c.queue())
                                                .handle("orderServices", "addCommission")
                                                .handle("orderServices", "registrationOrder")
                                )
                                .subFlowMapping(
                                        "Russia", sf -> sf
                                                .channel(c -> c.queue())
                                                .handle("orderServices", "registrationOrder")

                                )
                )
                .handle("orderServices","readyToSend")
                .aggregate()
                .channel("outChannel")
                .get();
    }

    public static void main(String[] args) {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(App.class);
        OrderProcess orderProcess = context.getBean(OrderProcess.class);
        List<OrderInternetShop> newOrderShop = OrderServices.generateOrder();
        List<OrderInternetShop> readyOrder = orderProcess.process(newOrderShop);
        System.out.println("Ready order" + readyOrder.toString());
        context.close();
    }


}
