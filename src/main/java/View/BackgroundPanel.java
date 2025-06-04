package View;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    private String imagePath; // To store the path for error messages

    public BackgroundPanel(String imagePath) {
        this.imagePath = imagePath;
        loadImage();
        setOpaque(false);
    }

    private void loadImage() {
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            this.backgroundImage = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Background image not found: " + imagePath);
            this.backgroundImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Optional: draw a solid background color if image fails to load
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void setBackgroundImage(String newImagePath) {
        this.imagePath = newImagePath;
        loadImage();
        repaint();
    }
}

