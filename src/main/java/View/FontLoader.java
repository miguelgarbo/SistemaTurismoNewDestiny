package View;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    public static Font loadFont(String fontPath, float size) {
        try {
            InputStream fontStream = FontLoader.class.getResourceAsStream(fontPath);
            if (fontStream != null) {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                return customFont;
            } else {
                System.err.println("Font not found at path: " + fontPath);
            }
        } catch (FontFormatException | IOException e) {
            System.err.println("Error loading font from " + fontPath + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
