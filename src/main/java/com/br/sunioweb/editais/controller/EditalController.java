package com.br.sunioweb.editais.controller;

import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.dto.edital.PatchEditalDTO;
import com.br.sunioweb.editais.dto.edital.PostEditalDTO;
import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.service.EditalService;
import com.br.sunioweb.editais.service.UserService;
import com.br.sunioweb.editais.ultil.ParseSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/edital")
@CrossOrigin(origins = ("http://localhost:3000/"))
public class EditalController {

    @Autowired
    private EditalService editalService;
    @Autowired
    private UserService userService;

    @Autowired
    private ParseSort parseSort;

    @GetMapping
    public ResponseEntity<ResponseDTO> find (@RequestParam Long id)
    {
        var edital = editalService.findById(id);

        if (edital == null)
            return new ResponseEntity<>( new ResponseDTO("Edital nao encontrada", null), HttpStatus.OK);

        return new ResponseEntity<>( new ResponseDTO("sucesso", edital), HttpStatus.OK);
    }
    /**
     *
     * @param page Pagina
     * @param size Tamanho da pagina
     * @param sortFields Ordenação da pagina
     * @param param paramentro de pesquisa
     * @return  objeto de paginação
     */
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "datePublication,desc") String[] sortFields,
            @RequestParam(required = false) String param // Adicione este parâmetro para o termo de pesquisa
    )
    {
        Sort sort = Sort.by(parseSort.order(sortFields));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Edital> editalPage = editalService.listAll(pageable, param);

        if (editalPage.isEmpty())
            return new ResponseEntity<>(new ResponseDTO("Nenhum registro encontrado", null),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ResponseDTO("Sucesso", editalPage), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public ResponseEntity<ResponseDTO> save (@RequestBody PostEditalDTO data)
    {
        try {
            var newEdital =  new Edital(data.name(),data.number(),data.description(),
                    data.disponibility(),data.visibility(),userService.findById(data.userId()));
            newEdital =  editalService.save(newEdital);
            return new ResponseEntity<>( new ResponseDTO("Edital salvo com sucesso",newEdital), HttpStatus.OK);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>( new ResponseDTO("Edital salvo com sucesso",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param data DTO que representado a atualização de edital
     * @return o novo edital preenchido
     */
    @PatchMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public ResponseEntity<ResponseDTO> update(@RequestBody PatchEditalDTO data)
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
            return new ResponseEntity<>( new ResponseDTO("Edital atualizado",newEdital), HttpStatus.OK);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>( new ResponseDTO("Edital atualizado",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ROOT')")
    public ResponseEntity<ResponseDTO> delete(@RequestParam Long id)
    {
        try {
            editalService.delete(id);
            return new ResponseEntity<>( new ResponseDTO("Edital deletado com sucesso!",null), HttpStatus.OK);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>( new ResponseDTO("Erro ao deletar",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    

}
