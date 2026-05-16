import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private int seconds = 0;
    private boolean running = false;
    private final JLabel timeLabel;
    private final Timer timer;

    public Main() {
        setTitle("Секундомер");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));

        JButton startButton = new JButton("Старт");
        JButton pauseButton = new JButton("Пауза");
        JButton resetButton = new JButton("Сброс");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resetButton);
        add(timeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        timer = new Timer(1000, e -> {
            seconds++;
            updateTime();
        });
        startButton.addActionListener(e -> startTimer());
        pauseButton.addActionListener(e -> pauseTimer());
        resetButton.addActionListener(e -> resetTimer());
    }

    private void startTimer() {
        if (!running) {
            timer.start();
            running = true;
        }
    }
    private void pauseTimer() {
        timer.stop();
        running = false;
    }

    private void resetTimer() {
        timer.stop();
        running = false;
        seconds = 0;
        updateTime();
    }
    private void updateTime() {
        int minutes = seconds / 60;
        int sec = seconds % 60;

        timeLabel.setText(String.format("%02d:%02d", minutes, sec));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}