package com.exemplo.atividade1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaudacaoController {

    @GetMapping("/saudacao")
    public String saudacao(@RequestParam(value = "nome", required = false) String nome, Model model) {
        if (nome == null || nome.isBlank()) {
            return "redirect:https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        }

        model.addAttribute("nome", nome);
        return "saudacao";
    }

}
