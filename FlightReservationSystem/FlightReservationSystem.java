
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FlightReservationSystem extends JFrame {
    JTextField passenger=new JTextField(15);
    JComboBox<String> from=new JComboBox<>(new String[]{"Delhi","Mumbai","Bengaluru","Kolkata"});
    JComboBox<String> to=new JComboBox<>(new String[]{"Delhi","Mumbai","Bengaluru","Kolkata"});
    JComboBox<String> seat=new JComboBox<>(new String[]{"Economy","Business","First Class"});
    JComboBox<String> airline=new JComboBox<>(new String[]{"Air India","IndiGo","Vistara","Akasa"});
    JSpinner tickets=new JSpinner(new SpinnerNumberModel(1,1,8,1));
    JLabel fare=new JLabel("₹0"),tax=new JLabel("₹0"),total=new JLabel("₹0");
    DefaultTableModel model=new DefaultTableModel(
            new String[]{"Passenger","Airline","Route","Seat","Total"},0);

    public FlightReservationSystem(){
        setTitle("Flight Reservation System");
        setSize(1050,680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg=new Color(248,250,252);
        Color card=Color.WHITE;
        Color primary=new Color(79,70,229);

        JPanel root=new JPanel(new BorderLayout(15,15));
        root.setBackground(bg);
        root.setBorder(new EmptyBorder(18,18,18,18));
        setContentPane(root);

        JLabel head=new JLabel("✈ Flight Reservation System",SwingConstants.CENTER);
        head.setOpaque(true);
        head.setBackground(primary);
        head.setForeground(Color.WHITE);
        head.setFont(new Font("Segoe UI",Font.BOLD,24));
        head.setBorder(new EmptyBorder(15,10,15,10));
        root.add(head,BorderLayout.NORTH);

        JPanel form=new JPanel(new GridBagLayout());
        form.setBackground(card);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(15,15,15,15)));
        GridBagConstraints g=new GridBagConstraints();
        g.insets=new Insets(8,8,8,8);
        g.fill=GridBagConstraints.HORIZONTAL;

        add(form,g,0,"Passenger",passenger);
        add(form,g,1,"From",from);
        add(form,g,2,"To",to);
        add(form,g,3,"Airline",airline);
        add(form,g,4,"Seat",seat);
        add(form,g,5,"Tickets",tickets);

        JPanel bill=new JPanel(new GridLayout(3,2,8,8));
        bill.setBackground(card);
        bill.setBorder(new TitledBorder("Fare Summary"));
        bill.add(new JLabel("Fare")); bill.add(fare);
        bill.add(new JLabel("Tax (18%)")); bill.add(tax);
        bill.add(new JLabel("Grand Total")); bill.add(total);

        JButton calc=button("Calculate",new Color(59,130,246));
        JButton reserve=button("Reserve Flight",new Color(16,185,129));
        JButton clear=button("Clear",new Color(239,68,68));

        JPanel bp=new JPanel();bp.setOpaque(false);
        bp.add(calc);bp.add(reserve);bp.add(clear);

        g.gridx=0;g.gridy=6;g.gridwidth=2;form.add(bp,g);
        g.gridx=2;g.gridy=0;g.gridheight=7;form.add(bill,g);

        root.add(form,BorderLayout.CENTER);

        JTable table=new JTable(model);
        table.setRowHeight(28);
        root.add(new JScrollPane(table),BorderLayout.SOUTH);

        calc.addActionListener(e->calculate());
        reserve.addActionListener(e->{
            if(passenger.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Enter passenger name.");
                return;
            }
            if(from.getSelectedItem().equals(to.getSelectedItem())){
                JOptionPane.showMessageDialog(this,"Source and destination cannot be the same.");
                return;
            }
            calculate();
            model.addRow(new Object[]{
                passenger.getText(),
                airline.getSelectedItem(),
                from.getSelectedItem()+" → "+to.getSelectedItem(),
                seat.getSelectedItem(),
                total.getText()
            });
            JOptionPane.showMessageDialog(this,"Flight Reserved Successfully!");
        });
        clear.addActionListener(e->{
            passenger.setText("");
            tickets.setValue(1);
            fare.setText("₹0");tax.setText("₹0");total.setText("₹0");
        });
    }

    void calculate(){
        int base=switch((String)seat.getSelectedItem()){
            case "Business"->6000;
            case "First Class"->10000;
            default->3500;
        };
        int qty=(Integer)tickets.getValue();
        double f=base*qty;
        double t=f*0.18;
        fare.setText("₹"+String.format("%.2f",f));
        tax.setText("₹"+String.format("%.2f",t));
        total.setText("₹"+String.format("%.2f",f+t));
    }

    void add(JPanel p,GridBagConstraints g,int y,String l,Component c){
        g.gridx=0;g.gridy=y;g.gridwidth=1;g.gridheight=1;
        p.add(new JLabel(l),g);
        g.gridx=1;
        p.add(c,g);
    }

    JButton button(String text,Color color){
        JButton b=new JButton(text);
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,13));
        return b;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new FlightReservationSystem().setVisible(true));
    }
}
