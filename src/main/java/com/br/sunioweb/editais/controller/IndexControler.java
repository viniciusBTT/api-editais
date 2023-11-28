package com.br.sunioweb.editais.controller;


import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.service.EditalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexControler {
    @Autowired
    EditalService editalService;

    @GetMapping
    public ResponseDTO index(){
        List<Edital> editais = editalService.findAllVisibility(true);
        return new ResponseDTO("Consulta bem sucedida","200",editais );
    }

}
