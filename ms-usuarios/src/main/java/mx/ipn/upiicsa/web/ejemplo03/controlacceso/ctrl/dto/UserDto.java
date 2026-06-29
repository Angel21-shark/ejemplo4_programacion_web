package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto;

import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private Integer id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .username(username)
                .password(password)
                .build();
    }

    public static UserDto fromEntity(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .username(entity.getUsername())
                .build();
    }
}
