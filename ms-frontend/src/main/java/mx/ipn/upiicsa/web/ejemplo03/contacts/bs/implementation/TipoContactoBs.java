package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.TipoContacto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TipoContactoBs {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8082/api/tiposcontacto";

    public List<TipoContacto> getActiveTiposContacto() {
        try {
            TipoContacto[] tipos = restTemplate.getForObject(API_URL, TipoContacto[].class);
            return tipos != null ? Arrays.asList(tipos) : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
