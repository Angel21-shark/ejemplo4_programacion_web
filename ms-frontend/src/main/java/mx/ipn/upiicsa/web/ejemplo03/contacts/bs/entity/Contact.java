package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Contact {
    private Integer id;
    private Integer idUser;
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickName;
}
