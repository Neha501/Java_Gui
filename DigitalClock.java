import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class DigitalClock extends JFrame {

    private JLabel timeLabel;
    private JLabel dateLabel;

    public DigitalClock() {

        setTitle("Digital Clock");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.BLACK);

        timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timeLabel.setForeground(Color.GREEN);

        dateLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        dateLabel.setForeground(Color.WHITE);

        panel.add(timeLabel);
        panel.add(dateLabel);

        add(panel);

        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();

        updateClock();

        setVisible(true);
    }

    private void updateClock() {

        Date currentDate = new Date();

        SimpleDateFormat timeFormat =
                new SimpleDateFormat("hh:mm:ss a");

        SimpleDateFormat dateFormat =
                new SimpleDateFormat("EEEE, dd MMMM yyyy");

        timeLabel.setText(timeFormat.format(currentDate));
        dateLabel.setText(dateFormat.format(currentDate));
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new DigitalClock());
    }
}