import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CountryManager cm = new CountryManager();
        cm.getCountries();
        JFrame jFrame = new JFrameWelcome();
        jFrame.setVisible(true);
    }
}

//TODO change getText to getPassword in all password fields