package com.br.sunioweb.editais.service;

import com.br.sunioweb.editais.model.Requisition;
import com.br.sunioweb.editais.repository.RequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionService {

    @Autowired
    private RequisitionRepository requisitionRepository;

    public Requisition save (Requisition requisition){
        return  requisitionRepository.save(requisition);
    }

    public List<Requisition> listALL() {
        return requisitionRepository.findAll();
    }
}
