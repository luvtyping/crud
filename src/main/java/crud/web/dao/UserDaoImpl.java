package crud.web.dao;

import crud.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
//    private static final String URL = "jdbc:mysql://localhost/users";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "RI43Jkf038fve";
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getUsers() {
//        List<User> users = new ArrayList<>();
//        String SQL = "SELECT * FROM users";
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(SQL)) {
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setName(resultSet.getString("name"));
//                user.setSurname(resultSet.getString("surname"));
//                user.setEmail(resultSet.getString("email"));
//                user.setAge(resultSet.getInt("age"));
//                users.add(user);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return users;
//    }
//
//    public User getUser(int id) {
//        User user = new User();
//        String SQL = "SELECT * FROM users WHERE id=" + id;
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            resultSet.next();
//            user.setId(resultSet.getInt("id"));
//            user.setName(resultSet.getString("name"));
//            user.setSurname(resultSet.getString("surname"));
//            user.setAge(resultSet.getInt("age"));
//            user.setEmail(resultSet.getString("email"));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return user;
//    }
//
//    public void save(User user) {
//        String SQL = "INSERT INTO users(name,surname,age,email) VALUES(?, ?, ?, ?)";
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getSurname());
//            preparedStatement.setInt(3, user.getAge());
//            preparedStatement.setString(4, user.getEmail());
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public void update(int id, User user) {
//        String SQL = "UPDATE users SET name=?, surname=?, age=?, email=? WHERE id=?";
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getSurname());
//            preparedStatement.setInt(3, user.getAge());
//            preparedStatement.setString(4, user.getEmail());
//            preparedStatement.setInt(5, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public void delete(int id) {
//        String SQL = "DELETE FROM users WHERE id=?";
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//            preparedStatement.setInt(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUser(int id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new BeanPropertyRowMapper<>(User.class), id)
                .stream().findFirst().orElse(new User());
    }

    @Override
    public boolean save(User user) {
        int updatedLines = jdbcTemplate.update("INSERT INTO users(name,surname,age,email) VALUES(?, ?, ?, ?)",
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail());
        return updatedLines != 0;
    }

    @Override
    public boolean update(int id, User user) {
        int updatedLines = jdbcTemplate.update("UPDATE users SET name=?, surname=?, age=?, email=? WHERE id=?",
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                id);
        return updatedLines != 0;
    }

    @Override
    public boolean delete(int id) {
        int updatedLInes = jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
        return updatedLInes != 0;
    }
}
