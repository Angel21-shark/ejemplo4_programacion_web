package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.TipoContacto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cat_tipo_contacto")
public class TipoContactoJpa {
    @Id
    @SequenceGenerator(name = "cat_tipo_contacto_id_seq", sequenceName = "cat_tipo_contacto_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "cat_tipo_contacto_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_tipo_contacto")
    private Integer id;
    
    @Column(name = "tx_nombre")
    private String nombre;
    
    @Column(name = "st_activo")
    private Boolean activo;

    public static TipoContactoJpa fromEntity(TipoContacto entity) {
        return TipoContactoJpa.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .activo(entity.getActivo())
                .build();
    }

    public TipoContacto toEntity() {
        return TipoContacto.builder()
                .id(id)
                .nombre(nombre)
                .activo(activo)
                .build();
    }
}
