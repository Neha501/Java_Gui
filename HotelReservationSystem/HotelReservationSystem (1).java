
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HotelReservationSystem extends JFrame{
    JTextField name=new JTextField(15),phone=new JTextField(15);
    JComboBox<String> room=new JComboBox<>(new String[]{"Standard","Deluxe","Suite"});
    JSpinner nights=new JSpinner(new SpinnerNumberModel(1,1,30,1));
    JLabel roomCost=new JLabel("₹0"),tax=new JLabel("₹0"),total=new JLabel("₹0");
    DefaultTableModel model=new DefaultTableModel(
            new String[]{"Guest","Room","Nights","Total","Status"},0);

    public HotelReservationSystem(){
        setTitle("Hotel Reservation System");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg=new Color(248,250,252);
        Color card=Color.WHITE;
        Color primary=new Color(99,102,241);

        JPanel root=new JPanel(new BorderLayout(15,15));
        root.setBorder(new EmptyBorder(18,18,18,18));
        root.setBackground(bg);
        setContentPane(root);

        JLabel head=new JLabel("🏨 Hotel Reservation System",SwingConstants.CENTER);
        head.setOpaque(true);
        head.setBackground(primary);
        head.setForeground(Color.WHITE);
        head.setFont(new Font("Segoe UI",Font.BOLD,24));
        head.setBorder(new EmptyBorder(14,10,14,10));
        root.add(head,BorderLayout.NORTH);

        JPanel form=new JPanel(new GridBagLayout());
        form.setBackground(card);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(15,15,15,15)));
        GridBagConstraints g=new GridBagConstraints();
        g.insets=new Insets(8,8,8,8);g.fill=GridBagConstraints.HORIZONTAL;

        addField(form,g,0,"Guest Name",name);
        addField(form,g,1,"Phone",phone);
        addField(form,g,2,"Room Type",room);
        addField(form,g,3,"Nights",nights);

        JPanel summary=new JPanel(new GridLayout(3,2,8,8));
        summary.setBackground(card);
        summary.setBorder(new TitledBorder("Bill Summary"));
        summary.add(new JLabel("Room Cost"));summary.add(roomCost);
        summary.add(new JLabel("Tax (12%)"));summary.add(tax);
        summary.add(new JLabel("Grand Total"));summary.add(total);

        JButton calc=button("Calculate",new Color(59,130,246));
        JButton reserve=button("Reserve",new Color(16,185,129));
        JButton clear=button("Clear",new Color(239,68,68));

        JPanel btns=new JPanel();btns.setOpaque(false);
        btns.add(calc);btns.add(reserve);btns.add(clear);

        g.gridx=0;g.gridy=4;g.gridwidth=2;form.add(btns,g);
        g.gridx=2;g.gridy=0;g.gridheight=5;form.add(summary,g);

        root.add(form,BorderLayout.CENTER);

        JTable table=new JTable(model);
        table.setRowHeight(28);
        JScrollPane sp=new JScrollPane(table);
        sp.setBorder(new TitledBorder("Reservation History"));
        root.add(sp,BorderLayout.SOUTH);

        calc.addActionListener(e->calculate());
        reserve.addActionListener(e->{
            if(name.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Enter guest name");
                return;
            }
            calculate();
            model.addRow(new Object[]{name.getText(),room.getSelectedItem(),nights.getValue(),total.getText(),"Reserved"});
            JOptionPane.showMessageDialog(this,"Room Reserved Successfully!");
        });
        clear.addActionListener(e->{
            name.setText("");phone.setText("");nights.setValue(1);
            roomCost.setText("₹0");tax.setText("₹0");total.setText("₹0");
        });
    }
    void calculate(){
        int rate=switch((String)room.getSelectedItem()){
            case "Deluxe"->3500;
            case "Suite"->6000;
            default->2000;
        };
        int n=(Integer)nights.getValue();
        double sub=rate*n;
        double t=sub*0.12;
        roomCost.setText("₹"+String.format("%.2f",sub));
        tax.setText("₹"+String.format("%.2f",t));
        total.setText("₹"+String.format("%.2f",sub+t));
    }
    void addField(JPanel p,GridBagConstraints g,int y,String l,Component c){
        g.gridx=0;g.gridy=y;g.gridwidth=1;g.gridheight=1;p.add(new JLabel(l),g);
        g.gridx=1;p.add(c,g);
    }
    JButton button(String t,Color c){
        JButton b=new JButton(t);
        b.setBackground(c);b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,13));
        return b;
    }
    public static void main(String[]args){
        SwingUtilities.invokeLater(()->new HotelReservationSystem().setVisible(true));
    }
}
