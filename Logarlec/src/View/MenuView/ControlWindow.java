package View.MenuView;

import javax.swing.*;
import java.awt.*;

/**
 * The window containing the necessary instructions for the players to understand and control the game.
 */
public class ControlWindow extends JFrame {
    ControlWindow() {
        super("Control");
        setSize(500, 500);

        // tray icon
        ImageIcon image = new ImageIcon("Icons/logarlec.png");
        this.setIconImage(image.getImage());

        // custom cursor
        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(image.getImage(), new Point(0, 5), "HandCursor");
        this.setCursor(customCursor);

        // Szöveg létrehozása
        String text = "Irányítási információk felhasználóknak\n\n" +
                "Akár előre betöltött pályán, akár az alapértelmezett pályán játszunk, a Start gombra kattintás után megjelenik bal oldalon a játék térképe, \n" +
                "illetve az egyik szoba sárgával lesz jelölve, jelezve hogy a soron következő hallgató ebben a szobában tartózkodik.\n\n" +
                "Jobb oldalon felül láthatjuk a soron következő hallgató által birtokolt tárgyak listáját, alatta pedig az aktuális szobájában található tárgyakat és karaketereket.\n" +
                "Ha a térképen található szobák közül rákattintunk valamelyikre, akkor jobb oldalon látható lesz egy panelen, hogy milyen tárgyak és karakterek találhatóak benne.\n" +
                "Az aktuális szobánkban opcionálisan felvehetünk tárgyakat a hozzájuk tartozó Pick feliratú gomb segítségével. Az aktuális hallgató a tárgyai közül a Drop gombra kattintva dobással dobhatja el őket az aktuális szobájában.\n" +
                "Ha át szeretnénk lépni egy velünk szomszédos szobába, a Move feliratú gombbal tehetjük ezt meg, a Stay feliratú gomb pedig arra szolgál, hogy véget vessünk a körünknek, szobába átmozgás nélkül.\n" +
                "Körünkben a tárgyainkat tetszőlegesen használhatjuk, célunk hogy elérjünk az arany színnel rendelkező logarléc szobájába, majd ott felvegyük a mágikus tárgyat, mellyel megnyerjük a játékot. ";

        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Betűtípus és méret beállítása
        textArea.setLineWrap(true); // Automatikus sortörés
        textArea.setWrapStyleWord(true); // Csak szavakonként történő sortörés

        JScrollPane scrollPane = new JScrollPane(textArea); // Görgetősáv hozzáadása
        scrollPane.setPreferredSize(new Dimension(500, 500)); // Méret beállítása a scroll panelnek

        getContentPane().add(scrollPane, BorderLayout.CENTER); // Panel hozzáadása az ablak közepére
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack(); // Az ablak méretének automatikus beállítása az elemekhez
        setLocationRelativeTo(null); // Az ablak középre helyezése
        setVisible(true); // Az ablak láthatóvá tétele
    }
}
