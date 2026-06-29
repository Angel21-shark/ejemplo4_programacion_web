package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ContactsBs {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8082/api/contacts";

    public List<Contact> getContacts(Integer idUser) {
        try {
            Contact[] contacts = restTemplate.getForObject(API_URL + "/user/" + idUser, Contact[].class);
            return contacts != null ? Arrays.asList(contacts) : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Contact saveContact(Contact contact) {
        return restTemplate.postForObject(API_URL, contact, Contact.class);
    }

    public Contact getContactById(Integer idContacto) {
        try {
            return restTemplate.getForObject(API_URL + "/" + idContacto, Contact.class);
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteContact(Integer idContacto) {
        try {
            restTemplate.delete(API_URL + "/" + idContacto);
        } catch (Exception e) {}
    }
}
