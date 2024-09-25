package nl.han.oose.project.sb3.dao;

import lombok.RequiredArgsConstructor;
import nl.han.oose.project.sb3.entities.Role;
import nl.han.oose.project.sb3.entities.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserDao
{
    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_SQL = "INSERT INTO _user (id, first_name, last_name, email, password, role) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM _user WHERE email = ?";

    public void save(User user)
    {
        String uuid = UUID.randomUUID().toString();

        jdbcTemplate.update(SAVE_SQL, uuid, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getRole().toString());

        System.out.println("Users currently in H2 database: " + findUsers());
    }

    public Optional<User> findByEmail(String email)
    {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_EMAIL_SQL, new UserRowMapper(), email));
    }

    public List<User> findUsers()
    {
        return jdbcTemplate.query("SELECT * FROM _user", new UserRowMapper());
    }
}
