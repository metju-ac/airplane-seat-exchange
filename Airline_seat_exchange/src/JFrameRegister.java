import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JFrameRegister extends JFrame {
    private JPanel registerPanel;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfEmail;
    private JButton buttonRegister;
    private JButton buttonBack;
    private JPanel mainPanel;

    public JFrameRegister() {
        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JFrameWelcome();
                dispose();
            }
        });

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uusername = tfUsername.getText();
                String email = tfEmail.getText();
                String ppassword = tfPassword.getText();
//                JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.");

                String dbURL = "jdbc:mysql://localhost:3306/airplane_seat_exchange";
                String username = "root";
                String password = "Rootroot";


                try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
                    System.out.println(456);
                    String sql = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";

                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, uusername);
                    statement.setString(2, ppassword);
                    statement.setString(3, email);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new user was inserted successfully!");
                    }

                } catch (SQLException exception) {
                    System.out.println(exception);;
                }

            }
        });
    }
}
