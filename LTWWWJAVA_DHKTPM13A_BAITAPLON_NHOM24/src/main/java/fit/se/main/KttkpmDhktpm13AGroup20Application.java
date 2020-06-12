package fit.se.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KttkpmDhktpm13AGroup20Application {
	
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(KttkpmDhktpm13AGroup20Application.class, args);
	}
	
}
