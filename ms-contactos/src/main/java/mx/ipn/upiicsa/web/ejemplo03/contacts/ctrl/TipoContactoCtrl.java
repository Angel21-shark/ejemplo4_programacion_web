package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.TipoContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.TipoContactoBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tiposcontacto")
public class TipoContactoCtrl {
    @Autowired
    private TipoContactoBs tipoContactoBs;

    @GetMapping
    public List<TipoContacto> getActiveTiposContacto() {
        return tipoContactoBs.getActiveTiposContacto();
    }
}
