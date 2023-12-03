package com.br.sunioweb.editais.service;

import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.repository.EditalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EditalService {

    @Autowired
    private EditalRepository editalRepository;

    public Page<Edital> listAll(Pageable pageable, String seacrhParam)
    {
        if (seacrhParam != null && !seacrhParam.isEmpty()) {
            return editalRepository.findAll(pageable);
        } else {
            return editalRepository.findAll(pageable);
        }
    }
    public Page<Edital> listIndex(Pageable pageable, String seacrhParam)
    {
        if (seacrhParam != null && !seacrhParam.isEmpty()) {
            return editalRepository.findVisibleEditaisByParam(
                    seacrhParam, pageable);
        } else {
            return editalRepository.findByVisibility(true, pageable);
        }
    }

    public Edital findById(Long id){ return editalRepository.findById(id).orElse(null);}

    public  Edital save(Edital edital)
    {
        Date currentTime = new Date();
        edital.setDateLastUpdate(currentTime);
        edital.setDatePublication(currentTime);
        return editalRepository.save(edital);
    }

    public Edital update(Edital edital)
    {
        Date currentTime = new Date();
        edital.setDateLastUpdate(currentTime);
        System.out.println(edital.toString());
        return editalRepository.save(edital);
    }

    public void delete(Long id){editalRepository.deleteById(id);}



}
