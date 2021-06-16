package crud.web.dao;

import crud.web.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql({"classpath:dbSchema.sql", "classpath:data.sql"})
class UserDaoImplTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
        userDao.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    void getUsers() {
        Assertions.assertEquals(4, userDao.getUsers().size());
        Assertions.assertEquals("Robert", userDao.getUsers().get(2).getName());
    }

    @Test
    void getUser() {
        Assertions.assertEquals("Joshua", userDao.getUser(1).getName());
        Assertions.assertEquals("Herbert", userDao.getUser(2).getName());
        Assertions.assertEquals("Robert", userDao.getUser(3).getName());
        Assertions.assertEquals("Bill", userDao.getUser(4).getName());
    }

    @Test
    void save() {
        User newUser = new User(8, "TestName", "TestSurname", 22, "nameOne@mail.ru");
        Assertions.assertTrue(userDao.save(newUser));
        Assertions.assertEquals(5, userDao.getUsers().size());
    }

    @Test
    void update() {
        User userToUpdate = userDao.getUser(2);
        userToUpdate.setAge(88);
        userToUpdate.setName("Boris");

        userDao.update(1, userToUpdate);
        Assertions.assertEquals("Boris", userDao.getUser(1).getName());
        Assertions.assertEquals(88, userDao.getUser(1).getAge());
    }

    @Test
    void delete() {
        Assertions.assertTrue(userDao.delete(1));
        Assertions.assertNull(userDao.getUser(1).getName());
        Assertions.assertFalse(userDao.delete(-99999));
    }
}