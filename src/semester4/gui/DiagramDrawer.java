package semester4.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.util.Arrays.asList;

public class DiagramDrawer extends JComponent {
    private final ArrayList<ArrayList<Number>> data;
    /**
     * Preferred width and height for this component
     */
    static final int PREFERRED_SIZE = 400;
    /**
     * List of colors used for diagram drawing
     */
    static private final List<Color> colorList = asList(
            new Color(151, 95, 85),
            new Color(151, 129, 80),
            new Color(103, 15, 96),
            new Color(105, 94, 58),
            new Color(36, 50, 151),
            new Color(85, 151, 47),
            new Color(151, 93, 66),
            new Color(78, 34, 44),
            new Color(36, 37, 50),
            new Color(88, 99, 53),
            new Color(151, 32, 130),
            new Color(111, 55, 106),
            new Color(29, 58, 29),
            new Color(151, 55, 31),
            new Color(115, 30, 102),
            new Color(151, 120, 33),
            new Color(151, 44, 63),
            new Color(82, 72, 63),
            new Color(102, 216, 106)
    );

    /**
     * Initializes diagram with <code>data</code>
     * @param data Data displayed in diagram
     */
    DiagramDrawer(final ArrayList<ArrayList<Number>> data) {
        this.data = data;
        setOpaque(true);
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        int colorIndex = 0;
        int circleWidth = width;
        int circleHeight = height;
        for (ArrayList<Number> row : data) {
            final int startX = (width - circleWidth) / 2;
            final int startY = (height - circleHeight) / 2;
            final double sum = row.stream().mapToDouble(Number::doubleValue).sum();
            double prevAngle = 0;
            for (int colIndex = 0; colIndex < row.size(); colIndex++) {
                final Number value = row.get(colIndex);
                final double angle;
                if (colIndex < row.size() - 1) {
                    angle = (int) (value.doubleValue() / sum * 360);
                } else {
                    angle = 360 - prevAngle;
                }
                g.setColor(colorList.get(colorIndex));
                g.fillArc(startX, startY, circleWidth, circleHeight, normalizeAngle((int) prevAngle), (int) -angle);
                g.setColor(BLACK);
                g.drawArc(startX, startY, circleWidth, circleHeight, normalizeAngle((int) prevAngle), (int) -angle);

                prevAngle += angle;
                colorIndex = (colorIndex + 1) % colorList.size();
            }
            circleHeight -= height / data.size();
            circleWidth -= width / data.size();
        }
        final int startX = (width - circleWidth) / 2;
        final int startY = (height - circleHeight) / 2;
        g.setColor(WHITE);
        g.fillOval(startX, startY, circleWidth, circleHeight);
        g.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREFERRED_SIZE, PREFERRED_SIZE);
    }

    /**
     * Saves diagram image to file
     * @param file Target file
     * @param format File format (e.g. jpeg or png)
     * @throws IOException (or NullPointerException because of Oracle JDK bug) if diagram writing to file fails
     */
    public void saveTo(final File file, String format) throws IOException, NullPointerException {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        paint(graphics2D);
        ImageIO.write(image, format, file);
    }

    /**
     * Change angle from clockwise, starting from 12am to default (counter-clockwise, start from 3am)
     * @param angle Transformed angle
     * @return Angle in the default system
     */
    int normalizeAngle(int angle) {
        return (90 - angle + 360) % 360;
    }
}
