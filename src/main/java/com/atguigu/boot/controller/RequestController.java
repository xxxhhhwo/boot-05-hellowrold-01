package com.atguigu.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @RequestMapping("/goto")
    public String gotoPage( HttpServletRequest  request){
        request.setAttribute("msg", "成功了..");
        return "forward:/success";
    }
    @ResponseBody
    @RequestMapping("/success")
    public Map success(HttpServletRequest  request
                       ){
        String msg = (String) request.getAttribute("msg");

        Map<String,Object>map=new HashMap<>();
       // map.put("Anno_msg", message);
        map.put("request--", msg);

        return  map;
    }

    @ResponseBody
    @RequestMapping("/ttt")
    public String tt(){
        return "tt";
    }



}
