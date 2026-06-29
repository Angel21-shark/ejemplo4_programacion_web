package mx.ipn.upiicsa.web.ejemplo03.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class AuthenticationFilter extends HttpFilter {

    // 1. Definimos las rutas públicas que NO requieren sesión
    private final List<String> rutasPublicas = List.of(
            "/",                      // Tu pantalla de login actual (POST y GET)
            "/register",                // Pantalla de registro
            "/register/new",           // Operación de registro
            "/recuperar-contrasena",  // Recuperación
            "/confirmar-contrasena",  // Confirmación
            "/logout"                 // Ruta de salida
    );

    // 2. Definimos los prefijos de recursos estáticos permitidos
    private final List<String> recursosEstaticos = List.of(
            "/css/", "/js/", "/images/", "/favicon.ico"
    );

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String uri = request.getRequestURI();

        // 3. Verificar si la URI actual es un recurso público o estático
        boolean esPublico = rutasPublicas.contains(uri) ||
                recursosEstaticos.stream().anyMatch(uri::startsWith);

        if (esPublico) {
            // Si es público, lo dejamos pasar sin validar sesión
            chain.doFilter(request, response);
            return;
        }

        // 4. Validar la sesión para rutas protegidas
        HttpSession session = request.getSession(false); // false = no crea una sesión nueva si no existe

        if (session != null && session.getAttribute("usuarioSesion") != null) {
            // Hay una sesión válida y el usuario está guardado, puede continuar
            log.info("Usuario autenticado: {}", uri);
            if(uri.equals("/")) {
                response.sendRedirect(request.getContextPath() + "/welcome");
            }
            chain.doFilter(request, response);
        } else {
            // No está autenticado: redirige a la raíz (pantalla de login)
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
