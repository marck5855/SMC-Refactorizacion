package mx.com.tp.smc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"mx.com.tp.smc"})
public class TpSmcBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpSmcBackApplication.class, args);
	}
}