package com.FMeneguzzi.demo_park.api;

import com.FMeneguzzi.demo_park.api.jwt.JwtToken;
import com.FMeneguzzi.demo_park.api.web.dto.UsuarioLoginDto;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.http.HttpHeaders;
import java.util.function.Consumer;

public class JwtAuthentication {

    public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String username, String password){
        String token = client
                .post()
                .uri("/api/v1/path")
                .bodyValue(new UsuarioLoginDto(username, password))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody().getToken();
        return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
