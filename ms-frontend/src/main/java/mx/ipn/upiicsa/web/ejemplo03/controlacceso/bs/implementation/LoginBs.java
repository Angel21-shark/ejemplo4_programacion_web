package mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.implementation;

import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.ctrl.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LoginBs {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://localhost:8081/api/auth/login";

    public Optional<User> login(String username, String password) {
        try {
            LoginDto request = LoginDto.builder().username(username).password(password).build();
            ResponseEntity<User> response = restTemplate.postForEntity(API_URL, request, User.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.ofNullable(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("Error calling MS-Usuarios: " + e.getMessage());
        }
        return Optional.empty();
    }
}
