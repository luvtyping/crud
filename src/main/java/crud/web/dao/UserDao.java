package crud.web.dao;

import crud.web.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    User getUser(int id);

    boolean save(User user);

    boolean update(int id, User user);

    boolean delete(int id);
}
