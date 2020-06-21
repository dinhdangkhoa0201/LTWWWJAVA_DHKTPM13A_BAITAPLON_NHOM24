package fit.se.main.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("fit.se.main")
public class ApplicationContextConfig implements WebMvcConfigurer{
	
	public InternalResourceViewResolver getViewResource() {
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/templates");
		view.setSuffix(".html");
		return view;
	}
}
