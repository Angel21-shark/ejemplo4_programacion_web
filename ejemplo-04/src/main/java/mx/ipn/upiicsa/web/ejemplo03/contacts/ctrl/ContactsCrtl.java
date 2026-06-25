package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactsBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl.dto.ContactDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contacts")
public class ContactsCrtl {
    @Autowired
    private ContactsBs contactsBs;

    @GetMapping
    public String getAll(Model model, HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        if(user == null) return "redirect:/";
        
        List<Contact> contacts = contactsBs.getContacts(user.getId());
        List<ContactDto> contactDtos = contacts.stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
                
        model.addAttribute("contacts", contactDtos);
        return "contacts/index";
    }

    @GetMapping("/new")
    public String showNewForm(Model model, HttpSession session) {
        if(session.getAttribute("usuarioSesion") == null) return "redirect:/";
        model.addAttribute("contact", new ContactDto());
        return "contacts/new";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute ContactDto contactDto, HttpSession session, RedirectAttributes redirectAttributes) {
        var user = (User) session.getAttribute("usuarioSesion");
        if(user == null) return "redirect:/";

        Contact contact = Contact.builder()
                .id(contactDto.getId())
                .idUser(user.getId())
                .name(contactDto.getName())
                .lastName(contactDto.getLastName())
                .secondLastName(contactDto.getSecondLastName())
                .nickName(contactDto.getNickName())
                .build();
                
        contactsBs.saveContact(contact);
        redirectAttributes.addFlashAttribute("mensaje", "Contacto guardado exitosamente");
        return "redirect:/contacts";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        if(user == null) return "redirect:/";

        Contact contact = contactsBs.getContactById(id);
        if (contact == null || !contact.getIdUser().equals(user.getId())) {
            return "redirect:/contacts"; // Protege contra acceso a contactos ajenos
        }
        
        model.addAttribute("contact", ContactDto.fromEntity(contact));
        return "contacts/edit";
    }

    @PostMapping("/{id}/delete")
    public String deleteContact(@PathVariable("id") Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        var user = (User) session.getAttribute("usuarioSesion");
        if(user == null) return "redirect:/";

        Contact contact = contactsBs.getContactById(id);
        if (contact != null && contact.getIdUser().equals(user.getId())) {
            contactsBs.deleteContact(id);
            redirectAttributes.addFlashAttribute("mensaje", "Contacto eliminado");
        }
        return "redirect:/contacts";
    }
}
