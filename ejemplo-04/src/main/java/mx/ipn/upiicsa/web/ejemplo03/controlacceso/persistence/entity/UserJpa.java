package mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactJpa;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tag01_usuario")
public class UserJpa {
    @Id
    @SequenceGenerator(name = "tag01_usuario_id_usuario_seq", sequenceName = "tag01_usuario_id_usuario_seq", allocationSize = 1)
    @GeneratedValue(generator = "tag01_usuario_id_usuario_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario")
    private Integer id;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "tx_primer_apellido")
    private String primerApellido;
    @Column(name = "tx_segundo_apellido")
    private String segundoApellido;
    @Column(name = "tx_apodo")
    private String nickName;
    @Column(name = "tx_username")
    private String username;
    @Column(name = "tx_password")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<ContactJpa> contacts;

    public static UserJpa fromEntity(User entity) {
        return UserJpa.builder()
                .id(entity.getId())
                .nombre(entity.getName())
                .primerApellido(entity.getLastName())
                .segundoApellido(entity.getSecondLastName())
                .nickName(entity.getNickName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(nombre)
                .lastName(primerApellido)
                .secondLastName(segundoApellido)
                .nickName(nickName)
                .username(username)
                .password(password)
                .build();
    }
}
