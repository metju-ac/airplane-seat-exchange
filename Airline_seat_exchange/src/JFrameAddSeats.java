import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameAddSeats extends JFrame {
    private JPanel mainPanel;
    private JLabel labelFlight;
    private JButton buttonBack;

    public JFrameAddSeats(User user, Flight flight) {
        assert user != null;
        assert flight != null;

        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        labelFlight.setText(flight.getFlightCode());

        buttonBack.addActionListener(new ActionListener() {
            private User user;
            @Override
            public void actionPerformed(ActionEvent e) {
                new JFrameAddTicket(this.user);
                dispose();
            }
            private ActionListener init(User user) {
                this.user = user;
                return this;
            }
        }.init(user));
    }
}
