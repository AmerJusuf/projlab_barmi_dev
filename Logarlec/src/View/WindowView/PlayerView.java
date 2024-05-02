package  View.WindowView;

import Characters.Character;
import Items.*;
import  View.ItemView.*;

import javax.swing.*;
import java.awt.*;

public class PlayerView {

    Character character;

    AirFreshener airFreshener = new AirFreshener();
    Beer beer = new Beer();
    FFP2 ffp2 = new FFP2(true);
    Camembert camembert = new Camembert();
    Logarlec logarlec = new Logarlec(false);
    Rag rag = new Rag();
    TVSZ tvsz = new TVSZ(false, 3);
    Transistor transistor = new Transistor();


    JPanel panel = new JPanel();
    JLabel iconLabel;
    JLabel title;

    //AirFreshenerView airFreshenerView = new AirFreshenerView(airFreshener);
    BeerView beerView = new BeerView(beer);
    CamembertView camembertView = new CamembertView(camembert);
    FFP2View ffp2View = new FFP2View(ffp2);
    LogarlecView logarlecView = new LogarlecView(logarlec);
    RagView ragView = new RagView(rag);
    TransistorView transistorView = new TransistorView(transistor);
    TVSZView tvszView = new TVSZView(false);


    final ImageIcon STUDENT_ICON = new ImageIcon("Icons/student.png");
    final ImageIcon INSTRUCTOR_ICON = new ImageIcon("Icons/instructor.png");
    final ImageIcon CLEANER_ICON = new ImageIcon("Icons/cleaner.png");

    public PlayerView(Character ch)
    {
        character = ch;
        setLabel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        title = new JLabel("Player 1");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVisible(true);
        panel.add(title);


        panel.add(ffp2View.getPanel());
        //panel.add(airFreshenerView.getPanel());
        panel.add(beerView.getPanel());
        panel.add(camembertView.getPanel());
        //panel.add(ragView.getPanel());
        panel.add(tvszView.getPanel());
        panel.add(transistorView.getPanel());



        panel.setVisible(true);
    }


    private void setLabel(){
        if(character instanceof Characters.Student){
            iconLabel = new JLabel(STUDENT_ICON);
        }
        else if(character instanceof Characters.Instructor){
            iconLabel = new JLabel(INSTRUCTOR_ICON);
        }
        else if(character instanceof Characters.Cleaner){
            iconLabel = new JLabel(CLEANER_ICON);
        }
    }


    public JLabel getIcon(){
        return iconLabel;
    }
}
