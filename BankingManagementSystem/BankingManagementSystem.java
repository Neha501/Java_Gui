
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalTime;

public class BankingManagementSystem extends JFrame {

    private JTextField nameField, amountField;
    private JLabel accountLabel, balanceLabel;
    private DefaultTableModel model;
    private double balance = 10000.0;
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    public BankingManagementSystem() {
        setTitle("Banking Management System");
        setSize(950,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg = new Color(248,250,252);
        Color card = Color.WHITE;
        Color primary = new Color(99,102,241);

        JPanel root = new JPanel(new BorderLayout(15,15));
        root.setBackground(bg);
        root.setBorder(new EmptyBorder(20,20,20,20));
        setContentPane(root);

        JLabel title = new JLabel("🏦 Banking Management System",SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(primary);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setBorder(new EmptyBorder(15,10,15,10));
        root.add(title,BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(15,15));
        center.setOpaque(false);
        root.add(center,BorderLayout.CENTER);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(card);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(20,20,20,20)));
        GridBagConstraints g = new GridBagConstraints();
        g.insets=new Insets(8,8,8,8);
        g.anchor=GridBagConstraints.WEST;
        g.fill=GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(18);
        amountField = new JTextField(18);
        accountLabel = new JLabel(String.valueOf((int)(10000000+Math.random()*90000000)));
        balanceLabel = new JLabel("₹ "+df.format(balance));
        balanceLabel.setFont(new Font("Segoe UI",Font.BOLD,20));

        g.gridx=0;g.gridy=0; form.add(new JLabel("Customer Name"),g);
        g.gridx=1; form.add(nameField,g);

        g.gridx=0;g.gridy=1; form.add(new JLabel("Account Number"),g);
        g.gridx=1; form.add(accountLabel,g);

        g.gridx=0;g.gridy=2; form.add(new JLabel("Amount"),g);
        g.gridx=1; form.add(amountField,g);

        g.gridx=0;g.gridy=3; form.add(new JLabel("Current Balance"),g);
        g.gridx=1; form.add(balanceLabel,g);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        buttons.setOpaque(false);

        JButton deposit=createButton("Deposit",new Color(16,185,129));
        JButton withdraw=createButton("Withdraw",new Color(239,68,68));
        JButton transfer=createButton("Transfer",primary);
        JButton statement=createButton("Statement",new Color(245,158,11));

        buttons.add(deposit);
        buttons.add(withdraw);
        buttons.add(transfer);
        buttons.add(statement);

        g.gridx=0;g.gridy=4;g.gridwidth=2;
        form.add(buttons,g);

        center.add(form,BorderLayout.NORTH);

        model=new DefaultTableModel(new Object[]{"Type","Amount","Balance","Time"},0);
        JTable table=new JTable(model);
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,13));

        JPanel tableCard=new JPanel(new BorderLayout());
        tableCard.setBackground(card);
        tableCard.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(15,15,15,15)));
        tableCard.add(new JLabel("Transaction History"),BorderLayout.NORTH);
        tableCard.add(new JScrollPane(table),BorderLayout.CENTER);

        center.add(tableCard,BorderLayout.CENTER);

        deposit.addActionListener(e->transaction("Deposit"));
        withdraw.addActionListener(e->transaction("Withdraw"));
        transfer.addActionListener(e->transaction("Transfer"));

        statement.addActionListener(e->{
            JOptionPane.showMessageDialog(this,
                    "Customer : "+nameField.getText()+
                    "\nAccount : "+accountLabel.getText()+
                    "\nBalance : ₹ "+df.format(balance),
                    "Mini Statement",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private JButton createButton(String text, Color c){
        JButton b=new JButton(text);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,14));
        b.setBorder(new EmptyBorder(10,18,10,18));
        return b;
    }

    private void transaction(String type){
        if(nameField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Enter customer name.");
            return;
        }
        double amt;
        try{
            amt=Double.parseDouble(amountField.getText());
            if(amt<=0) throw new Exception();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Enter valid amount.");
            return;
        }

        if(type.equals("Deposit")){
            balance+=amt;
        }else{
            if(balance<amt){
                JOptionPane.showMessageDialog(this,"Insufficient Balance.");
                return;
            }
            balance-=amt;
        }

        balanceLabel.setText("₹ "+df.format(balance));
        model.addRow(new Object[]{
                type,
                "₹ "+df.format(amt),
                "₹ "+df.format(balance),
                LocalTime.now().withNano(0)
        });
        amountField.setText("");
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new BankingManagementSystem().setVisible(true));
    }
}
