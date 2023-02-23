package com.newcoder.community.controller;

import com.newcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        //不写responseBody会默认认为返回的是个网页而不是字符串
        return "hello spring boot";
    }
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }
    //解释如何获得请求对象和响应数据
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //得到所有请求行的name
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":"+value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        //获取输出流
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求 /students?current=1&limit=20
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current",required = false,defaultValue = "1") int current,
                              @RequestParam(name = "limit",required = false,defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    @RequestMapping(path = "student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age",30);
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){//把model放入参数里，然后将视图直接返回
        model.addAttribute("name","清华大学");
        model.addAttribute("age",100);
        return "demo/view";
    }

    //响应JSON数据，异步请求，就是当前页面不动，但是对服务器进行访问，返回了一些信息

    //java对象 --> JSON字符串 ---> JS对象
    //响应一个
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }
    //响应多个
    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);

        Map<String,Object> emp1 = new HashMap<>();
        emp1.put("name","张三1");
        emp1.put("age",231);
        emp1.put("salary",8000.001);
        list.add(emp1);

        Map<String,Object> emp2 = new HashMap<>();
        emp2.put("name","张三2");
        emp2.put("age",232);
        emp2.put("salary",8000.002);
        list.add(emp2);

        return list;
    }

}
