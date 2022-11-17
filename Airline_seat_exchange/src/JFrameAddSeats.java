import javax.swing.*;

public class JFrameAddSeats extends JFrame {
    private JPanel mainPanel;
    private JLabel labelFlight;

    public JFrameAddSeats(User user, Flight flight) {
        assert user != null;
        assert flight != null;

        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        labelFlight.setText(flight.getFlightCode());
    }
}
