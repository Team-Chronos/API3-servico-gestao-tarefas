package com.criarTarefas.criarTarefas.controle;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.criarTarefas.criarTarefas.modelo.DTO.ProfissionalNomeDTO;
import com.criarTarefas.criarTarefas.servico.ProfissionalExternoServico;

@RestController
@RequestMapping("/api/profissionais")
@CrossOrigin(origins = "*")
public class ProfissionalController {

    private final ProfissionalExternoServico profissionalExternoServico;

    public ProfissionalController(ProfissionalExternoServico profissionalExternoServico) {
        this.profissionalExternoServico = profissionalExternoServico;
    }

    @GetMapping("/nomes")
    public List<ProfissionalNomeDTO> listarNomes() {
        return profissionalExternoServico.listarNomesProfissionais();
    }
}