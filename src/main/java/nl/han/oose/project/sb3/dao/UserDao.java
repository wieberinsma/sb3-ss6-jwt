package nl.han.oose.project.sb3.dao;

import lombok.RequiredArgsConstructor;
import nl.han.oose.project.sb3.entities.Role;
import nl.han.oose.project.sb3.entities.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserDao implements RowMapper<User>
{
    private final JdbcTemplate jdbcTemplate;

    private static final String SAVE_SQL = "INSERT INTO user (id, first_name, last_name, email, password, role) " +
            "VALUES ?, ?, ?, ?, ?, ?";

    private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?";

    public void save(User user)
    {
        String uuid = UUID.randomUUID().toString();

        jdbcTemplate.update(SAVE_SQL, uuid, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), user.getRole());
    }

    public Optional<User> findByEmail(String email)
    {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_EMAIL_SQL, new Object[]{email},
                new int[]{Types.VARCHAR}, User.class));
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        User user = null;

        while (rs.next())
        {
            user = new User();
            user.setId(rs.getString("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setRole(Enum.valueOf(Role.class, rs.getString("role")));
        }

        return user;
    }
}
