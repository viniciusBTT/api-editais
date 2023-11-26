package com.br.sunioweb.editais.controller;

import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.dto.edital.PatchEditalDTO;
import com.br.sunioweb.editais.dto.edital.PostEditalDTO;
import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.service.EditalService;
import com.br.sunioweb.editais.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edital")
public class EditalController {

    @Autowired
    private EditalService editalService;
    @Autowired
    private UserService userService;

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
        try {
            var newEdital =  new Edital(data.name(),data.number(),data.description(),
                    data.disponibility(),data.visibility(),userService.findById(data.userId()));

            editalService.save(newEdital);


            return new ResponseDTO("Edital salvo com sucesso","200",newEdital);
        }catch (Exception e)
        {
            return new ResponseDTO("Erro ao salvar o edital","500",null);
        }

    }

    @PatchMapping
    public ResponseDTO update(@RequestBody PatchEditalDTO data)
    {
        try {
            System.out.println("entrouuuu");
            var newEdital =  new Edital(data.id(),data.name(),data.number(),data.description(),
                    data.disponibility(),data.visibility(),userService.findById(data.userId()),data.datePublication());

            editalService.update(newEdital);
            return new ResponseDTO("Edital atualizado","200",newEdital);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO("Falha a atualizar o edital","403",null);

        }

    }

    @DeleteMapping
    public ResponseDTO delete(@RequestParam Long id)
    {
        editalService.delete(id);
        return new ResponseDTO("Edital deletado","200",null);
    }
}
