package com.br.sunioweb.editais.service;

import com.br.sunioweb.editais.model.Edital;
import com.br.sunioweb.editais.repository.EditalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditalService {

    @Autowired
    private EditalRepository editalRepository;

    public List<Edital> list (){ return editalRepository.findAll();}

    public Edital find(Long id){ return editalRepository.findById(id).orElse(null);}

    public  Edital save(Edital edital){ return editalRepository.save(edital);}

    public void delete(Long id){editalRepository.deleteById(id);}
}
