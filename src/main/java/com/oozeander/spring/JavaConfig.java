package com.oozeander.spring;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@ComponentScan(basePackages = {"com.oozeander.service"})
public class JavaConfig {
	@Bean Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.oozeander.model");

		Map<String, Object> maps = new HashMap<>();
		maps.put(Marshaller.JAXB_ENCODING, "UTF-8");
		maps.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.setMarshallerProperties(maps);
		return marshaller;
	}
}