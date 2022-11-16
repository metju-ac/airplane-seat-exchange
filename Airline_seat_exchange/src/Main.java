import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        AirportManager am = new AirportManager();
        ArrayList<Airport> airports = am.getAirports(3);
        for (Airport airport : airports) {
            System.out.println(airport);
        }

        JFrame jFrame = new JFrameWelcome();
        jFrame.setVisible(true);
    }
}

//TODO change getText to getPassword in all password fields