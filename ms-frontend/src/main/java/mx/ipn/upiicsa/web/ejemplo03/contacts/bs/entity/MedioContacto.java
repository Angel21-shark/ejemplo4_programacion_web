package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MedioContacto {
    private Integer id;
    private Integer idContacto;
    private Integer idTipoContacto;
    private String valor;
    
    // Optional, to hold the type name when returning to view
    private String tipoContactoNombre;
}
