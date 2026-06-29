package mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation.UserBs;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.UpdateUserDto;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UsersCtrl {
    @Autowired
    private UserBs userBs;

    @GetMapping
    public List<UserDto> index() {
        return userBs.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Integer idUsuario) {
        Optional<User> resultadoUser = userBs.findById(idUsuario);
        return resultadoUser.map(user -> ResponseEntity.ok(UserDto.fromEntity(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Integer idUsuario, @RequestBody UpdateUserDto updateUserDto) {
        User user = updateUserDto.toEntity();
        user.setId(idUsuario);
        User resultdo = userBs.update(user);
        return ResponseEntity.ok(UserDto.fromEntity(resultdo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer idUsuario) {
        userBs.delete(idUsuario);
        return ResponseEntity.ok().build();
    }
}
