package View.MenuView;

import Controller.Controller;
import View.WindowView.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {


    JLabel title;

    JButton startButton;
    JButton descriptionButton;
    JButton controlButton;
    JButton exitButton;


    public Menu() {
        super("Best Game Ever");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(java.awt.Color.DARK_GRAY);
        this.setResizable(false);
        this.setLayout(null);

        // tray icon
        ImageIcon image = new ImageIcon("Icons/logarlec.png");
        this.setIconImage(image.getImage());

        // custom cursor
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getImage(), new Point(0, 5), "HandCursor");
        this.setCursor(customCursor);

        title = new JLabel();
        title.setText("Logarlec");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(200, 40, 100, 50);
        this.add(title);

        startButton = new JButton("Start");
        startButton.setBounds(200, 100, 100, 50);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        this.add(startButton);

        descriptionButton = new JButton("Description");
        descriptionButton.setBounds(200, 160, 100, 50);
        descriptionButton.setFocusable(false);
        descriptionButton.addActionListener(this);
        this.add(descriptionButton);

        controlButton = new JButton("Control");
        controlButton.setBounds(200, 220, 100, 50);
        controlButton.setFocusable(false);
        controlButton.addActionListener(this);
        this.add(controlButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(200, 280, 100, 50);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        this.add(exitButton);


        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            System.out.println("Start button clicked");
            Controller controller = new Controller();
            MainWindow mainWindow = new MainWindow(controller);
            controller.setMainWindow(mainWindow);

            this.dispose();
        } else if (e.getSource() == descriptionButton) {
            System.out.println("Description button clicked");
            DescriptionWindow descriptionWindow = new DescriptionWindow();
            //this.dispose();
        } else if (e.getSource() == controlButton) {
            System.out.println("Control button clicked");
            ControlWindow controlWindow = new ControlWindow();
            //this.dispose();
        } else if (e.getSource() == exitButton) {
            System.out.println("Exit button clicked");
            this.dispose();
        }
    }
}
