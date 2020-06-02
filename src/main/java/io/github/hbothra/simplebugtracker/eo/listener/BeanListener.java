package io.github.hbothra.simplebugtracker.eo.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import lombok.Setter;

@Service
public class BeanListener implements ApplicationContextAware {

	@Setter
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		setContext(applicationContext);
	}

	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}

}
