package uts.repository;

import uts.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User Save(User user)throws SQLException ;
    boolean Delete(User user) throws SQLException;
    List<User> View() throws SQLException;
}
