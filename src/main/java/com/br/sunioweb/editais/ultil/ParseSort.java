package com.br.sunioweb.editais.ultil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParseSort {

    public Sort.Order[] order(String[] sortFields) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortField : sortFields) {
            String[] parts = sortField.split(",");
            if (parts.length == 2) {
                String field = parts[0];
                String direction = parts[1];
                orders.add(new Sort.Order(Sort.Direction.fromString(direction), field));
            }
        }
        return orders.toArray(new Sort.Order[0]);
    }
}
