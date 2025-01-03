package TicTacToe;
import TicTacToe.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import  java.sql.*;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginUI {
    private Connection connection;
    String username1 = "";
    String username2 = "";
    boolean player1LoggedIn=false;
    public LoginUI() throws SQLException {
        connection = DataBaseConnection.getConnection(); //represents the connection to the database.
        // new Login page window
        JFrame loginFrame = new JFrame("LOG IN");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(600, 600);
        loginFrame.setLocationRelativeTo(null);  // This method centers the loginFrame on the screen.

        loginFrame.setBounds(null);  // This method disables the layout manager for the loginFrame.
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(200,100,100,50);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(270,110,100,50);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200,150,100,50);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(270,160,150,30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(280,200,80,30);
        submitButton.setForeground(Color.BLUE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean user = AuthenticateUser(usernameField.getText(), passwordField.getText());  // Checks if username & password matches with database

                    if (user && !player1LoggedIn){
                        username1 = usernameField.getText();
                        player1LoggedIn = true;
                        JOptionPane.showMessageDialog(loginFrame, "Login Successful for " + username1);
                        // This method creates and displays a simple modal dialog box with a message.
                        // It blocks interaction with other windows until the user dismisses it.
                        usernameField.setText("");
                        passwordField.setText("");
                    } else if (user && player1LoggedIn && username2.isEmpty()) { //user is true (the login credentials are valid). player1LoggedIn is true (Player 1 is already logged in). username2 is empty (Player 2 is not logged in yet).
                        username2 = usernameField.getText();
                        JOptionPane.showMessageDialog(loginFrame,"Login Successful for " + username2);
                        loginFrame.setVisible(false);
                    }else {
                        JOptionPane.showMessageDialog(loginFrame, "Invalid Username or Password");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } {

                }
            }
        });

        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordField);
        loginFrame.add(passwordLabel);
        loginFrame.add(submitButton);
        loginFrame.setVisible(true);
    }

    public boolean AuthenticateUser(String username, String password) throws SQLException {
        String query = "SELECT username FROM Users WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();

        return rs.next();  // Checks if username & password matches with database. Returns true if it matches.
    }
}