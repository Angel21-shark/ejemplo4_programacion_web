package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl;

import jakarta.validation.Valid;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation.LoginBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.LoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Controller
public class LoginCtrl {
    private static final Logger log = LoggerFactory.getLogger(LoginCtrl.class);
    @Autowired
    LoginBs loginBs;

    @GetMapping("/")
    public String inicio(Model model, HttpSession session) {
        // El "Modelo" sirve para enviar datos a la "Vista"
        log.info("Inicio: pasando por inicio");
        String resultado;
        var usuarioSesion = (User) session.getAttribute("usuarioSesion");
        if(usuarioSesion != null) {
            resultado = "redirect:/welcome";
        } else {
            model.addAttribute("usuarioDto", LoginDto.builder().build());
            populateLogin(model);
            resultado = "index";// Retorna el nombre del archivo HTML (src/main/resources/templates/index.html)
        }
        return resultado;
    }

    public void populateLogin(Model model){
        model.addAttribute("mensaje", "¡Bienvenido a mi aplicación Spring Boot MVC con Java 21!");
    }

    @PostMapping("/")
    public String procesarLogin(@Valid @ModelAttribute("usuarioDto") LoginDto user,
                                BindingResult result,
                                Model model,
                                HttpSession session
    ) {
        if(result.hasErrors()) {
            log.info("El formulario tiene errores");
            populateLogin(model);
            return "index";
        }
        // Aquí puedes validar los datos o imprimir en consola para probar
        System.out.println("Usuario recibido: " + user.getUsername());
        System.out.println("Password recibido: " + user.getPassword());
        Optional<User> resultadoUsuario = loginBs.login(user.getUsername(), user.getPassword());
        if (resultadoUsuario.isPresent()) {
            model.addAttribute("resultado", "¡Acceso concedido!");
            session.setAttribute("usuarioSesion", resultadoUsuario.get());
            return "redirect:/welcome";
        } else {
            populateLogin(model);
            return "index";
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}