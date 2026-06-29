package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDto {
    @NotEmpty(message = "Favor de proporcionar el usuario")
    private String username;
    @NotEmpty(message = "Favor de proporcionar el password")
    private String password;
}
