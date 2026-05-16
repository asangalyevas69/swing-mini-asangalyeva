import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    private final DefaultListModel<String> taskModel;
    private final JList<String> taskList;
    private final JTextField taskField;

    public Main() {
        setTitle("Список задач");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskField = new JTextField();

        JButton addButton = new JButton("Добавить");
        JButton clearButton = new JButton("Очистить всё");

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(clearButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addTask());

        taskField.addActionListener(e -> addTask());

        clearButton.addActionListener(e -> taskModel.clear());

        taskList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = taskList.locationToIndex(e.getPoint());

                    if (index >= 0) {
                        taskModel.remove(index);
                    }
                }
            }
        });
    }

    private void addTask() {
        String task = taskField.getText().trim();

        if (task.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Введите задачу",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        taskModel.addElement(task);
        taskField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}