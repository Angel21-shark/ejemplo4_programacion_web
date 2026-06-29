package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactsBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactsCrtl {
    @Autowired
    private ContactsBs contactsBs;

    @GetMapping("/user/{idUser}")
    public List<ContactDto> getAll(@PathVariable("idUser") Integer idUser) {
        return contactsBs.getContacts(idUser).stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable("id") Integer id) {
        Contact contact = contactsBs.getContactById(id);
        if (contact == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ContactDto.fromEntity(contact));
    }

    @PostMapping
    public ResponseEntity<ContactDto> saveContact(@RequestBody ContactDto contactDto) {
        Contact contact = Contact.builder()
                .id(contactDto.getId())
                .idUser(contactDto.getIdUser())
                .name(contactDto.getName())
                .lastName(contactDto.getLastName())
                .secondLastName(contactDto.getSecondLastName())
                .nickName(contactDto.getNickName())
                .build();
        Contact saved = contactsBs.saveContact(contact);
        return ResponseEntity.ok(ContactDto.fromEntity(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Integer id) {
        contactsBs.deleteContact(id);
        return ResponseEntity.ok().build();
    }
}
