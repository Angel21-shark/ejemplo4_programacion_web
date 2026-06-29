package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.MedioContactoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedioContactoJpaRepository extends JpaRepository<MedioContactoJpa, Integer> {
    List<MedioContactoJpa> findByIdContacto(Integer idContacto);
}
