package com.example.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySources({
	@PropertySource("classpath:properties/default.properties"),
	@PropertySource("classpath:properties/messages_${spring.profiles.active:na}.properties")
})
@Component
public class Demo3Configuration {
	@Value("${name:NA}")
	private String name;
	
	@Autowired
	private Environment env;
	
	public String getName() {
		return name;
	}
	
	public String getName2() {
		return env.getProperty("name");
	}
}
