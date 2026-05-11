import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch extends JFrame implements ActionListener {

    private JLabel timeLabel;

    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    private Timer timer;

    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    public Stopwatch() {

        setTitle("Stopwatch");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout(15, 15));

        JLabel title = new JLabel("STOPWATCH", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        timeLabel = new JLabel("00 : 00 : 00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                seconds++;

                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }

                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }

                updateTime();
            }
        });

        setVisible(true);
    }

    private void updateTime() {

        String time = String.format(
                "%02d : %02d : %02d",
                hours,
                minutes,
                seconds
        );

        timeLabel.setText(time);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == startButton) {

            timer.start();

        } else if (e.getSource() == stopButton) {

            timer.stop();

        } else if (e.getSource() == resetButton) {

            timer.stop();

            hours = 0;
            minutes = 0;
            seconds = 0;

            updateTime();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Stopwatch());

    }
}