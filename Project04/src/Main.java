import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    private Color currentColor = Color.BLACK;
    public Main() {
        setTitle("Рисовалка");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        DrawingPanel drawingPanel = new DrawingPanel();

        JPanel buttonPanel = new JPanel();
        JButton blackButton = new JButton("Черный");
        JButton redButton = new JButton("Красный");
        JButton blueButton = new JButton("Синий");
        JButton greenButton = new JButton("Зеленый");
        JButton clearButton = new JButton("Очистить");

        buttonPanel.add(blackButton);
        buttonPanel.add(redButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        blackButton.addActionListener(e ->
                currentColor = Color.BLACK);
        redButton.addActionListener(e ->
                currentColor = Color.RED);
        blueButton.addActionListener(e ->
                currentColor = Color.BLUE);
        greenButton.addActionListener(e ->
                currentColor = Color.GREEN);
        clearButton.addActionListener(e ->
                drawingPanel.clear());
    }

    class DrawingPanel extends JPanel {
        private Image image;
        private Graphics2D graphics2D;
        private int x;
        private int y;

        public DrawingPanel() {
            setBackground(Color.WHITE);
            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    x = e.getX();
                    y = e.getY();
                }
                @Override
                public void mouseDragged(MouseEvent e) {
                    int newX = e.getX();
                    int newY = e.getY();

                    if (graphics2D != null) {
                        graphics2D.setColor(currentColor);
                        graphics2D.drawLine(x, y, newX, newY);

                        repaint();
                    }
                    x = newX;
                    y = newY;
                }
            };
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (image == null) {
                image = createImage(
                        getWidth(),
                        getHeight()
                );

                graphics2D = (Graphics2D) image.getGraphics();
                graphics2D.setColor(Color.WHITE);
                graphics2D.fillRect(
                        0,
                        0,
                        getWidth(),
                        getHeight()
                );
            }

            g.drawImage(image, 0, 0, null);
        }

        public void clear() {
            graphics2D.setColor(Color.WHITE);
            graphics2D.fillRect(
                    0,
                    0,
                    getWidth(),
                    getHeight()
            );
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}