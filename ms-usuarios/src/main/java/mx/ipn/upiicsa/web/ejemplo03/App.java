package mx.ipn.upiicsa.web.ejemplo03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 👈 ¡Esta anotación es obligatoria!
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

