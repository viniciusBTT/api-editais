package com.br.sunioweb.editais.service;

import com.br.sunioweb.editais.model.Requester;
import com.br.sunioweb.editais.repository.RequesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequesterService {
    @Autowired
    private RequesterRepository requesterRepository;


    public Requester save (Requester requester)
    {
        return  requesterRepository.save(requester);
    }

    public List<Requester> findByNomenclature(String nomenclature)
    {
         return requesterRepository.findByNomenclature(nomenclature);
    }

    /**
     *  função para verificar se já existe um solicitante com nome e ou email igual já cadastrado
     * @param requester
     * @return o usuario salvo
     */
    public Requester checkEndSave(Requester requester)
    {
        Requester requesterSaved = requesterRepository.findByNomenclatureAndEmail(requester.getNomenclature(), requester.getEmail());
        if(requesterSaved == null)
            requesterSaved = requesterRepository.save(requester);

        return requesterSaved;
    }
}
