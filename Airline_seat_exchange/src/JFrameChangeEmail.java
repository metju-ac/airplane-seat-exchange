import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameChangeEmail extends JFrame {
    private JPanel mainPanel;
    private JTextField tfOldEmail;
    private JTextField tfNewEmail;
    private JPasswordField pwPassword;
    private JButton buttonBack;
    private JButton buttonConfirm;
    private final User user;

    public JFrameChangeEmail(User user) {
        assert user != null;
        this.user = user;

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
        }.init(this.user));
    }
}
