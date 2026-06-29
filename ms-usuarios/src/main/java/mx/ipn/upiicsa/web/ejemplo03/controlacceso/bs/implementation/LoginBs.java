package mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoginBs {
    @Autowired
    LoginDao loginDao;

    public Optional<User> login(String username, String password){
        log.info("LoginBs.login()");
        return loginDao.login(username, password);
    }
}
