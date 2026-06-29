package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.MedioContacto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tag03_medio_contacto")
public class MedioContactoJpa {
    @Id
    @SequenceGenerator(name = "tag03_medio_id_seq", sequenceName = "tag03_medio_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "tag03_medio_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medio_contacto")
    private Integer id;

    @Column(name = "fk_id_contacto")
    private Integer idContacto;

    @Column(name = "fk_id_tipo_contacto")
    private Integer idTipoContacto;

    @Column(name = "tx_valor")
    private String valor;

    @ManyToOne
    @JoinColumn(name = "fk_id_contacto", referencedColumnName = "id_contacto", insertable = false, updatable = false)
    private ContactJpa contact;

    @ManyToOne
    @JoinColumn(name = "fk_id_tipo_contacto", referencedColumnName = "id_tipo_contacto", insertable = false, updatable = false)
    private TipoContactoJpa tipoContacto;

    public static MedioContactoJpa fromEntity(MedioContacto entity) {
        return MedioContactoJpa.builder()
                .id(entity.getId())
                .idContacto(entity.getIdContacto())
                .idTipoContacto(entity.getIdTipoContacto())
                .valor(entity.getValor())
                .build();
    }

    public MedioContacto toEntity() {
        return MedioContacto.builder()
                .id(id)
                .idContacto(idContacto)
                .idTipoContacto(idTipoContacto)
                .valor(valor)
                .tipoContactoNombre(tipoContacto != null ? tipoContacto.getNombre() : null)
                .build();
    }
}
