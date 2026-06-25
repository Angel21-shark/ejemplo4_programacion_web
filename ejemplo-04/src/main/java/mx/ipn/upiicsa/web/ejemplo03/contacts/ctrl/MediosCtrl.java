package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import jakarta.servlet.http.HttpSession;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.MedioContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.ContactsBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.MedioContactoBs;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.TipoContactoBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacts/{idContacto}/medios")
public class MediosCtrl {

    @Autowired
    private MedioContactoBs medioContactoBs;

    @Autowired
    private ContactsBs contactsBs;
    
    @Autowired
    private TipoContactoBs tipoContactoBs;

    private boolean isUserAuthorized(HttpSession session, Integer idContacto) {
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null) return false;
        Contact contact = contactsBs.getContactById(idContacto);
        return contact != null && contact.getIdUser().equals(user.getId());
    }

    @GetMapping
    public String index(@PathVariable("idContacto") Integer idContacto, Model model, HttpSession session) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        model.addAttribute("contacto", contactsBs.getContactById(idContacto));
        model.addAttribute("medios", medioContactoBs.getMediosByContacto(idContacto));
        model.addAttribute("tiposContacto", tipoContactoBs.getActiveTiposContacto());
        model.addAttribute("nuevoMedio", MedioContacto.builder().idContacto(idContacto).build());
        
        return "contacts/medios";
    }

    @PostMapping("/save")
    public String save(@PathVariable("idContacto") Integer idContacto, @ModelAttribute MedioContacto medio, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        medio.setIdContacto(idContacto);
        medioContactoBs.saveMedioContacto(medio);
        redirectAttributes.addFlashAttribute("mensaje", "Medio de contacto guardado correctamente");
        return "redirect:/contacts/" + idContacto + "/medios";
    }

    @PostMapping("/{idMedio}/delete")
    public String delete(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        // Ensure medio belongs to the contact
        MedioContacto medio = medioContactoBs.getMedioById(idMedio);
        if(medio != null && medio.getIdContacto().equals(idContacto)) {
            medioContactoBs.deleteMedio(idMedio);
            redirectAttributes.addFlashAttribute("mensaje", "Medio eliminado");
        }
        return "redirect:/contacts/" + idContacto + "/medios";
    }
}
