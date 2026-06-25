package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactsBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.ContactDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactsCrtl {
    @Autowired
    ContactsBs contactsBs;

    @GetMapping("/contacts")
    public String getAll(Model model, HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        var contacts = contactsBs.getContacts(user.getId());
        List<ContactDto> contactDtos = new ArrayList<>();
        for(Contact contact : contacts) {
            contactDtos.add(ContactDto.fromEntity(contact));
        }
        model.addAttribute("contacts", contactDtos);
        return "/contacts/index";
    }
}
