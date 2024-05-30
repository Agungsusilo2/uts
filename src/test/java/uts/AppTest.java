package uts;

import static org.junit.Assert.assertTrue;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;
import uts.model.User;
import uts.repository.UserRepository;
import uts.repository.UserRepositoryImpl;
import uts.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void testConnection() throws SQLException {
        Connection connection = DatabaseUtil.gDataSource().getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testSave() throws SQLException{
        HikariDataSource dataSource = DatabaseUtil.gDataSource();
        UserRepositoryImpl userRepository = new UserRepositoryImpl(dataSource);
        User save = userRepository.Save(new User(1213, "joko", "2D", "informatics"));
        Assert.assertNotNull(save);
    }

    @Test
    public void testView() throws SQLException{
        HikariDataSource connection = DatabaseUtil.gDataSource();
        UserRepository userRepository = new UserRepositoryImpl(connection);
        List<User> view = userRepository.View();
        Assert.assertNotNull(view);
    }

    @Test
    public void testDelete() throws SQLException{
        HikariDataSource connection = DatabaseUtil.gDataSource();
        UserRepository userRepository = new UserRepositoryImpl(connection);
        User user = new User();
        user.setId(1213);
        boolean view = userRepository.Delete(user);
        Assert.assertTrue(view);
    }
}
