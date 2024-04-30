package  View.WindowView;

import  View.ItemView.*;

import javax.swing.*;
import java.awt.*;

public class PlayerView {
    JPanel panel = new JPanel();

    JLabel title;

    FFP2View ffp2View = new FFP2View(false);
    AirFreshenerView airFreshenerView = new AirFreshenerView();
    BeerView beerView = new BeerView();
    CamembertView camembertView = new CamembertView();
    RagView ragView = new RagView();
    TVSZView tvszView = new TVSZView(false);
    TransistorView transistorView = new TransistorView();

    public PlayerView(String curTitle)
    {
        //panel.setPreferredSize(new Dimension(200, 360));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        title = new JLabel(curTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVisible(true);
        panel.add(title);


        //panel.add(ffp2View.panel);
        panel.add(airFreshenerView.getPanel());
        panel.add(beerView.getPanel());
        panel.add(camembertView.getPanel());
        panel.add(ragView.getPanel());
        //panel.add(tvszView.panel);
        panel.add(transistorView.getPanel());



        panel.setVisible(true);
    }
}
