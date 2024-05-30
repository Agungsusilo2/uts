package uts.repository;

import com.zaxxer.hikari.HikariDataSource;
import uts.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private final HikariDataSource hikariDataSource;

    public UserRepositoryImpl(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @Override
    public User Save(User user) throws SQLException {
        try(Connection connection = hikariDataSource.getConnection()) {
            String sql = "INSERT INTO userrecords (id, name, class_name, section) VALUES (?, ?, ?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getClassName());
                preparedStatement.setString(4, user.getSection());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return user;
                }
            }
            return null;
        }
    }


    @Override
    public boolean Delete(User user) throws SQLException {
        try(Connection connection = hikariDataSource.getConnection()){
            String sql = "DELETE FROM userrecords WHERE id = ?";
            try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
                prepareStatement.setInt(1, user.getId());
                int affectedRows = prepareStatement.executeUpdate();
                return affectedRows > 0;
            }
        }
    }

    @Override
    public List<User> View() throws SQLException {
        List<User> users = new ArrayList<>();
        try(Connection connection = hikariDataSource.getConnection()){
            String sql = "SELECT id, name, class_name, section FROM userrecords";
            try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
                try(ResultSet resultSet = prepareStatement.executeQuery()){
                    while(resultSet.next()){
                        User user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("class_name"),
                                resultSet.getString("section")
                        );
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }
}
