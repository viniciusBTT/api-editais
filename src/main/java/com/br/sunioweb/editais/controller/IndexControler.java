package com.br.sunioweb.editais.controller;


import com.br.sunioweb.editais.dto.email.SendEmailDTO;
import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.model.Requester;
import com.br.sunioweb.editais.model.Requisition;
import com.br.sunioweb.editais.service.EditalService;
import com.br.sunioweb.editais.service.EmailService;
import com.br.sunioweb.editais.service.RequesterService;
import com.br.sunioweb.editais.service.RequisitionService;
import com.br.sunioweb.editais.ultil.ParseSort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
@CrossOrigin(origins = ("http://localhost:3000/"))
public class IndexControler {
    @Autowired
    private EditalService editalService;

    @Autowired
    private RequesterService requesterService;

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private ParseSort parseSort;

    @Autowired
    private EmailService emailService;

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
        Page<Edital> editalPage = editalService.listIndex(pageable, param);

        if (editalPage.isEmpty())
            return new ResponseEntity<>(new ResponseDTO("Nenhum registro encontrado",null), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ResponseDTO("Registros encontrador",editalPage),
                HttpStatus.OK);
    }

    // Método para analisar os campos de ordenação


    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailDTO data)
    {
        Edital edital = editalService.findById(data.codEdital());
        if (edital == null)
            return new ResponseEntity<>("Edital não localizado", HttpStatus.NOT_FOUND);

        Requester requester = new Requester(data.email(), data.nomenclature());

        requester = requesterService.checkEndSave(requester);

        requisitionService.save(new Requisition(requester, edital));

        String text = "Conforme solicitado segue em anexo os arquivos do edital " + edital.getName();
        emailService.sendmail(
                requester.getEmail(),
                "Edital: " + edital.getName(),
                text
        );

        return new ResponseEntity<>("Email encaminhado com sucesso", HttpStatus.OK);


    }



}
