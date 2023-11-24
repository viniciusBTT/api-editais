package com.br.sunioweb.editais.controller;

import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.dto.edital.PatchEditalDTO;
import com.br.sunioweb.editais.dto.edital.PostEditalDTO;
import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.service.EditalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edital")
public class EditalController {

    @Autowired
    private EditalService editalService;

    @GetMapping
    public ResponseDTO find (@RequestBody Long id)
    {
        var edital = editalService.find(id);

        if (edital == null)
            return new ResponseDTO("Falha ao localizar o usuario","404",null);

        return new ResponseDTO("sucesso","200",edital);
    }
    @GetMapping("/list")
    public ResponseDTO list ()
    {
        var editaisList = editalService.list();
        return new ResponseDTO("sucesso","200",editaisList);
    }

    @PostMapping
    public ResponseDTO save (@RequestBody PostEditalDTO data)
    {
        var newEdital =  editalService.save(new Edital(data.name(),data.number()));


        return new ResponseDTO("Edital salvo com sucesso","200",newEdital);
    }

    @PatchMapping
    public ResponseDTO update(@RequestBody @Valid PatchEditalDTO data)
    {
        var newEdital = editalService.save(new Edital(data.id(), data.name(), data.number()));
        return new ResponseDTO("Edital atualizado","200",newEdital);
    }

    @DeleteMapping
    public ResponseDTO delete(@RequestParam Long id)
    {
        editalService.delete(id);
        return new ResponseDTO("Edital deletado","200",null);
    }
}
