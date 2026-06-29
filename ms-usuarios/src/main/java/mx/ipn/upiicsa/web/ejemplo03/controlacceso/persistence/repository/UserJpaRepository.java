package mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpa,Integer> {
    Optional<UserJpa> findByUsernameAndPassword(String username, String password);
}
