package com.demo.web;

import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ekaterina on 24.04.2017.
 */

@Controller
public class IndexController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String getIndexPage(Model model) {
        LOGGER.debug("Getting home page");
        return "index";
    }

}
