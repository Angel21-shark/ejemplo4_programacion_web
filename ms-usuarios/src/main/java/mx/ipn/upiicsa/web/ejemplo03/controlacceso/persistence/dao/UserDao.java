package mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.bs.entity.User;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.entity.UserJpa;
import mx.ipn.upiicsa.web.ejemplo03.controlacceso.persistence.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase DAO para manejar la persistencia y consultas de los usuarios.
 * Utiliza Spring Data JPA y EntityManager para consultas nativas.
 */
@Slf4j
@Repository
public class UserDao {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    EntityManager entityManager;

    private static final String QUERY_FIND_ALL = "select * from usuario";
    private static final String QUERY_DELETE_BY_ID = "delete from usuario where id_usuario = :id";

    /**
     * Crea o actualiza un registro de usuario en el sistema.
     * 
     * @param user el usuario a guardar
     * @return el usuario guardado y mapeado de vuelta a entidad de negocio
     */
    public User save(User user) {
        log.info("Usuario DAO: {}",user.getPassword());
        var userSavedJpa = userJpaRepository.save(UserJpa.fromEntity(user));
        return userSavedJpa.toEntity();
    }

    /**
     * Extrae a todos los usuarios ejecutando una sentencia SQL nativa y los mapea
     * utilizando objetos Tuple para evitar problemas de tipos de retorno genéricos.
     *
     * @return lista de usuarios recuperados de la base de datos
     */
    public List<User> findAll() {
        Query query = entityManager.createNativeQuery(QUERY_FIND_ALL, Tuple.class);
        @SuppressWarnings("unchecked")
        List<Tuple> resultados = query.getResultList();
        return resultados.stream().map(tuple -> User.builder()
                    .id(tuple.get("id_usuario",Integer.class))
                    .name(tuple.get("tx_nombre",String.class))
                    .lastName(tuple.get("tx_primer_apellido",String.class))
                    .secondLastName(tuple.get("tx_segundo_apellido",String.class))
                    .username(tuple.get("tx_username",String.class))
                    .password(tuple.get("tx_password",String.class))
                    .build()
        ).toList();
    }

    /**
     * Busca un usuario específico por su ID.
     *
     * @param idUsuario el identificador del usuario
     * @return un Optional conteniendo el usuario si se encuentra
     */
    public Optional<User> findById(Integer idUsuario) {
        return userJpaRepository.findById(idUsuario).map(UserJpa::toEntity);
    }

    /**
     * Elimina un usuario de la base de datos utilizando una consulta nativa.
     *
     * @param user el usuario que se desea eliminar
     */
    public void delete(User user) {
        Query query = entityManager.createNativeQuery(QUERY_DELETE_BY_ID);
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }
}
