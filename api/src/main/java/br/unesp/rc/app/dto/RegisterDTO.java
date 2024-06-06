package br.unesp.rc.app.dto;

import br.unesp.rc.app.model.UserRole;
import br.unesp.rc.app.model.Usuario.Sexo;

public record RegisterDTO(String login, String email, String password, Sexo sexo, UserRole role) {

}
