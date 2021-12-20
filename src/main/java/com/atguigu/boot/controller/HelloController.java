package com.atguigu.boot.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String index(){
        return "hello,this is myfriend";
    }
    @GetMapping("/user")
    public String userGet(){
        return "get 方式提交 ";
    }

    @PostMapping("/user")
    public String userPut(){

        return "post 方式提交";
    }

    @DeleteMapping("/user")
    public String deletey(){
        return "delete 方式提交";
    }

     @PutMapping("/user")
    public String postUser(){
        return "put 方式提交";
    }

    @RequestMapping("/car/{id}/owner/{username}")
    public Map car(@PathVariable("id")String id,@PathVariable("username")String username,
                   @PathVariable Map<String,String>mp,
                   @RequestHeader("User-Agent")String header){
        Map<String, Object >map=new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("mp", mp);
        map.put("User-Agent", header);

        return map;
    }
  // /boss/1;age=20/2;age=25
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(name = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map=new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }
    // /car/sell;low=35;brand=auti,byd,bchi
    @GetMapping("/car/{suibianxie}")
    public Map car(@MatrixVariable("low") Integer low,
                   @MatrixVariable("brand") List<String> brand,
                   @PathVariable("suibianxie") String path){
        Map<String,Object> map=new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }


}
