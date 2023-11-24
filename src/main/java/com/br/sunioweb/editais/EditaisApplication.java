package com.br.sunioweb.editais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()


public class EditaisApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(EditaisApplication.class, args);
	}

}
