package com.springapp.mvc.service;


import com.springapp.mvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 5/8/13
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class apiInterceptor implements HandlerInterceptor {

    private final DbRepositoryService dbRepositoryService;

    @Autowired
    public apiInterceptor(DbRepositoryService dbRepositoryService) {
        this.dbRepositoryService = dbRepositoryService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Inside pre handle");
        System.out.println(request.getHeader("accessToken"));
        Users u = dbRepositoryService.fetchUserByAccessToken(request.getHeader("accessToken"));
        if(u == null)
            return false;
        System.out.println("User returned");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("Inside post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {
        System.out.println("Inside after completion");
    }
}
