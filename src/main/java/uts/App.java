package uts;

import uts.repository.UserRepository;
import uts.repository.UserRepositoryImpl;
import uts.service.UserServiceImpl;
import uts.util.DatabaseUtil;
import uts.view.UserView;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl(DatabaseUtil.gDataSource());
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        SwingUtilities.invokeLater(() -> {
            UserView userView = new UserView(userService);
            userView.setVisible(true);
        });
    }
}
