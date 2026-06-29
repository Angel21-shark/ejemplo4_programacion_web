package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.TipoContactoJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContactoJpaRepository extends JpaRepository<TipoContactoJpa, Integer> {
}
