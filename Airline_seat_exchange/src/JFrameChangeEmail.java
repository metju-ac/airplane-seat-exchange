import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class JFrameChangeEmail extends JFrame {
    private JPanel mainPanel;
    private JTextField tfOldEmail;
    private JTextField tfNewEmail;
    private JPasswordField pfPassword;
    private JButton buttonBack;
    private JButton buttonConfirm;

    public JFrameChangeEmail(User user) {
        assert user != null;

        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buttonBack.addActionListener(new ActionListener() {
            private User user;
            @Override
            public void actionPerformed(ActionEvent e) {
                new JFrameLoggedIn(this.user);
                dispose();
            }
            private ActionListener init(User user) {
                this.user = user;
                return this;
            }
        }.init(user));

        buttonConfirm.addActionListener(new ActionListener() {
            private User user;
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldEmail = tfOldEmail.getText();
                String newEmail = tfNewEmail.getText();
                char[] password = pfPassword.getPassword();

                if (Objects.equals(oldEmail, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty old email!");
                    return;
                }
                if (Objects.equals(newEmail, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty new email!");
                    return;
                }
                if (Objects.equals(password, "")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty password!");
                    return;
                }

                int changed = this.user.changeEmail(oldEmail, newEmail, password);
                if (changed == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Changed successfully");
                } else if (changed == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong old email!");
                } else if (changed == 2) {
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong password!");
                } else if (changed == 3) {
                    JOptionPane.showMessageDialog(new JFrame(), "Invalid new email!");
                } else if (changed == 4) {
                    JOptionPane.showMessageDialog(new JFrame(), "This email is already registered!");
                }
            }
            private ActionListener init(User user) {
                this.user = user;
                return this;
            }
        }.init(user));
    }
}
