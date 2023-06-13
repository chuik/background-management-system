package com.hzlx.controller;

import com.hzlx.annotation.*;
import com.hzlx.service.EasyCaptchaService;
import com.hzlx.service.impl.EasyCaptServicechaImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/captcha")
public class EasyCaptchaController {
    private EasyCaptchaService easyCaptchaService = new EasyCaptServicechaImpl();
    @RequestMapping("/code")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        easyCaptchaService.captcha(request, response);
    }

    @RequestMapping("/check")
    @ResponseBody
    public String check(HttpServletRequest request,HttpServletResponse response) {
        return easyCaptchaService.check(request);
    }
}
