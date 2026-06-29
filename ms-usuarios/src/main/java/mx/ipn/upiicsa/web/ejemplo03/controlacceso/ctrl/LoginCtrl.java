package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl;

import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation.LoginBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginCtrl {
    @Autowired
    private LoginBs loginBs;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto user) {
        Optional<User> resultadoUsuario = loginBs.login(user.getUsername(), user.getPassword());
        return resultadoUsuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}