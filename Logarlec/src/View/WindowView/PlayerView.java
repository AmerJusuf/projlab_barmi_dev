package  View.WindowView;

import Characters.Character;
import  View.ItemView.*;

import javax.swing.*;
import java.awt.*;

public class PlayerView {

    Character character;
    JPanel panel = new JPanel();
    JLabel iconLabel;
    JLabel title;

    FFP2View ffp2View = new FFP2View(false);
    AirFreshenerView airFreshenerView = new AirFreshenerView();
    BeerView beerView = new BeerView();
    CamembertView camembertView = new CamembertView();
    RagView ragView = new RagView();
    TVSZView tvszView = new TVSZView(false);
    TransistorView transistorView = new TransistorView();

    final ImageIcon STUDENT_ICON = new ImageIcon("Icons/student.png");
    final ImageIcon INSTRUCTOR_ICON = new ImageIcon("Icons/instructor.png");
    final ImageIcon CLEANER_ICON = new ImageIcon("Icons/cleaner.png");

    public PlayerView(Character ch)
    {
        character = ch;
        setLabel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        title = new JLabel("asd");
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
