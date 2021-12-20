package com.atguigu.boot.convertor;


import com.atguigu.boot.bean.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class MyAppConvertor  implements HttpMessageConverter<Person>{
    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {

        return MediaType.parseMediaTypes("application/x-ssq");
    }

    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
            String msg=person.getName()+";"+person.getAge()+";"+person.getDate();
        OutputStream body = outputMessage.getBody();
        body.write(msg.getBytes());

    }

    @Override
    public Person read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
}
