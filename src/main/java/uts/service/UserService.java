package uts.service;

import uts.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User saveUser(User user) throws SQLException;
    boolean deleteUser(User user) throws SQLException;
    List<User> viewAllUsers() throws SQLException;
}
