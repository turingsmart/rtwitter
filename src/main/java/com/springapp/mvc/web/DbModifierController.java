package com.springapp.mvc.web;

import com.springapp.mvc.service.DbRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Path;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 25/7/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/")
public class DbModifierController {
    private final DbRepositoryService dbRepositoryService;

    @Autowired
    public DbModifierController(DbRepositoryService dbRepositoryService) {
        this.dbRepositoryService = dbRepositoryService;
    }

}
