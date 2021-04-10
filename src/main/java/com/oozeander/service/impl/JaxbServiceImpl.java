package com.oozeander.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;

import com.oozeander.service.JaxbService;

@Service("jaxbService")
public class JaxbServiceImpl<T> implements JaxbService<T> {
	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Override
	/**
	 * marshal to file
	 */
	public void marshal(T t, String location) throws XmlMappingException, IOException {

		jaxb2Marshaller.marshal(t, new StreamResult(new FileWriter(location)));

		//아래 DOM Parser 이용 xml 만들기에서도 StreamResult를 사용해 소스(여기선 t)를 transform(여기선 marshal)함
		//https://blog.naver.com/lmh06132/221276328823

		//결국엔 https://docs.oracle.com/javase/6/docs/api/    StreamResult란 XML, Text, HTML.. 등으로 변환을 위한 홀더 역할을 합니다.
		//마샬 매서드가 Writer 보다 이걸 싼 Result가 필요해 넘겨 준거다라고 넘어갑시다.
 	}

	@Override
	/**
	 * marshal to String
	 */
	public String marshal(T t) {
		StringWriter sw = new StringWriter();
		jaxb2Marshaller.marshal(t, new StreamResult(sw));
		return sw.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T unmarshal(String location) {
		// XML 파일을 읽고 (Acts as an holder)스트림소스에 넣어 언마샬링 매서드에 전달하네   *Acts as an holder를 컵 홀더 정도로 이해하자
		return (T) jaxb2Marshaller.unmarshal(new StreamSource(new File(location)));
	}
}