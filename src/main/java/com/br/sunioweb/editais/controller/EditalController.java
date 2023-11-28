package com.br.sunioweb.editais.controller;

import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.dto.edital.PatchEditalDTO;
import com.br.sunioweb.editais.dto.edital.PostEditalDTO;
import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.service.EditalService;
import com.br.sunioweb.editais.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/edital")
public class EditalController {

    @Autowired
    private EditalService editalService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseDTO find (@RequestParam Long id)
    {
        var edital = editalService.find(id);

        if (edital == null)
            return new ResponseDTO("Falha ao localizar o edital","404",null);

        return new ResponseDTO("sucesso","200",edital);
    }
    @GetMapping("/list")
    public ResponseDTO list ()
    {
        return new ResponseDTO("sucesso","200",editalService.list());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public ResponseDTO save (@RequestBody PostEditalDTO data)
    {
        try {
            var newEdital =  new Edital(data.name(),data.number(),data.description(),
                    data.disponibility(),data.visibility(),userService.findById(data.userId()));
            newEdital =  editalService.save(newEdital);
            return new ResponseDTO("Edital salvo com sucesso","200",newEdital);
        }catch (Exception e)
        {
            return new ResponseDTO("Erro ao salvar o edital","500",e.getMessage());
        }

    }

    /**
     *
     * @param data DTO que representado a atualização de edital
     * @return o novo edital preenchido
     */
    @PatchMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public ResponseDTO update(@RequestBody PatchEditalDTO data)
    {
        try
        {
            //Transformando a string de data em um objeto Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dataObjeto = dateFormat.parse(data.datePublication().trim());

            //Montando um objeto do tipo edital
            var newEdital =  new Edital(data.id(),data.name(),data.number(),data.description(),
                    data.disponibility(),data.visibility(),userService.findById(data.userId()),dataObjeto);

            editalService.update(newEdital);
            return new ResponseDTO("Edital atualizado","200",newEdital);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO("Falha a atualizar o edital","403",null);
        }

    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public ResponseDTO delete(@RequestParam Long id)
    {
        editalService.delete(id);
        return new ResponseDTO("Edital deletado","200",null);
    }



    

}
