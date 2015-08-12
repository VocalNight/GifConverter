package giftoimage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowConverter {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gif Converter");
        frame.setVisible(true);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("Select gif");
        panel.add(button);
        //Click the button to search for the file
        button.addActionListener(new Action1());

    }

    static class Action1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GifEditor editor = new GifEditor();
            editor.createPanel();
        }
    }
}
