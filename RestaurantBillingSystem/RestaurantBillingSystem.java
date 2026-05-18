
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RestaurantBillingSystem extends JFrame {
    JComboBox<String> item=new JComboBox<>(new String[]{
        "Veg Burger","Pizza","Pasta","Sandwich","Cold Coffee"});
    JSpinner qty=new JSpinner(new SpinnerNumberModel(1,1,20,1));
    JTextField customer=new JTextField(15);
    JLabel sub=new JLabel("₹0"),tax=new JLabel("₹0"),total=new JLabel("₹0");
    DefaultTableModel model=new DefaultTableModel(
        new String[]{"Customer","Item","Qty","Amount"},0);

    public RestaurantBillingSystem(){
        setTitle("Restaurant Billing System");
        setSize(980,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg=new Color(248,250,252);
        Color card=Color.WHITE;
        Color primary=new Color(99,102,241);

        JPanel root=new JPanel(new BorderLayout(15,15));
        root.setBackground(bg);
        root.setBorder(new EmptyBorder(18,18,18,18));
        setContentPane(root);

        JLabel header=new JLabel("🍽 Restaurant Billing System",SwingConstants.CENTER);
        header.setOpaque(true);
        header.setBackground(primary);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI",Font.BOLD,24));
        header.setBorder(new EmptyBorder(15,10,15,10));
        root.add(header,BorderLayout.NORTH);

        JPanel form=new JPanel(new GridBagLayout());
        form.setBackground(card);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(15,15,15,15)));
        GridBagConstraints g=new GridBagConstraints();
        g.insets=new Insets(8,8,8,8);
        g.fill=GridBagConstraints.HORIZONTAL;

        addField(form,g,0,"Customer",customer);
        addField(form,g,1,"Food Item",item);
        addField(form,g,2,"Quantity",qty);

        JPanel bill=new JPanel(new GridLayout(3,2,8,8));
        bill.setBackground(card);
        bill.setBorder(new TitledBorder("Bill Summary"));
        bill.add(new JLabel("Subtotal")); bill.add(sub);
        bill.add(new JLabel("GST (5%)")); bill.add(tax);
        bill.add(new JLabel("Grand Total")); bill.add(total);

        JButton calc=btn("Calculate",new Color(59,130,246));
        JButton add=btn("Add Bill",new Color(16,185,129));
        JButton clear=btn("Clear",new Color(239,68,68));

        JPanel actions=new JPanel();
        actions.setOpaque(false);
        actions.add(calc);actions.add(add);actions.add(clear);

        g.gridx=0;g.gridy=3;g.gridwidth=2;form.add(actions,g);
        g.gridx=2;g.gridy=0;g.gridheight=4;form.add(bill,g);

        root.add(form,BorderLayout.CENTER);

        JTable table=new JTable(model);
        table.setRowHeight(28);
        JScrollPane sp=new JScrollPane(table);
        sp.setBorder(new TitledBorder("Billing History"));
        root.add(sp,BorderLayout.SOUTH);

        calc.addActionListener(e->calculate());

        add.addActionListener(e->{
            if(customer.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Enter customer name.");
                return;
            }
            calculate();
            model.addRow(new Object[]{
                customer.getText(),
                item.getSelectedItem(),
                qty.getValue(),
                total.getText()
            });
            JOptionPane.showMessageDialog(this,"Bill Generated Successfully!");
        });

        clear.addActionListener(e->{
            customer.setText("");
            qty.setValue(1);
            sub.setText("₹0");
            tax.setText("₹0");
            total.setText("₹0");
        });
    }

    void calculate(){
        int price=switch((String)item.getSelectedItem()){
            case "Pizza"->350;
            case "Pasta"->220;
            case "Sandwich"->180;
            case "Cold Coffee"->160;
            default->150;
        };
        int q=(Integer)qty.getValue();
        double s=price*q;
        double g=s*0.05;
        sub.setText("₹"+String.format("%.2f",s));
        tax.setText("₹"+String.format("%.2f",g));
        total.setText("₹"+String.format("%.2f",s+g));
    }

    void addField(JPanel p,GridBagConstraints g,int y,String text,Component c){
        g.gridx=0;g.gridy=y;g.gridwidth=1;g.gridheight=1;
        p.add(new JLabel(text),g);
        g.gridx=1;
        p.add(c,g);
    }

    JButton btn(String t,Color c){
        JButton b=new JButton(t);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,13));
        return b;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new RestaurantBillingSystem().setVisible(true));
    }
}
