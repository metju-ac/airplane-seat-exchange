import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JFrameLogin extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton buttonLogin;
    private JButton buttonBack;
    private JPanel mainPanel;

    public JFrameLogin() {
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

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                char[] password = pfPassword.getPassword();

                if (Objects.equals(username, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty username!");
                    return;
                }
                if (password.length == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty password!");
                    return;
                }

                User user = new User(username);
                int logged = user.Login(password);

                if (logged == 0) {
                    new JFrameLoggedIn(user);
                    dispose();
                } else if (logged == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "No user with this username exists!");
                } else if (logged == 2) {
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong password!");
                }
            }
        });
    }
}
