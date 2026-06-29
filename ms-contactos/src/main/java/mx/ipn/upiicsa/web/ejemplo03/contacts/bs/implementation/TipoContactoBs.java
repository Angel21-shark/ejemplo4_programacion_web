package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.TipoContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao.TipoContactoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TipoContactoBs {

    @Autowired
    private TipoContactoDao tipoContactoDao;

    public List<TipoContacto> getActiveTiposContacto() {
        return tipoContactoDao.findAllActive();
    }
}
