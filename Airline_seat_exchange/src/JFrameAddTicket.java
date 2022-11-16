import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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

        CountryManager countryManager = new CountryManager();
        AirportManager airportManager = new AirportManager();

        ArrayList<Country> countries = countryManager.getCountries();
        for (Country country : countries) {
            cbCountryFrom.addItem(country);
            cbCountryTo.addItem(country);
        }

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
                Country selected = (Country) cbCountryFrom.getSelectedItem();
                ArrayList<Airport> airportsFrom = airportManager.getAirports(selected);
                cbAirportFrom.removeAllItems();
                for (Airport airport : airportsFrom) {
                    cbAirportFrom.addItem(airport);
                }
            }
        });

        cbCountryTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Country selected = (Country) cbCountryTo.getSelectedItem();
                ArrayList<Airport> airportsFrom = airportManager.getAirports(selected);
                cbAirportTo.removeAllItems();
                for (Airport airport : airportsFrom) {
                    cbAirportTo.addItem(airport);
                }
            }
        });

    }
}
