package br.unesp.rc.app.dto;

import br.unesp.rc.app.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
