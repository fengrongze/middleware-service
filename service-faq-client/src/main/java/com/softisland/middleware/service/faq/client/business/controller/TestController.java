package com.softisland.middleware.service.faq.client.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by fengrongze on 2017/6/28.
 */
@Controller
public class TestController {


    @RequestMapping("/")
    public String first (Map<String,Object> map){

        map.put("name", "[Angel -- 守护天使]");

        return "hello";

    }


    @RequestMapping(value="/{id}")
    public String other (Map<String,Object> map,@PathVariable("id") String id){

        map.put("name", "[Angel -- 守护天使-other]"+id);

        return "hello";

    }



    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){

        map.put("name", "[Angel -- 守护天使]");

        return "hello";

    }







    @RequestMapping("/img")
    public String info(Map<String,Object> map){

        map.put("name", "[Angel -- 守护天使]");

        map.put("gender",1);//gender:性别，1：男；0：女；



        List<Map<String,Object>> friends =new ArrayList<Map<String,Object>>();

        Map<String,Object> friend = new HashMap<String,Object>();

        friend.put("name", "张三");

        friend.put("age", 20);

        friends.add(friend);

        friend = new HashMap<String,Object>();

        friend.put("name", "李四");

        friend.put("age", 22);

        friends.add(friend);

        map.put("friends", friends);
        return "info";

    }

    @RequestMapping("/complex")
    public String complex(Map<String,Object> map){

        map.put("name", "[Angel -- 守护天使]");

        map.put("gender",1);//gender:性别，1：男；0：女；



        List<Map<String,Object>> friends =new ArrayList<Map<String,Object>>();

        Map<String,Object> friend = new HashMap<String,Object>();

        friend.put("name", "张三");

        friend.put("age", 20);

        friends.add(friend);

        friend = new HashMap<String,Object>();

        friend.put("name", "李四");

        friend.put("age", 22);

        friends.add(friend);

        map.put("friends", friends);
        return "complex";

    }

}
