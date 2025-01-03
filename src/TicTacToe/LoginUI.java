package TicTacToe;
import TicTacToe.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import  java.sql.*;
import java.sql.SQLException;

public class LoginUI {
    private Connection connection;
    String username1 = "";
    String username2 = "";
    boolean player1LoggedIn=false;
    public LoginUI() throws SQLException {
        connection = DataBaseConnection.getConnection();
        JFrame loginFrame = new JFrame("LOG IN");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(600, 600);
        loginFrame.setLocationRelativeTo(null);
    }
}