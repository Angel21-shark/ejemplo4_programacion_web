package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.MedioContacto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MedioContactoBs {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8082/api/contacts";

    public List<MedioContacto> getMediosByContacto(Integer idContacto) {
        try {
            MedioContacto[] medios = restTemplate.getForObject(API_URL + "/" + idContacto + "/medios", MedioContacto[].class);
            return medios != null ? Arrays.asList(medios) : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public MedioContacto saveMedioContacto(MedioContacto medioContacto) {
        try {
            if (medioContacto.getId() != null) {
                restTemplate.put(API_URL + "/" + medioContacto.getIdContacto() + "/medios/" + medioContacto.getId(), medioContacto);
                return medioContacto;
            } else {
                return restTemplate.postForObject(API_URL + "/" + medioContacto.getIdContacto() + "/medios", medioContacto, MedioContacto.class);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public MedioContacto getMedioById(Integer idContacto, Integer idMedioContacto) {
        try {
            return restTemplate.getForObject(API_URL + "/" + idContacto + "/medios/" + idMedioContacto, MedioContacto.class);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteMedio(Integer idContacto, Integer idMedioContacto) {
        try {
            restTemplate.delete(API_URL + "/" + idContacto + "/medios/" + idMedioContacto);
        } catch (Exception e) {}
    }
}
