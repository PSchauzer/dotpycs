package BL;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScrollPaneWithWatermark extends JScrollPane {

    BufferedImage backgroundImage;

    public ScrollPaneWithWatermark(JTable table, URL url) {
        super(table);
        try {
            backgroundImage = ImageIO.read(url);
        } catch (Exception ex) 
        {

        }
        setOpaque(false);
        getViewport().setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        final int imageWidth = backgroundImage.getWidth();
        final int imageHeight = backgroundImage.getHeight();
        final Dimension d = getSize();
        final int x = (d.width - imageWidth) / 2;
        final int y = (d.height - imageHeight) / 2;
        g.drawImage(backgroundImage, x, y, null, null);
        super.paintComponent(g);
    }
}
