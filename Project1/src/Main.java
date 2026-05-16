////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    public static void main(String[] args) {
//        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");
//
//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
//    }
//}


import javax.swing.*;
import java.awt.*;
public class Main extends JFrame {
    private final JTextField display;
    private double firstNumber = 0;
    private String operation = "";
    private boolean startNewNumber = true;

    public Main() {
        setTitle("Калькулятор");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "", "", ""
        };

        for (String text : buttons) {
            if (text.isEmpty()) {
                panel.add(new JLabel());
                continue;
            }
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.addActionListener(e -> handleButton(text));
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }
    private void handleButton(String text) {

        if (text.matches("[0-9]")) {
            enterNumber(text);
        } else if (text.equals(".")) {
            enterDot();
        } else if (text.equals("C")) {
            clear();
        } else if (text.equals("=")) {
            calculate();
        } else {
            chooseOperation(text);
        }
    }
    private void enterNumber(String number) {
        if (startNewNumber || display.getText().equals("0")) {
            display.setText(number);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + number);
        }
    }

    private void enterDot() {
        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }
    private void chooseOperation(String op) {
        firstNumber = Double.parseDouble(display.getText());
        operation = op;
        startNewNumber = true;
    }

    private void calculate() {
        if (operation.isEmpty()) {
            return;
        }
        double secondNumber = Double.parseDouble(display.getText());
        double result;
        switch (operation) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    display.setText("Ошибка");
                    operation = "";
                    startNewNumber = true;
                    return;
                }
                result = firstNumber / secondNumber;
                break;
            default:
                return;
        }
        display.setText(formatResult(result));
        operation = "";
        startNewNumber = true;
    }
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
    private void clear() {

        display.setText("0");
        firstNumber = 0;
        operation = "";
        startNewNumber = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }

}