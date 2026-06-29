package mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserBs {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8081/api/users";
    private final String REG_URL = "http://localhost:8081/api/register";

    public User create(User user) {
        return restTemplate.postForObject(REG_URL, user, User.class);
    }

    public List<User> findAll() {
        try {
            User[] users = restTemplate.getForObject(API_URL, User[].class);
            return users != null ? Arrays.asList(users) : Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Optional<User> findById(Integer idUsuario) {
        try {
            User user = restTemplate.getForObject(API_URL + "/" + idUsuario, User.class);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public User update(User user) {
        restTemplate.put(API_URL + "/" + user.getId(), user);
        return user;
    }

    public Boolean delete(Integer idUsuario) {
        try {
            restTemplate.delete(API_URL + "/" + idUsuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
