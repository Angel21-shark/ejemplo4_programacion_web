package mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.dao;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.entity.UserJpa;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
public class LoginDao {
    @Autowired
    UserJpaRepository userJpaRepository;

    public Optional<User> login(String username, String password) {
        //Optional<UsuarioJpa> resultado = usuarioJpaRepository.findByUsernameAndPassword(username, password);
        //if (resultado.isPresent()) {
        //    return Optional.of(resultado.get().toEntity());
        //} else {
        //    return Optional.empty();
        //}
        var resultado = userJpaRepository.findByUsernameAndPassword(username, password);
        //return resultado.map(usuarioJpa -> usuarioJpa.toEntity());
        return resultado.map(UserJpa::toEntity);
    }
}
