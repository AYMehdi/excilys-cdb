package com.excilys.main;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebApp implements WebApplicationInitializer {
	public static AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		context.register(ConfigSpringMvc.class);
		context.register(ConfigApp.class);
		context.setServletContext(servletContext);
		context.refresh();
		servletContext.addListener(new ContextLoaderListener(context));
		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
	}
}