import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoList extends JFrame implements ActionListener {

    private JTextField taskField;
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;

    private JButton addButton;
    private JButton completeButton;
    private JButton deleteButton;
    private JButton clearButton;

    private JLabel taskCountLabel;

    public TodoList() {

        setTitle("To-Do List");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel(
                "MY TO-DO LIST",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 25)
        );

        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 10, 10)
        );

        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 30, 10, 30)
        );

        JPanel inputPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        taskField = new JTextField();

        taskField.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        addButton = new JButton("Add Task");
        addButton.addActionListener(this);

        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        centerPanel.add(inputPanel, BorderLayout.NORTH);

        taskModel = new DefaultListModel<>();

        taskList = new JList<>(taskModel);

        taskList.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        taskList.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(taskList);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Tasks")
        );

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(
                new GridLayout(2, 1, 5, 5)
        );

        JPanel buttonPanel = new JPanel();

        completeButton = new JButton("Complete");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear All");

        completeButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(completeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        taskCountLabel = new JLabel(
                "Total Tasks: 0",
                SwingConstants.CENTER
        );

        taskCountLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        bottomPanel.add(buttonPanel);
        bottomPanel.add(taskCountLabel);

        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 10, 20, 10)
        );

        add(bottomPanel, BorderLayout.SOUTH);

        taskField.addActionListener(e -> addTask());

        setVisible(true);
    }

    private void addTask() {

        String task = taskField.getText().trim();

        if (task.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a task.",
                    "Empty Task",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        taskModel.addElement(task);

        taskField.setText("");
        taskField.requestFocus();

        updateTaskCount();
    }

    private void updateTaskCount() {

        taskCountLabel.setText(
                "Total Tasks: " + taskModel.size()
        );
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addButton) {

            addTask();
        }

        else if (e.getSource() == completeButton) {

            int index = taskList.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a task.",
                        "No Task Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String task = taskModel.getElementAt(index);

            if (!task.startsWith("✓ ")) {

                taskModel.setElementAt(
                        "✓ " + task,
                        index
                );
            }
        }

        else if (e.getSource() == deleteButton) {

            int index = taskList.getSelectedIndex();

            if (index == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a task to delete.",
                        "No Task Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            taskModel.remove(index);

            updateTaskCount();
        }

        else if (e.getSource() == clearButton) {

            if (taskModel.isEmpty()) {
                return;
            }

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to delete all tasks?",
                    "Clear Tasks",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                taskModel.clear();

                updateTaskCount();
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new TodoList()
        );
    }
}