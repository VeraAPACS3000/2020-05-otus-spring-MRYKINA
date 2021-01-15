package ru.otus.spring.homework15.services;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

@Service
public class StartBatchServices {

    private final JobOperator jobOperator;
    public static final String IMPORT_USER_JOB_NAME = "importUserJob";

    public StartBatchServices(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(IMPORT_USER_JOB_NAME,"");
        System.out.println(jobOperator.getSummary(executionId));
    }

}
