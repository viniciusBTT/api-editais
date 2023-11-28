package com.br.sunioweb.editais.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    /**
     * Definindo quem pode fazer requests a API e quais metodos e end points podem ser acessados
     * @param registry
     */

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Permita solicitações do seu frontend (localhost)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Defina os métodos HTTP permitidos
                .allowedHeaders("*") // Permita todos os cabeçalhos
                .allowCredentials(true); // Permita credenciais (por exemplo, cookies)
    }

}