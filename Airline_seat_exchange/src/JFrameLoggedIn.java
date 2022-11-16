import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameLoggedIn extends JFrame {
    private JButton buttonLogOut;
    private JButton buttonChangeEmail;
    private JButton buttonChangePassword;
    private JButton buttonDeleteAccount;
    private JPanel mainPanel;
    private final User user;

    public JFrameLoggedIn(User user) {
        assert user != null;
        this.user = user;

        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JFrameWelcome();
                dispose();
            }
        });

        buttonChangeEmail.addActionListener(new ActionListener() {
            private User user;
            @Override
            public void actionPerformed(ActionEvent e) {
                new JFrameChangeEmail(this.user);
                dispose();
            }
            private ActionListener init(User user) {
                this.user = user;
                return this;
            }
        }.init(this.user));
    }
}
