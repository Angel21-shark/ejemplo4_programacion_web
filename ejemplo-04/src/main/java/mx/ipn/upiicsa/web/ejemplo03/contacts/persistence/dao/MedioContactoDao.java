package mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.MedioContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.entity.MedioContactoJpa;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.repository.MedioContactoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MedioContactoDao {
    @Autowired
    private MedioContactoJpaRepository repository;

    public List<MedioContacto> findByContactoId(Integer idContacto) {
        return repository.findByIdContacto(idContacto).stream()
                .map(MedioContactoJpa::toEntity)
                .toList();
    }

    public MedioContacto save(MedioContacto medio) {
        var saved = repository.save(MedioContactoJpa.fromEntity(medio));
        return saved.toEntity();
    }

    public Optional<MedioContacto> findById(Integer id) {
        return repository.findById(id).map(MedioContactoJpa::toEntity);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
