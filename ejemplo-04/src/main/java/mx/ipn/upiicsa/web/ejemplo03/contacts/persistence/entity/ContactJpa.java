package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.entity.UserJpa;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tag02_contacto")
public class ContactJpa {
    @Id
    @SequenceGenerator(name = "tag02_contacto_id_contacto_seq", sequenceName = "tag02_contacto_id_contacto_seq", allocationSize = 1)
    @GeneratedValue(generator = "tag02_contacto_id_contacto_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_contacto")
    private Integer id;
    @Column(name = "fk_id_usuario")
    private Integer idUser;
    @Column(name = "tx_nombre")
    private String name;
    @Column(name = "tx_primer_apellido")
    private String lastName;
    @Column(name = "tx_segundo_apellido")
    private String secondLastName;
    @Column(name = "tx_apodo")
    private String nickName;
    @ManyToOne
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private UserJpa user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioContactoJpa> medios;

    public static ContactJpa fromEntity(Contact entity) {
        return ContactJpa.builder()
                .id(entity.getId())
                .idUser(entity.getIdUser())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .secondLastName(entity.getSecondLastName())
                .nickName(entity.getNickName())
                .build();
    }

    public Contact toEntity() {
        return Contact.builder()
                .id(id)
                .idUser(idUser)
                .name(name)
                .lastName(lastName)
                .secondLastName(secondLastName)
                .nickName(nickName)
                .build();
    }
}
