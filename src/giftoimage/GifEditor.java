package giftoimage;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import cafe.image.ImageIO;
import cafe.image.ImageType;
import cafe.image.gif.GIFTweaker;
import cafe.image.writer.ImageWriter;



public class GifEditor {

	//A lot of dead code here that i might use later
    JFrame f = new JFrame("Animation");
    int b = 0;
    int overlay = 0;
    int count = 0;
    int limit;
    ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
    List<BufferedImage> frames2 = new ArrayList<BufferedImage>();
    ArrayList<Image> images = new ArrayList<Image>();


    public GifEditor() {
        super();
    }



    public void createPanel() {
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


    	//You can choose the extension of the final images here, just change from JPG to anything really.
        ImageWriter writer = ImageIO.getWriter(ImageType.JPG);

        FileInputStream is;
        try {
            is = new FileInputStream(file.toString());
            GIFTweaker.splitAnimatedGIF(is, writer, "split");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        // DEAD CODE
        //For now
        
        // pring general GIF image info
        // int limit = gifImage.getNumberOfFrames();
        // for (int i = 0; i < gifImage.getNumberOfFrames(); i++) {
        // GifFrame frame = gifImage.getFrame(i);
        // Image image = frame.getAsImage();
        // images.add(image);
        // BufferedImage bufferedImage = frame.getAsBufferedImage();
        //
        // frames.add(bufferedImage);
        // }
        //
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // createImagePanel();
        // getFrame();
    }

    // public void createImagePanel() {
    //
    // JLabel label = new JLabel(new ImageIcon(adjustImage()));
    // f.getContentPane().removeAll();
    // f.getContentPane().add(label);
    // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // f.pack();
    // f.setLocationRelativeTo(null);
    // f.setVisible(true);
    // }
    //
    // public int getFrame() {
    // JFrame buttons = new JFrame("Commands");
    // JPanel panel = new JPanel();
    // buttons.add(panel);
    // buttons.setSize(350, 100);
    // JButton back = new JButton("Previous frame");
    // JButton next = new JButton("Next frame");
    // JButton save = new JButton("Save frame");
    // JButton adjust = new JButton("Fix frame");
    // JButton clear = new JButton("Clear frame");
    // panel.add(back);
    // panel.add(save);
    // panel.add(next);
    // panel.add(adjust);
    // panel.add(clear);
    // buttons.setVisible(true);
    //
    // adjust.addActionListener(new AdjustFrame());
    // clear.addActionListener(new ClearFrame());
    // back.addActionListener(new PreviousFrame());
    // next.addActionListener(new NextFrame());
    // save.addActionListener(new ActionListener() {
    //
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // JFileChooser fileChooser = new JFileChooser();
    //
    // int saveValue = fileChooser.showSaveDialog(null);
    // if (saveValue == JFileChooser.APPROVE_OPTION) {
    // try {
    // ImageIO.write(adjustImage(), "png", new File(fileChooser.getSelectedFile().getAbsolutePath()
    // + ".png"));
    // } catch (IOException a) {
    // a.printStackTrace();
    // }
    // }
    // }
    // });
    // return b;
    // }

    // class NextFrame implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // if (!(b >= limit - 1)) {
    // ++b;
    // overlay = 0;
    // createImagePanel();
    // } else {
    // JOptionPane.showMessageDialog(null, "This is the last frame");
    // }
    // }
    // }
    //
    // class AdjustFrame implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // if (!(overlay >= limit - 1)) {
    // ++overlay;
    // createImagePanel();
    // } else {
    // JOptionPane.showMessageDialog(null, "This is the last frame possible. Clear the image");
    // }
    // }
    // }
    //
    // class ClearFrame implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // overlay = 0;
    // createImagePanel();
    // }
    // }
    //
    // class PreviousFrame implements ActionListener {
    // public void actionPerformed(ActionEvent e) {
    // if (b != 0) {
    // --b;
    // createImagePanel();
    // } else {
    // JOptionPane.showMessageDialog(null, "This is the first frame");
    // }
    //
    // }
    // }
    //
    // public BufferedImage adjustImage() {
    //
    // int w = Math.max(frames.get(overlay).getWidth(), frames.get(b).getWidth());
    // int h = Math.max(frames.get(overlay).getHeight(), frames.get(b).getHeight());
    // BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB_PRE);
    //
    // // paint both images, preserving the alpha channels
    // Graphics g = combined.getGraphics();
    // g.drawImage(frames.get(overlay), 0, 0, null);
    // g.drawImage(frames.get(b), 0, 0, null);
    //
    // return combined;
    // }
}
