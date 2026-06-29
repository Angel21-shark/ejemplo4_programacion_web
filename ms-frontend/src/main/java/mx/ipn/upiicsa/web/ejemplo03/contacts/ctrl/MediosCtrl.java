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

/**
 * Controlador MVC para gestionar los Medios de Contacto.
 * Permite agregar, editar, listar y eliminar medios asociados a un contacto en específico.
 */
@Controller
@RequestMapping("/contacts/{idContacto}/medios")
public class MediosCtrl {

    @Autowired
    private MedioContactoBs medioContactoBs;

    @Autowired
    private ContactsBs contactsBs;
    
    @Autowired
    private TipoContactoBs tipoContactoBs;

    /**
     * Valida si el usuario en sesión es propietario del contacto al que intenta acceder.
     *
     * @param session    sesión HTTP actual del usuario
     * @param idContacto el identificador del contacto a validar
     * @return true si es propietario, false de lo contrario
     */
    private boolean isUserAuthorized(HttpSession session, Integer idContacto) {
        var user = (User) session.getAttribute("usuarioSesion");
        if (user == null) return false;
        Contact contact = contactsBs.getContactById(idContacto);
        return contact != null && contact.getIdUser().equals(user.getId());
    }

    /**
     * Muestra la vista principal con la lista de medios de un contacto.
     *
     * @param idContacto el identificador del contacto
     * @param model      modelo para la vista Thymeleaf
     * @param session    sesión HTTP actual
     * @return la plantilla de la lista de medios
     */
    @GetMapping
    public String index(@PathVariable("idContacto") Integer idContacto, Model model, HttpSession session) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        model.addAttribute("contacto", contactsBs.getContactById(idContacto));
        model.addAttribute("medios", medioContactoBs.getMediosByContacto(idContacto));
        model.addAttribute("tiposContacto", tipoContactoBs.getActiveTiposContacto());
        model.addAttribute("nuevoMedio", MedioContacto.builder().idContacto(idContacto).build());
        
        return "contacts/medios";
    }

    /**
     * Procesa la solicitud POST para agregar un nuevo medio a un contacto.
     *
     * @param idContacto         el identificador del contacto
     * @param medio              DTO/Entidad del medio capturado en el formulario
     * @param session            sesión HTTP actual
     * @param redirectAttributes atributos flash para mensajes de éxito
     * @return redirección a la lista de medios del contacto
     */
    @PostMapping("/save")
    public String save(@PathVariable("idContacto") Integer idContacto, @ModelAttribute MedioContacto medio, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        medio.setIdContacto(idContacto);
        medioContactoBs.saveMedioContacto(medio);
        redirectAttributes.addFlashAttribute("mensaje", "Medio de contacto guardado correctamente");
        return "redirect:/contacts/" + idContacto + "/medios";
    }

    /**
     * Muestra la vista con el formulario para editar un medio de contacto existente.
     *
     * @param idContacto el identificador del contacto padre
     * @param idMedio    el identificador del medio a editar
     * @param model      modelo para la vista Thymeleaf
     * @param session    sesión HTTP actual
     * @return la plantilla del formulario de edición de medio
     */
    @GetMapping("/{idMedio}/edit")
    public String edit(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio, Model model, HttpSession session) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        MedioContacto medio = medioContactoBs.getMedioById(idContacto, idMedio);
        if (medio == null || !medio.getIdContacto().equals(idContacto)) return "redirect:/contacts/" + idContacto + "/medios";
        
        model.addAttribute("contacto", contactsBs.getContactById(idContacto));
        model.addAttribute("medio", medio);
        model.addAttribute("tiposContacto", tipoContactoBs.getActiveTiposContacto());
        
        return "contacts/medios-edit";
    }

    /**
     * Procesa la solicitud POST para guardar los cambios de un medio editado.
     *
     * @param idContacto         el identificador del contacto padre
     * @param idMedio            el identificador del medio a actualizar
     * @param medioForm          los datos del formulario
     * @param session            sesión HTTP actual
     * @param redirectAttributes atributos flash para mensajes de éxito
     * @return redirección a la lista de medios del contacto
     */
    @PostMapping("/{idMedio}/edit")
    public String update(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio, @ModelAttribute MedioContacto medioForm, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        MedioContacto medio = medioContactoBs.getMedioById(idContacto, idMedio);
        if (medio != null && medio.getIdContacto().equals(idContacto)) {
            medio.setIdTipoContacto(medioForm.getIdTipoContacto());
            medio.setValor(medioForm.getValor());
            medioContactoBs.saveMedioContacto(medio);
            redirectAttributes.addFlashAttribute("mensaje", "Medio actualizado correctamente");
        }
        return "redirect:/contacts/" + idContacto + "/medios";
    }

    /**
     * Procesa la solicitud POST para eliminar un medio de contacto.
     * Verifica que el medio pertenezca al contacto.
     *
     * @param idContacto         el identificador del contacto padre
     * @param idMedio            el identificador del medio a eliminar
     * @param session            sesión HTTP actual
     * @param redirectAttributes atributos flash para mensajes
     * @return redirección a la lista de medios del contacto
     */
    @PostMapping("/{idMedio}/delete")
    public String delete(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isUserAuthorized(session, idContacto)) return "redirect:/contacts";
        
        // Ensure medio belongs to the contact
        MedioContacto medio = medioContactoBs.getMedioById(idContacto, idMedio);
        if(medio != null && medio.getIdContacto().equals(idContacto)) {
            medioContactoBs.deleteMedio(idContacto, idMedio);
            redirectAttributes.addFlashAttribute("mensaje", "Medio eliminado");
        }
        return "redirect:/contacts/" + idContacto + "/medios";
    }
}
