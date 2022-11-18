import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameDeleteAccount extends JFrame{
    private JPasswordField pfPassword;
    private JButton buttonBack;
    private JButton buttonConfirm;
    private JPanel mainPanel;

    public JFrameDeleteAccount(User user) {
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
                char[] password = pfPassword.getPassword();
                if (password.length == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Empty password!");
                    return;
                }

                int deleted = this.user.deleteAccount(password);
                System.out.println(deleted);
                if (deleted == 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "Deleted successfully");
                    new JFrameWelcome();
                    dispose();
                } else if (deleted == 1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Wrong password");
                }
            }
            private ActionListener init(User user) {
                this.user = user;
                return this;
            }
        }.init(user));
    }
}
