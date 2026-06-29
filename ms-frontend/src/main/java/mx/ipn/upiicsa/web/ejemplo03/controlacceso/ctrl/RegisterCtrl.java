package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl;

import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation.UserBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.RegisterUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterCtrl {
    private static final Logger log = LoggerFactory.getLogger(RegisterCtrl.class);
    @Autowired
    UserBs userBs;

    @GetMapping("/register/new")
    public String editNew(Model model){
        model.addAttribute("registerUserDto", RegisterUserDto.builder().build());
        return "register/new";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute("registerUserDto") RegisterUserDto registerUserDto, Model model, RedirectAttributes redirectAttributes){
        log.info("Creating new user");
        redirectAttributes.addFlashAttribute("messageConfirmacion","El usuario se registró exitósamente");
        userBs.create(registerUserDto.toEntity());
        return "redirect:/";
    }
}
