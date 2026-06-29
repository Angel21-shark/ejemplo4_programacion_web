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

/**
 * Controlador MVC responsable de la gestión de Contactos.
 * Intercepta las solicitudes web relacionadas a la creación, edición,
 * listado y eliminación de contactos de la agenda del usuario.
 */
@Controller
@RequestMapping("/contacts")
public class ContactsCrtl {
    @Autowired
    private ContactsBs contactsBs;

    /**
     * Muestra la vista principal con la lista de contactos del usuario en sesión.
     *
     * @param model   modelo para pasar datos a la vista Thymeleaf
     * @param session sesión HTTP actual para validar la autenticación
     * @return la plantilla de la lista de contactos o redirección al inicio si no
     *         hay sesión
     */
    @GetMapping
    public String getAll(Model model, HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null)
            return "redirect:/";

        List<Contact> contacts = contactsBs.getContacts(user.getId());
        List<ContactDto> contactDtos = contacts.stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());

        model.addAttribute("contacts", contactDtos);
        return "contacts/index";
    }

    /**
     * Presenta el formulario vacío para crear un nuevo contacto.
     *
     * @param model   modelo para pasar el objeto DTO vacío a la vista
     * @param session sesión HTTP actual
     * @return la plantilla del formulario de nuevo contacto
     */
    @GetMapping("/new")
    public String showNewForm(Model model, HttpSession session) {
        if (session.getAttribute("usuarioSesion") == null)
            return "redirect:/";
        model.addAttribute("contact", new ContactDto());
        return "contacts/new";
    }

    /**
     * Procesa la solicitud POST para guardar un contacto (nuevo o editado).
     *
     * @param contactDto         el DTO con los datos del contacto provenientes del
     *                           formulario
     * @param session            sesión HTTP actual para recuperar el propietario
     * @param redirectAttributes atributos para mostrar mensajes flash de éxito
     * @return redirección a la lista de contactos
     */
    @PostMapping("/save")
    public String saveContact(@ModelAttribute ContactDto contactDto, HttpSession session,
            RedirectAttributes redirectAttributes) {
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null)
            return "redirect:/";

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

    /**
     * Presenta el formulario pre-cargado para editar un contacto existente.
     * Valida que el contacto pertenezca al usuario en sesión.
     *
     * @param id      el identificador del contacto a editar
     * @param model   modelo para enviar el DTO a la vista
     * @param session sesión HTTP actual
     * @return la plantilla de edición o redirección si hay intento de acceso
     *         indebido
     */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpSession session) {
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null)
            return "redirect:/";

        Contact contact = contactsBs.getContactById(id);
        if (contact == null || !contact.getIdUser().equals(user.getId())) {
            return "redirect:/contacts"; // Protege contra acceso a contactos ajenos
        }

        model.addAttribute("contact", ContactDto.fromEntity(contact));
        return "contacts/edit";
    }

    /**
     * Procesa la solicitud de eliminación de un contacto.
     *
     * @param id                 el identificador del contacto a eliminar
     * @param session            sesión HTTP actual
     * @param redirectAttributes atributos para mostrar mensajes flash de
     *                           confirmación
     * @return redirección a la lista de contactos
     */
    @PostMapping("/{id}/delete")
    public String deleteContact(@PathVariable("id") Integer id, HttpSession session,
            RedirectAttributes redirectAttributes) {
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null)
            return "redirect:/";

        Contact contact = contactsBs.getContactById(id);
        if (contact != null && contact.getIdUser().equals(user.getId())) {
            contactsBs.deleteContact(id);
            redirectAttributes.addFlashAttribute("mensaje", "Contacto eliminado");
        }
        return "redirect:/contacts";
    }
}
