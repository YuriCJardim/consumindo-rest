package br.com.ycj.consumindorest.integracao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ViaCepIntegracaoServiceTest {

    @Value("https://viacep.com.br/")
    private String urlCep;

    public RestTemplate obterRestTemplateCep() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(2))
                .setReadTimeout(Duration.ofSeconds(1))
                .build();
    }
    @Test
    public void testIntegracaoCep() {
        String fullUrl = "https://viacep.com.br/ws/{cep}/json/";
        Map<String,String> params = new HashMap<>();
        params.put("cep", "71900100");

        ResponseEntity<String> response =  obterRestTemplateCep().getForEntity(fullUrl,String.class, params);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

}

