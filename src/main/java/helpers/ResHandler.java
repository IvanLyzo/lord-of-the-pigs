package helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ResHandler {

    public static BufferedImage getSprite(String spriteLoc) {
        try {
            BufferedImage sprite = ImageIO.read(Objects.requireNonNull(ResHandler.class.getResourceAsStream(spriteLoc)));
            return sprite;
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException("Could not load sprite at location " + spriteLoc);
        }
    }

    public static BufferedImage rotateSprite(BufferedImage img)
    {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());

        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(90), (double) width / 2, (double) height / 2);
        g2.drawImage(img, null, 0, 0);

        return newImage;
    }

    public static Font getFont(String fontLoc) {
        InputStream is = ResHandler.class.getResourceAsStream("/font/ByteBounce.ttf");
        if (is != null) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, is);
            } catch (Exception e) {
                throw new RuntimeException("Could not read in font!!");
            }
        } else {
            throw new RuntimeException("Font location: " + fontLoc + ", could not be found!!");
        }
    }
}
