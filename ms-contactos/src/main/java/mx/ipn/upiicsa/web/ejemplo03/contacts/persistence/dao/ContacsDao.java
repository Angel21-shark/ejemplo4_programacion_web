package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.ContactJpa;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository.ContactJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContacsDao {
    @Autowired
    ContactJpaRepository contactJpaRepository;

    public List<Contact> findAll(Integer idUser) {
        return contactJpaRepository.findByIdUser(idUser)
                .stream()
                .map(ContactJpa::toEntity)
                .toList();
    }

    public Contact save(Contact contact) {
        var contactSavedJpa = contactJpaRepository.save(ContactJpa.fromEntity(contact));
        return contactSavedJpa.toEntity();
    }

    public java.util.Optional<Contact> findById(Integer idContacto) {
        return contactJpaRepository.findById(idContacto).map(ContactJpa::toEntity);
    }

    public void delete(Integer idContacto) {
        contactJpaRepository.deleteById(idContacto);
    }
}
