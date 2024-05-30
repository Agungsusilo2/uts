package uts.view;

import uts.model.User;
import uts.repository.UserRepository;
import uts.repository.UserRepositoryImpl;
import uts.service.UserService;
import uts.service.UserServiceImpl;
import uts.util.DatabaseUtil;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UserView extends JFrame {
    private UserService userService;
    private JTextField nameField;
    private JTextField classNameField;
    private JTextField sectionField;
    private JTable userTable;
    private DefaultTableModel tableModel;

    public UserView(UserService userService) {
        this.userService = userService;

        setTitle("User Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Class Name:"));
        classNameField = new JTextField();
        formPanel.add(classNameField);

        formPanel.add(new JLabel("Section:"));
        sectionField = new JTextField();
        formPanel.add(sectionField);

        JButton saveButton = new JButton("Save");
        formPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Class Name", "Section"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);


        loadUserData();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveUser();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton, BorderLayout.SOUTH);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteUser();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void loadUserData() {
        try {
            List<User> users = userService.viewAllUsers();
            tableModel.setRowCount(0);
            for (User user : users) {
                tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getClassName(), user.getSection()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveUser() throws SQLException {
        String name = nameField.getText();
        String className = classNameField.getText();
        String section = sectionField.getText();

        User user = new User(name, className, section);
        userService.saveUser(user);
        loadUserData();
    }

    private void deleteUser() throws SQLException {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            int userId = (int) userTable.getValueAt(selectedRow, 0);
            User user = new User(userId, "", "", "");
            userService.deleteUser(user);
            loadUserData();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete");
        }
    }

    public static void main(String[] args) {


        UserRepository userRepository = new UserRepositoryImpl(DatabaseUtil.gDataSource());
        UserService userService = new UserServiceImpl(userRepository);

        SwingUtilities.invokeLater(() -> {
            UserView userView = new UserView(userService);
            userView.setVisible(true);
        });

    }
}