package mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
public class User implements Serializable {
    private Integer id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickName;
    private String username;
    private String password;
}
