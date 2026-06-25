package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.MedioContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao.MedioContactoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MedioContactoBs {

    @Autowired
    private MedioContactoDao medioContactoDao;

    public List<MedioContacto> getMediosByContacto(Integer idContacto) {
        return medioContactoDao.findByContactoId(idContacto);
    }

    public MedioContacto saveMedioContacto(MedioContacto medio) {
        return medioContactoDao.save(medio);
    }

    public MedioContacto getMedioById(Integer idMedio) {
        return medioContactoDao.findById(idMedio).orElse(null);
    }

    public void deleteMedio(Integer idMedio) {
        medioContactoDao.delete(idMedio);
    }
}
