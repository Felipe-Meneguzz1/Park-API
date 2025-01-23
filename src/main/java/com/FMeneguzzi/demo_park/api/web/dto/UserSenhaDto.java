package com.FMeneguzzi.demo_park.api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSenhaDto {

    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;
}
