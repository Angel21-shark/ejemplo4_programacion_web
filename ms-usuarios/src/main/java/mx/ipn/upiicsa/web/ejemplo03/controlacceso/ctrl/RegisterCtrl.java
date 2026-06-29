package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation.UserBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.RegisterUserDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/register")
public class RegisterCtrl {
    @Autowired
    private UserBs userBs;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody RegisterUserDto registerUserDto) {
        log.info("Creating new user");
        User user = userBs.create(registerUserDto.toEntity());
        return ResponseEntity.ok(UserDto.fromEntity(user));
    }
}
