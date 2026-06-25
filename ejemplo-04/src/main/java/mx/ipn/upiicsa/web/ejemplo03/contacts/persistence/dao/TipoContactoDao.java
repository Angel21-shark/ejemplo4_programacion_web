package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.TipoContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.TipoContactoJpa;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository.TipoContactoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoContactoDao {
    @Autowired
    private TipoContactoJpaRepository repository;

    public List<TipoContacto> findAllActive() {
        return repository.findAll().stream()
                .filter(TipoContactoJpa::getActivo)
                .map(TipoContactoJpa::toEntity)
                .toList();
    }
}
