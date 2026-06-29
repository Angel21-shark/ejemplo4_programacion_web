package mx.ipn.upiicsa.web.ejemplo03.contacts.ctrl;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.MedioContacto;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation.MedioContactoBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts/{idContacto}/medios")
public class MediosCtrl {

    @Autowired
    private MedioContactoBs medioContactoBs;

    @GetMapping
    public List<MedioContacto> getMedios(@PathVariable("idContacto") Integer idContacto) {
        return medioContactoBs.getMediosByContacto(idContacto);
    }

    @PostMapping
    public ResponseEntity<MedioContacto> save(@PathVariable("idContacto") Integer idContacto, @RequestBody MedioContacto medio) {
        medio.setIdContacto(idContacto);
        MedioContacto saved = medioContactoBs.saveMedioContacto(medio);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{idMedio}")
    public ResponseEntity<MedioContacto> getMedio(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio) {
        MedioContacto medio = medioContactoBs.getMedioById(idMedio);
        if (medio == null || !medio.getIdContacto().equals(idContacto)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medio);
    }

    @PutMapping("/{idMedio}")
    public ResponseEntity<MedioContacto> update(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio, @RequestBody MedioContacto medioForm) {
        MedioContacto medio = medioContactoBs.getMedioById(idMedio);
        if (medio != null && medio.getIdContacto().equals(idContacto)) {
            medio.setIdTipoContacto(medioForm.getIdTipoContacto());
            medio.setValor(medioForm.getValor());
            MedioContacto updated = medioContactoBs.saveMedioContacto(medio);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idMedio}")
    public ResponseEntity<Void> delete(@PathVariable("idContacto") Integer idContacto, @PathVariable("idMedio") Integer idMedio) {
        MedioContacto medio = medioContactoBs.getMedioById(idMedio);
        if(medio != null && medio.getIdContacto().equals(idContacto)) {
            medioContactoBs.deleteMedio(idMedio);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
