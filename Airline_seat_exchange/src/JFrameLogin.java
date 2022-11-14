import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }
}
