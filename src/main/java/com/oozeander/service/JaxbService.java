package com.oozeander.service;

import java.io.IOException;

import org.springframework.oxm.XmlMappingException;

public interface JaxbService<T> {

	/**
	 * pojo -> xml file
	 * @param t
	 * @param location
	 * @throws XmlMappingException
	 * @throws IOException
	 */
	public void marshal(T t, String location) throws XmlMappingException, IOException;

	/**
	 * pojo -> xml String
	 * @param t
	 * @return
	 */
	public String marshal(T t);

	/**
	 * xml -> java
	 * @param location
	 * @return
	 */
	public T unmarshal(String location);
}