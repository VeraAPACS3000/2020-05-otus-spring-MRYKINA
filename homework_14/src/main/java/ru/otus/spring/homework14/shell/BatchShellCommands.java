package ru.otus.spring.homework14.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static ru.otus.spring.homework14.JobConfiguration.IMPORT_DB;


@ShellComponent
public class BatchShellCommands {
    private final Job importUserJob;
    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;

    public BatchShellCommands(Job importUserJob, JobLauncher jobLauncher, JobOperator jobOperator) {
        this.importUserJob = importUserJob;
        this.jobLauncher = jobLauncher;
        this.jobOperator = jobOperator;
    }

    @ShellMethod(value = "startMigrationJobWithJobOperator", key = "sm-jo")
    public void startMigrationJobWithJobOperator() throws Exception {
        Long executionId = jobOperator.start(IMPORT_DB, "");
        System.out.println(jobOperator.getSummary(executionId));
    }

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "sm-jl")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution =
                jobLauncher.run(importUserJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }
}
