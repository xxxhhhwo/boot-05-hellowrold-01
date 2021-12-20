package com.atguigu.boot.controller;

import com.atguigu.boot.bean.Person;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Date;

@Controller
public class ResponseController {


    @ResponseBody
    @RequestMapping("/file")
    public FileSystemResource resource(){
        String url="D:\\javaStudy\\hello.txt";
        File file=new File(url);
        FileSystemResource fileSystemResource=new FileSystemResource(file);
        return fileSystemResource;
    }


    @ResponseBody
    @RequestMapping("/test/person")
    public Person person(){
        Person person=new Person();
        person.setName("sss");
        person.setAge(18);
        person.setDate(new Date());
        return person;
    }

}
