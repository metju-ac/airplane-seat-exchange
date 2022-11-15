import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

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
                String username = tfUsername.getText();
                String email = tfEmail.getText();
                String password = tfPassword.getText();

                if (Objects.equals(username, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty username!");
                    return;
                }
                if (Objects.equals(email, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty email!");
                    return;
                }
                if (Objects.equals(password, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty password!");
                    return;
                }

                User user = new User(username, password, email);
                int added = user.addToDatabase();
                if (added == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "User added successfully");
                } else if (added == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "User with this username already exists!");
                } else if (added == 2) {
                    JOptionPane.showMessageDialog(new JFrame(), "User with this email already exists!");
                } else if (added == 3) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid email format");
                } else if (added == 4) {
                    JOptionPane.showMessageDialog(new JFrame(), "Password too short!");
                }
            }
        });
    }
}
