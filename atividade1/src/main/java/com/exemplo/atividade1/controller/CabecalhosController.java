package com.exemplo.atividade1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CabecalhosController {

    @GetMapping("/cabecalhos")
    public String cabecalhos(HttpServletRequest request, Model model) {
        model.addAttribute("host", request.getHeader("host"));
        model.addAttribute("userAgent", request.getHeader("user-agent"));
        model.addAttribute("acceptEncoding", request.getHeader("accept-encoding"));
        model.addAttribute("acceptLanguage", request.getHeader("accept-language"));
        return "cabecalhos";
    }

}
