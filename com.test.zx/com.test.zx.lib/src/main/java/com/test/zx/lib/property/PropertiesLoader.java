package com.test.zx.lib.property;

import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesLoader extends PropertyPlaceholderConfigurer {
	
	private Properties prop = null;

	public Properties getProperties() {
		return this.prop;
	}

	public String get(String key) {
		return getProperties().getProperty(key);
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		this.prop = props;
		super.processProperties(beanFactoryToProcess, props);
	}

	
}