package mx.ipn.upiicsa.web.ejemplo03.contacts.bs.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.contacts.bs.entity.Contact;
import mx.ipn.upiicsa.web.ejemplo03.contacts.persistence.dao.ContacsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de negocio dedicado a procesar y manejar Contactos.
 * Maneja las reglas y sirve de puente entre los controladores y el DAO.
 */
@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ContactsBs {
    @Autowired
    ContacsDao contacsDao;

    /**
     * Devuelve un listado completo de los contactos que pertenecen a un usuario.
     *
     * @param idUser el identificador del usuario propietario de los contactos
     * @return lista de contactos pertenecientes al usuario
     */
    public List<Contact> getContacts(Integer idUser) {
        return contacsDao.findAll(idUser);
    }

    /**
     * Inserta un contacto nuevo o sobrescribe uno existente.
     *
     * @param contact la información del contacto a guardar
     * @return el contacto guardado y actualizado
     */
    public Contact saveContact(Contact contact) {
        return contacsDao.save(contact);
    }

    /**
     * Busca un contacto específico a través de su identificador.
     *
     * @param idContacto el identificador único del contacto
     * @return el contacto si se encuentra, o null si no existe
     */
    public Contact getContactById(Integer idContacto) {
        return contacsDao.findById(idContacto).orElse(null);
    }

    /**
     * Elimina un contacto de forma permanente.
     *
     * @param idContacto el identificador del contacto a eliminar
     */
    public void deleteContact(Integer idContacto) {
        contacsDao.delete(idContacto);
    }
}
