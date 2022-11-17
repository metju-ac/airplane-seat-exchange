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
    private JComboBox cbFlight;

    public JFrameAddTicket(User user) {
        assert user != null;

        setContentPane(mainPanel);
        setTitle("Airplane seat exchange");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        CountryManager countryManager = new CountryManager();
        AirportManager airportManager = new AirportManager();
        FlightManager flightManager = new FlightManager();

        ArrayList<Country> countries = countryManager.getCountries();
        for (Country country : countries) {
            cbCountryFrom.addItem(country);
            cbCountryTo.addItem(country);
        }

        final Airport[] airportsSelected = new Airport[2]; //0 = from, 1 = to

        ItemListener itemListenerAirportFrom = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                airportsSelected[0] = (Airport) cbAirportFrom.getSelectedItem();
                if (airportsSelected[1] == null) {
                    return;
                }
                ArrayList<Flight> flights = flightManager.getFlights(airportsSelected[0], airportsSelected[1]);
                cbFlight.removeAllItems();
                for (Flight flight : flights) {
                    cbFlight.addItem(flight);
                }
            }
        };

        cbAirportFrom.setSelectedIndex(-1);
        cbAirportFrom.addItemListener(itemListenerAirportFrom);

        ItemListener itemListenerAirportTo = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                airportsSelected[1] = (Airport) cbAirportTo.getSelectedItem();
                if (airportsSelected[0] == null) {
                    return;
                }
                ArrayList<Flight> flights = flightManager.getFlights(airportsSelected[0], airportsSelected[1]);
                cbFlight.removeAllItems();
                for (Flight flight : flights) {
                    cbFlight.addItem(flight);
                }
            }
        };

        cbAirportTo.setSelectedIndex(-1);
        cbAirportTo.addItemListener(itemListenerAirportTo);

        ItemListener itemListenerCountryFrom = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Country selected = (Country) cbCountryFrom.getSelectedItem();
                ArrayList<Airport> airportsFrom = airportManager.getAirports(selected);
                cbAirportFrom.removeItemListener(itemListenerAirportFrom);
                cbAirportFrom.removeAllItems();
                for (Airport airport : airportsFrom) {
                    cbAirportFrom.addItem(airport);
                }
                cbAirportFrom.setSelectedIndex(-1);
                cbAirportFrom.addItemListener(itemListenerAirportFrom);
                cbFlight.removeAllItems();
            }
        };

        cbCountryFrom.setSelectedIndex(-1);
        cbCountryFrom.addItemListener(itemListenerCountryFrom);

        ItemListener itemListenerCountryTo = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Country selected = (Country) cbCountryTo.getSelectedItem();
                ArrayList<Airport> airportsFrom = airportManager.getAirports(selected);
                cbAirportTo.removeItemListener(itemListenerAirportTo);
                cbAirportTo.removeAllItems();
                for (Airport airport : airportsFrom) {
                    cbAirportTo.addItem(airport);
                }
                cbAirportTo.setSelectedIndex(-1);
                cbAirportTo.addItemListener(itemListenerAirportTo);
                cbFlight.removeAllItems();
            }
        };

        cbCountryTo.setSelectedIndex(-1);
        cbCountryTo.addItemListener(itemListenerCountryTo);

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
    }
}
