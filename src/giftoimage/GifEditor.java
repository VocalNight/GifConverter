package giftoimage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import cafe.image.reader.GIFReader;



public class GifEditor {

    JFrame f = new JFrame("Animation");
    int b = 0;
    int overlay = 0;
    int count = 0;
    int limit;
    List<BufferedImage> frames = new ArrayList<BufferedImage>();


    public GifEditor() {
        super();
    }



    public void createPanel() {
        // Create the panel to chose the gif
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Gif Files", "gif");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        File file = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            createFrames(file);
        } else {
            System.out.println("No file/gif selected");
        }
    }

    public void createFrames(File file) {
        // Split the gif into frames
        try {
            FileInputStream gif = new FileInputStream(file);
            GIFReader reader = new GIFReader();
            BufferedImage frame = reader.getFrameAsBufferedImageEx(gif);

            while (frame != null) {
                frames.add(frame);
                frame = reader.getFrameAsBufferedImageEx(gif);
                ++limit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        createImagePanel();
        getFrame();

    }


    // Creates the file control panel
    public void createImagePanel() {

        JLabel label = new JLabel(new ImageIcon(frames.get(b)));
        f.getContentPane().removeAll();
        f.getContentPane().add(label);
        f.getContentPane().repaint();
        f.getContentPane().revalidate();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public int getFrame() {
        JFrame buttons = new JFrame("Commands");
        JPanel panel = new JPanel();
        buttons.add(panel);
        buttons.setSize(350, 100);
        JButton back = new JButton("Previous frame");
        JButton next = new JButton("Next frame");
        JButton save = new JButton("Save frame");
        panel.add(back);
        panel.add(save);
        panel.add(next);
        buttons.setVisible(true);

        back.addActionListener(new PreviousFrame());
        next.addActionListener(new NextFrame());
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int saveValue = fileChooser.showSaveDialog(null);
                if (saveValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        // The program writes the file here, you can choose the extension you want.
                        ImageIO.write(frames.get(b), "png", new File(fileChooser.getSelectedFile().getAbsolutePath() + ".png"));
                    } catch (IOException a) {
                        a.printStackTrace();
                    }
                }
            }
        });
        return b;
    }

    class NextFrame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!(b >= limit - 1)) {
                ++b;
                overlay = 0;
                createImagePanel();
            } else {
                JOptionPane.showMessageDialog(null, "This is the last frame");
            }
        }
    }

    class AdjustFrame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!(overlay >= limit - 1)) {
                ++overlay;
                createImagePanel();
            } else {
                JOptionPane.showMessageDialog(null, "This is the last frame possible. Clear the image");
            }
        }
    }

    class ClearFrame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            overlay = 0;
            createImagePanel();
        }
    }

    class PreviousFrame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (b != 0) {
                --b;
                createImagePanel();
            } else {
                JOptionPane.showMessageDialog(null, "This is the first frame");
            }

        }
    }
}
