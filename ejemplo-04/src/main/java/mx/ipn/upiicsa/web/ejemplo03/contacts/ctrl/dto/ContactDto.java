package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;

@Builder
@Setter
@Getter
public class ContactDto {
    private Integer id;
    private Integer idUser;
    private String name;
    private String lastName;
    private String secondLastName;
    private String nickName;

    public static ContactDto fromEntity(Contact entity) {
        return ContactDto.builder()
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
