package mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserBs {
    @Autowired
    UserDao userDao;

    @Transactional(rollbackOn = Exception.class)
    public void create(User user){
        log.info("Creando usuario: {}",user.getId());
        var userSaved = userDao.save(user);
        log.info("Usuario guardado: {}",userSaved.getId());
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Integer idUsuario) {
        return userDao.findById(idUsuario);
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean update(User user) {
        var userResult = userDao.findById(user.getId());
        if(userResult.isPresent()){
            var userSaved = userResult.get();
            userSaved.setName(user.getName());
            userSaved.setLastName(user.getLastName());
            userSaved.setSecondLastName(user.getSecondLastName());
            userSaved.setUsername(user.getUsername());
            log.info("Usuario BS: {}",userSaved.getPassword());
            userDao.save(userSaved);
            return true;
        }
        return false;
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean delete(Integer idUsuario) {
        Optional<User> userResult = userDao.findById(idUsuario);
        if(userResult.isPresent()){
            userDao.delete(userResult.get());
            return true;
        }
        return false;
    }
}
