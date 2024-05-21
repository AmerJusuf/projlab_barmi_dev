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
    private Image backgroundImage;

    public Menu() {
        super("Best Game Ever");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("Icons/gergo.png");

        // Custom panel to handle background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(Color.DARK_GRAY);

        // Tray icon
        ImageIcon image = new ImageIcon("Icons/logarlec.png");
        this.setIconImage(image.getImage());

        // Custom cursor
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getImage(), new Point(0, 5), "HandCursor");
        this.setCursor(customCursor);

        title = new JLabel("Logarlec");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.RED);
        title.setBounds(470, 40, 100, 50);
        backgroundPanel.add(title);

        startButton = new JButton("Start");
        startButton.setBounds(40, 70, 100, 50);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        backgroundPanel.add(startButton);

        descriptionButton = new JButton("Description");
        descriptionButton.setBounds(230, 70, 100, 50);
        descriptionButton.setFocusable(false);
        descriptionButton.addActionListener(this);
        backgroundPanel.add(descriptionButton);

        controlButton = new JButton("Control");
        controlButton.setBounds(710, 70, 100, 50);
        controlButton.setFocusable(false);
        controlButton.addActionListener(this);
        backgroundPanel.add(controlButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(865, 70, 100, 50);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        backgroundPanel.add(exitButton);

        this.add(backgroundPanel);
        this.setSize(1000, 500);
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
            new DescriptionWindow();
        } else if (e.getSource() == controlButton) {
            System.out.println("Control button clicked");
            new ControlWindow();
        } else if (e.getSource() == exitButton) {
            System.out.println("Exit button clicked");
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new Menu();
    }
}

