import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JFrameAddTicket extends JFrame {
    private JComboBox cbCountryFrom;
    private JComboBox cbAirportFrom;
    private JComboBox cbCountryTo;
    private JComboBox cbAirportTo;
    private JButton buttonBack;
    private JPanel mainPanel;

    public JFrameAddTicket(User user) {
        assert user != null;

        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        cbCountryFrom.addItem("First");
        cbCountryFrom.addItem("Second");

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

        cbCountryFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbAirportFrom.addItem("One");
                cbAirportFrom.addItem("Two");
                cbAirportFrom.addItem("Three");
            }
        });
        cbCountryFrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });
    }
}
