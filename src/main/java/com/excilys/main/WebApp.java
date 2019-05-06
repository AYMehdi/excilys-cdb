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
		System.out.println("TEST");
		context.register(ConfigSpringMvc.class);
		System.out.println(2);
		context.register(ConfigApp.class);
		System.out.println(3);
		context.setServletContext(servletContext);
		System.out.println(4);
		context.refresh();
		System.out.println(5);
		servletContext.addListener(new ContextLoaderListener(context));
		System.out.println(6);
		DispatcherServlet servlet = new DispatcherServlet(context);
		System.out.println(7);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
		System.out.println(8);
		registration.setLoadOnStartup(1);
		System.out.println(9);
		registration.addMapping("/");
		System.out.println(10);
	}
}