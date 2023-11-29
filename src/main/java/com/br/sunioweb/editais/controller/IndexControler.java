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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexControler {
    @Autowired
    private EditalService editalService;

    @Autowired
    private RequesterService requesterService;

    @Autowired
    private RequisitionService requisitionService;


    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseDTO index(){
        List<Edital> editais = editalService.findAllVisibility(true);
        return new ResponseDTO("Consulta bem sucedida","200",editais );
    }

    @PostMapping("/sendEmail")
    public ResponseDTO sendEmail(@RequestBody SendEmailDTO data)
    {
        Edital edital = editalService.findById(data.codEdital());
        if (edital == null)
            return new ResponseDTO("Edital não localizado","404",null );

        Requester requester = new Requester(data.email(), data.nomenclature());

        requester = requesterService.checkEndSave(requester);

        requisitionService.save(new Requisition(requester, edital));

        String text = "Conforme solicitado segue em anexo os arquivos do edital " + edital.getName();
        emailService.sendmail(
                requester.getEmail(),
                "Edital: " + edital.getName(),
                text
        );

        return new ResponseDTO("Solicitação encaminhada com sucesso","200",null );

    }



}
