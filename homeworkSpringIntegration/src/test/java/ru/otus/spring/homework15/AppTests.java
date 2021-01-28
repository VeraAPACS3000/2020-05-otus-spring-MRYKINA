package ru.otus.spring.homework15;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import ru.otus.spring.homework15.domain.OrderInternetShop;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest
@Description("Test class App")
class AppTests {

	@Autowired
	private QueueChannel inChannel;

	@Autowired
	public QueueChannel outChannel;

	@Test
	@Description("Test message not null")
	void sendMessagesToFlowNotNull() {
		OrderInternetShop order =
				new OrderInternetShop(
						"test",
						1,
						"Russia",
						10.00,
						"Ready to send");

		Message<OrderInternetShop> message =
				MessageBuilder.withPayload(order).setReplyChannel(outChannel).build();

		inChannel.send(message);
		Message<OrderInternetShop> receive = (Message<OrderInternetShop>) outChannel.receive();
		System.out.println("receive:" + receive.getPayload());
		assertNotNull(receive);
	}

	@Test
	@Description("Test prices add commission")
	void checkPrices() {
		OrderInternetShop order =
				new OrderInternetShop(
						"test",
						1,
						"Canada",
						10.00,
						"Ready to send");

		Message<OrderInternetShop> message =
				MessageBuilder.withPayload(order).setReplyChannel(outChannel).build();

		inChannel.send(message);
		Message<OrderInternetShop> receive = (Message<OrderInternetShop>) outChannel.receive();
		System.out.println("receive:" + receive.getPayload());
		List<OrderInternetShop> listOrderFromReceive = (List<OrderInternetShop>) receive.getPayload();
		double actualPrice = order.getPrice()+70.00d;
		double priceReceive = listOrderFromReceive.get(0).getPrice();
		assertEquals(priceReceive, actualPrice, 0);
	}

	@Test
	@Description("Test prices not add commission")
	void checkPricesRussia() {
		OrderInternetShop order =
				new OrderInternetShop(
						"test",
						1,
						"Russia",
						10.00,
						"Ready to send");

		Message<OrderInternetShop> message =
				MessageBuilder.withPayload(order).setReplyChannel(outChannel).build();

		inChannel.send(message);
		Message<OrderInternetShop> receive = (Message<OrderInternetShop>) outChannel.receive();
		System.out.println("receive:" + receive.getPayload());
		List<OrderInternetShop> listOrderFromReceive = (List<OrderInternetShop>) receive.getPayload();
		double actualPrice = order.getPrice();
		double priceReceive = listOrderFromReceive.get(0).getPrice();
		assertEquals(priceReceive, actualPrice, 0);
	}

	@Test
	@Description("Test message equals values")
	void sendMessagesToFlowEqualsGivenValue() {
		OrderInternetShop order =
				new OrderInternetShop(
						"test",
						1,
						"Canada",
						10.00,
						"Ready to send");

		Message<OrderInternetShop> message =
				MessageBuilder.withPayload(order).setReplyChannel(outChannel).build();

		inChannel.send(message);
		Message<OrderInternetShop> receive = (Message<OrderInternetShop>) outChannel.receive();
		System.out.println("receive:" + receive.getPayload());
		List<OrderInternetShop> listOrderFromReceive = (List<OrderInternetShop>) receive.getPayload();
		assertEquals(listOrderFromReceive.get(0).getCountryProduct(),
				order.getCountryProduct());
		assertEquals(listOrderFromReceive.get(0).getProduct(),
				order.getProduct());
		assertEquals(listOrderFromReceive.get(0).getStatus(),
				order.getStatus());
		assertEquals(listOrderFromReceive.get(0).getNumberOrder(),
				order.getNumberOrder());
	}

}
