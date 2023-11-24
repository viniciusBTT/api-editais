package com.br.sunioweb.editais.ultil;

public enum EnumRoleUser {

    ADMIN("admin"),
    USER("user");

    private String role;

    EnumRoleUser(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
