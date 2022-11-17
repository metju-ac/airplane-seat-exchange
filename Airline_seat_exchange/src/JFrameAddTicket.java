import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        cbAirportFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                airportsSelected[0] = (Airport) cbAirportFrom.getSelectedItem();
                if (airportsSelected[1] == null) {
                    return;
                }
//                System.out.println(123);
                ArrayList<Flight> flights = flightManager.getFlights(airportsSelected[0], airportsSelected[1]);
                cbFlight.removeAllItems();
                for (Flight flight : flights) {
                    cbFlight.addItem(flight);
                }
            }
        });

        cbAirportTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                airportsSelected[1] = (Airport) cbAirportTo.getSelectedItem();
                if (airportsSelected[0] == null) {
                    return;
                }
                System.out.println(123);
                System.out.println(airportsSelected[0]);
                System.out.println(airportsSelected[1]);
                ArrayList<Flight> flights = flightManager.getFlights(airportsSelected[0], airportsSelected[1]);
                cbFlight.removeAllItems();
                for (Flight flight : flights) {
                    cbFlight.addItem(flight);
                }
            }
        });

    }
}
