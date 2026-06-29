package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto;

import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateUserDto {
    private String name;
    private String lastName;
    private String secondLastName;
    private String username;

    public User toEntity() {
        return User.builder()
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .username(username)
                .build();
    }

    public static UpdateUserDto fromEntity(User entity) {
        return UpdateUserDto.builder()
                .name(entity.getName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .username(entity.getUsername())
                .build();
    }
}
