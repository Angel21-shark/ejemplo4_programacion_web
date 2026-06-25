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

@Slf4j
@Repository
public class UserDao {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    EntityManager entityManager;

    private static final String QUERY_FIND_ALL = "select * from usuario";
    private static final String QUERY_DELETE_BY_ID = "delete from usuario where id_usuario = :id";

    public User save(User user) {
        log.info("Usuario DAO: {}",user.getPassword());
        var userSavedJpa = userJpaRepository.save(UserJpa.fromEntity(user));
        return userSavedJpa.toEntity();
    }

    public List<User> findAll() {
        //var usuariosJpa = userJpaRepository.findAll();
        //List<User> users = new ArrayList<>();
        //for(UserJpa usuarioJpa: usuariosJpa) {
        //    users.add(usuarioJpa.toEntity());
        //}
        //return users;
        //return userJpaRepository
        //        .findAll()
        //        .stream()
        //        .map(UserJpa::toEntity)
        //        .toList();
        //Query query = entityManager.createNativeQuery(QUERY_FIND_ALL);
        //List<UserJpa> usersJpa = query.getResultList();
        //return usersJpa.stream().map(UserJpa::toEntity).toList();
        //Query query = entityManager.createNativeQuery(QUERY_FIND_ALL);
        //List<Object[]> resultados = query.getResultList();
        //return resultados.stream().map(row -> User.builder()
        //            .id((Integer) row[0])
        //            .name((String) row[1])
        //            .lastName((String) row[2])
        //            .secondLastName((String) row[3])
        //            .username((String) row[4])
        //            .password((String) row[5])
        //            .build()
        //).toList();
        Query query = entityManager.createNativeQuery(QUERY_FIND_ALL, Tuple.class);
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

    public Optional<User> findById(Integer idUsuario) {
        return userJpaRepository.findById(idUsuario).map(UserJpa::toEntity);
    }

    public void delete(User user) {
        //userJpaRepository.delete(UserJpa.fromEntity(user));
        //userJpaRepository.deleteById(user.getId());
        //Query query = entityManager.createQuery("delete from UserJpa u where u.id = :id");
        Query query = entityManager.createNativeQuery(QUERY_DELETE_BY_ID);
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }
}
