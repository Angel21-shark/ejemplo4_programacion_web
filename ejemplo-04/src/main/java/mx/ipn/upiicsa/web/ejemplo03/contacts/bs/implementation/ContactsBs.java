package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao.ContacsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ContactsBs {
    @Autowired
    ContacsDao contacsDao;

    public List<Contact> getContacts(Integer idUser) {
        return contacsDao.findAll(idUser);
    }

    public Contact saveContact(Contact contact) {
        return contacsDao.save(contact);
    }

    public Contact getContactById(Integer idContacto) {
        return contacsDao.findById(idContacto).orElse(null);
    }

    public void deleteContact(Integer idContacto) {
        contacsDao.delete(idContacto);
    }
}
