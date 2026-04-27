package com.exemplo.atividade1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequisicaoController {

    @GetMapping("/requisicao")
    public String requisicao(HttpServletRequest request, Model model) {
        model.addAttribute("metodo", request.getMethod());
        model.addAttribute("uri", request.getRequestURI());
        model.addAttribute("protocolo", request.getProtocol());
        return "requisicao";
    }

}
