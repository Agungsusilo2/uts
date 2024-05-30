package uts.service;

import uts.model.User;
import uts.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws SQLException {
        int randomId;
        do {
            randomId = (int) (Math.random() * Integer.MAX_VALUE);
        } while (isIdExists(randomId));
        user.setId(randomId);
        return userRepository.Save(user);
    }

    private boolean isIdExists(int id) throws SQLException {
        List<User> users = userRepository.View();
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean deleteUser(User user) throws SQLException {
        return userRepository.Delete(user);
    }

    @Override
    public List<User> viewAllUsers() throws SQLException {
        return userRepository.View();
    }
}
