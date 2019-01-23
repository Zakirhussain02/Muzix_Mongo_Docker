/*
main class for run
 */

package com.stackroute;

import com.stackroute.domain.Muzix;
import com.stackroute.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication


//path source of Applicaton context

@PropertySource("classpath:object.properties")
public class UserServiceApplication implements /*ApplicationListener<ContextRefreshedEvent>,*/ CommandLineRunner {

	@Autowired
	MuzixRepository muzixRepository;

	@Value("${trackId}")
	private int trackId;

	@Value("${trackName}")
	private String trackName;

	@Value("${comment}")
	private String comment;



	public static void main(String[] args)
	{
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		muzixRepository.save(new Muzix(trackId,trackName,comment));
	}
}



















//ApplicationListener
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
//		muzixRepository.save(new Muzix(trackId,trackName,comment));
//	}
