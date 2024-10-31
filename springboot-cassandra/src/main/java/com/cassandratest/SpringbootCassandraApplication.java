package com.cassandratest;

import com.cassandratest.entity.Book;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.ToString.Exclude;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
// facing data source url attribute not specified hence disable it using exclude prop
@EnableScheduling
public class SpringbootCassandraApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootCassandraApplication.class, args);
	}

	@Autowired
	CassandraTemplate cassandraTemplate;
	@Scheduled(cron = "*/5 * * * * *")
	public void scheduleTaskUsingCronExpression() {

		long now = System.currentTimeMillis() / 1000;
		CassandraBatchOperations ops = cassandraTemplate.batchOps();
		Book book1 = new Book(UUID.randomUUID(),"rakesh","java","java basic");
		Book book2 = new Book(UUID.randomUUID(),"suresh","java","java basic");
		Book book3 = new Book(UUID.randomUUID(),"sangam","java","java basic");
		Book book4 = new Book(UUID.randomUUID(),"manohar","java","java basic");
		List<Book> books = Arrays.asList(book1,book2,book3,book4);
		ops.insert(books);
		ops.execute();

		System.out.println("Persited book successfully");
	}


}
