import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame {

    private int randomNumber;
    private int attempts;

    private final JTextField inputField;
    private final JLabel resultLabel;
    private final JLabel attemptsLabel;

    public Main() {
        setTitle("Угадай число");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(6, 1, 10, 10));
        JLabel titleLabel = new JLabel(
                "Угадайте число от 1 до 100",
                SwingConstants.CENTER
        );


        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        inputField = new JTextField();

        JButton checkButton = new JButton("Проверить");
        JButton newGameButton = new JButton("Новая игра");

        resultLabel = new JLabel(
                "Введите число",
                SwingConstants.CENTER
        );

        attemptsLabel = new JLabel(
                "Попыток: 0",
                SwingConstants.CENTER
        );

        add(titleLabel);
        add(inputField);
        add(checkButton);
        add(newGameButton);
        add(resultLabel);
        add(attemptsLabel);

        startNewGame();

        checkButton.addActionListener(e -> checkNumber());
        inputField.addActionListener(e -> checkNumber());
        newGameButton.addActionListener(e -> startNewGame());
    }

    private void startNewGame() {

        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("Введите число");
        attemptsLabel.setText("Попыток: 0");

        inputField.setText("");
    }

    private void checkNumber() {

        String text = inputField.getText().trim();

        if (text.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Введите число",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int userNumber;
        try {
            userNumber = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Введите только число",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        attempts++;
        attemptsLabel.setText("Попыток: " + attempts);
        if (userNumber < randomNumber) {
            resultLabel.setText("Загаданное число больше");

        } else if (userNumber > randomNumber) {

            resultLabel.setText("Загаданное число меньше");
        } else {

            resultLabel.setText("Вы угадали!");
        }

        inputField.setText("");
    }
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}