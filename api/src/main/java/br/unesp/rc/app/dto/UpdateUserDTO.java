package br.unesp.rc.app.dto;

import br.unesp.rc.app.model.Usuario.Sexo;

public record UpdateUserDTO(String login, String email, String currentPassword, String newPassword, String confirmPassword, Sexo sexo) {
    
}
