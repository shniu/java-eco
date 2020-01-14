package io.github.shniu.mvc;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.HttpServletBean;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class TestMVC {

    public static void main(String[] args) {
        DispatcherServlet servlet;
        HttpServletBean bean;
        FrameworkServlet frameworkServlet;
        HashMap map;
        Object o;
        Collections.synchronizedMap(null);
        CopyOnWriteArrayList copyOnWriteArrayList;
        ConcurrentHashMap concurrentHashMap;
    }
}
