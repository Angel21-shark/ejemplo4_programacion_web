package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository;

import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactJpaRepository extends JpaRepository<ContactJpa, Integer> {
}
