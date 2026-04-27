package com.exemplo.atividade3.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exemplo.atividade3.models.Status;
import com.exemplo.atividade3.models.Tarefa;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private Long proximoId = 4L;

    public TarefaController() {
        Tarefa t1 = new Tarefa(1L, "Tarefa1", "Desc T1", LocalDate.now());
        Tarefa t2 = new Tarefa(2L, "Tarefa2", "Desc T2", LocalDate.now());
        Tarefa t3 = new Tarefa(3L, "Tarefa3", "Desc T3", LocalDate.now());
        listaDeTarefas.add(t1);
        listaDeTarefas.add(t2);
        listaDeTarefas.add(t3);
    }

    @GetMapping({"", "/", "/listar"})
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas", listaDeTarefas);
        return "tarefa-lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrarTarefa() {
        return "tarefa-cadastro";
    }

    @PostMapping("/salvar")
    public String salvarTarefa(Tarefa tarefa) {
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            return "redirect:/tarefas/cadastrar";
        }
        tarefa.setId(proximoId);
        proximoId++;
        tarefa.setStatus(Status.EM_ANDAMENTO);
        this.listaDeTarefas.add(tarefa);
        return "tarefa-salva";
    }

    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = buscarPorId(id);
        model.addAttribute("tarefa", tarefa);
        return "tarefa-editar";
    }

    @PostMapping("/atualizar")
    public String atualizarTarefa(Tarefa tarefa) {
        for (int i = 0; i < listaDeTarefas.size(); i++) {
            if (listaDeTarefas.get(i).getId().equals(tarefa.getId())) {
                listaDeTarefas.set(i, tarefa);
                break;
            }
        }
        return "tarefa-salva";
    }

    @GetMapping("/confirmar-remocao/{id}")
    public String confirmarRemocao(@PathVariable Long id, Model model) {
        Tarefa tarefa = buscarPorId(id);
        model.addAttribute("tarefa", tarefa);
        return "tarefa-confirmar-remocao";
    }

    @GetMapping("/remover/{id}")
    public String removerTarefa(@PathVariable Long id) {
        Tarefa tarefa = buscarPorId(id);
        if (tarefa != null) {
            listaDeTarefas.remove(tarefa);
        }
        return "redirect:/tarefas";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = buscarPorId(id);
        model.addAttribute("tarefa", tarefa);
        return "tarefa-detalhes";
    }

    @GetMapping("/em-andamento")
    public String listarEmAndamento(Model model) {
        List<Tarefa> emAndamento = new ArrayList<>();
        for (Tarefa t : listaDeTarefas) {
            if (t.getStatus() == Status.EM_ANDAMENTO) {
                emAndamento.add(t);
            }
        }
        model.addAttribute("tarefas", emAndamento);
        return "tarefa-lista";
    }

    private Tarefa buscarPorId(Long id) {
        for (Tarefa t : listaDeTarefas) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
