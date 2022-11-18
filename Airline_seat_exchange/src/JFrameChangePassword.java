import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameChangePassword extends JFrame{
    private JPasswordField pfNewPassword;
    private JPasswordField pfOldPassword;
    private JButton buttonBack;
    private JButton buttonConfirm;
    private JPanel mainPanel;

    public JFrameChangePassword(User user) {
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
                char[] oldPassword = pfOldPassword.getPassword();
                char[] newPassword = pfNewPassword.getPassword();

                if (oldPassword.length == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty old password!");
                    return;
                }
                if (newPassword.length == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty new password!");
                    return;
                }

                int changed = this.user.changePassword(oldPassword, newPassword);
                if (changed == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Changed successfully");
                } else if (changed == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong password!");
                } else if (changed == 2) {
                    JOptionPane.showMessageDialog(new JFrame(), "New password is too short!");
                }
            }
            private ActionListener init(User user) {
                this.user = user;
                return this;
            }
        }.init(user));
    }
}
