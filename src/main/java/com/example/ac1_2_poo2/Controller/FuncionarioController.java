package com.example.ac1_2_poo2.Controller;

import com.example.ac1_2_poo2.Entity.Funcionario;
import com.example.ac1_2_poo2.Service.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/funcionarios")
    public ModelAndView getFuncionarios(){

        ModelAndView mv = new ModelAndView("funcionarios");
        mv.addObject("funcionarios", service.getFuncionarios());

        return mv;
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Funcionario funcionario){
        
        if(validar(funcionario)){
            service.salvar(funcionario);
            return "redirect:/funcionarios";
        } else{
            return "erro";
        }
    }

    public boolean validar(Funcionario funcionario){
        if(funcionario.getNome() == null || funcionario.getCargo() == null || funcionario.getSalario() == null || funcionario.getSetor() == null)
            return false;
        else if(funcionario.getNome().trim().length() == 0  || funcionario.getCargo().trim().length() == 0  || funcionario.getSetor().trim().length() == 0 )
            return false;
        else
            return true;
    }

}