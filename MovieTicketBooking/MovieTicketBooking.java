// this is movieticektbookig system
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class MovieTicketBooking extends JFrame {
    JComboBox<String> movieBox = new JComboBox<>(new String[]{
            "Avengers: Endgame","Interstellar","Inception","Spider-Man","Dune"});
    JComboBox<String> seatBox = new JComboBox<>(new String[]{
            "Silver","Gold","Platinum"});
    JComboBox<String> timeBox = new JComboBox<>(new String[]{
            "10:00 AM","1:30 PM","4:30 PM","7:30 PM","10:15 PM"});
    JTextField dateField=new JTextField("31/07/2026");
    JSpinner qty=new JSpinner(new SpinnerNumberModel(1,1,10,1));

    JLabel subtotal=new JLabel("₹0");
    JLabel gst=new JLabel("₹0");
    JLabel total=new JLabel("₹0");

    DefaultTableModel model=new DefaultTableModel(
            new String[]{"Movie","Seat","Qty","Total","Status"},0);

    public MovieTicketBooking(){
        setTitle("Movie Ticket Booking");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg=new Color(248,250,252);
        Color card=Color.WHITE;
        Color primary=new Color(79,70,229);

        JPanel root=new JPanel(new BorderLayout(15,15));
        root.setBorder(new EmptyBorder(18,18,18,18));
        root.setBackground(bg);
        setContentPane(root);

        JLabel header=new JLabel("🎬 Movie Ticket Booking System",SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(primary);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI",Font.BOLD,24));
        header.setBorder(new EmptyBorder(15,10,15,10));
        root.add(header,BorderLayout.NORTH);

        JPanel top=new JPanel(new GridBagLayout());
        top.setBackground(card);
        top.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(15,15,15,15)));
        GridBagConstraints g=new GridBagConstraints();
        g.insets=new Insets(8,8,8,8);
        g.fill=GridBagConstraints.HORIZONTAL;

        addField(top,g,0,"Movie",movieBox);
        addField(top,g,1,"Date",dateField);
        addField(top,g,2,"Show Time",timeBox);
        addField(top,g,3,"Seat",seatBox);
        addField(top,g,4,"Tickets",qty);

        JButton calc=btn("Calculate",new Color(59,130,246));
        JButton book=btn("Book Ticket",new Color(16,185,129));
        JButton reset=btn("Reset",new Color(239,68,68));

        JPanel bp=new JPanel();
        bp.setOpaque(false);
        bp.add(calc);bp.add(book);bp.add(reset);
        g.gridx=0;g.gridy=5;g.gridwidth=2;top.add(bp,g);

        JPanel summary=new JPanel(new GridLayout(3,2,10,10));
        summary.setBackground(card);
        summary.setBorder(new TitledBorder("Ticket Summary"));
        summary.add(new JLabel("Subtotal"));summary.add(subtotal);
        summary.add(new JLabel("GST (18%)"));summary.add(gst);
        summary.add(new JLabel("Grand Total"));summary.add(total);
        g.gridx=2;g.gridy=5;g.gridwidth=1;top.add(summary,g);

        root.add(top,BorderLayout.NORTH);

        JTable table=new JTable(model);
        table.setRowHeight(28);
        JScrollPane sp=new JScrollPane(table);
        sp.setBorder(new TitledBorder("Booking History"));
        root.add(sp,BorderLayout.CENTER);

        calc.addActionListener(e->calculate());
        book.addActionListener(e->{
            calculate();
            model.addRow(new Object[]{
                movieBox.getSelectedItem(),
                seatBox.getSelectedItem(),
                qty.getValue(),
                total.getText(),
                "Confirmed"
            });
            JOptionPane.showMessageDialog(this,"Booking Confirmed!");
        });
        reset.addActionListener(e->{
            qty.setValue(1);
            subtotal.setText("₹0");
            gst.setText("₹0");
            total.setText("₹0");
        });
    }

    void calculate(){
        int q=(Integer)qty.getValue();
        int price=switch((String)seatBox.getSelectedItem()){
            case "Gold"->300;
            case "Platinum"->500;
            default->180;
        };
        double sub=price*q;
        double tax=sub*0.18;
        subtotal.setText("₹"+String.format("%.2f",sub));
        gst.setText("₹"+String.format("%.2f",tax));
        total.setText("₹"+String.format("%.2f",sub+tax));
    }

    void addField(JPanel p,GridBagConstraints g,int row,String l,java.awt.Component c){
        g.gridx=0;g.gridy=row;p.add(new JLabel(l),g);
        g.gridx=1;p.add(c,g);
    }
    JButton btn(String t,Color c){
        JButton b=new JButton(t);
        b.setBackground(c);b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,13));
        return b;
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new MovieTicketBooking().setVisible(true));
    }
}
