package com.example.demo.common;

import org.springframework.ui.ModelMap;

public class RedirectViewUtil {

    public static String redirectWithMessage(ModelMap modelMap, String url, String message) {
        modelMap.addAttribute("url", url);
        modelMap.addAttribute("message", message);

        return "/common/redirectWithMessage";
    }

    public static String backWithMessage(ModelMap modelmap, String message) {
        modelmap.addAttribute("message", message);

        return "/common/backWithMessage";
    }
}
