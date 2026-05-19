import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class LibraryManagementSystem extends JFrame{
    JTextField student=new JTextField(15);
    JComboBox<String> book=new JComboBox<>(new String[]{
        "Clean Code","Java Complete Reference","DSA Made Easy","Atomic Habits","Operating Systems"});
    JComboBox<String> issue=new JComboBox<>(new String[]{"7 Days","14 Days","30 Days"});
    JLabel fee=new JLabel("₹0");
    DefaultTableModel model=new DefaultTableModel(
        new String[]{"Student","Book","Duration","Fee","Status"},0);

    public LibraryManagementSystem(){
        setTitle("Library Management System");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg=new Color(248,250,252);
        Color card=Color.WHITE;
        Color primary=new Color(99,102,241);

        JPanel root=new JPanel(new BorderLayout(15,15));
        root.setBackground(bg);
        root.setBorder(new EmptyBorder(18,18,18,18));
        setContentPane(root);

        JLabel title=new JLabel("📚 Library Management System",SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(primary);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setBorder(new EmptyBorder(15,10,15,10));
        root.add(title,BorderLayout.NORTH);

        JPanel form=new JPanel(new GridBagLayout());
        form.setBackground(card);
        form.setBorder(new CompoundBorder(new LineBorder(new Color(220,220,220)),new EmptyBorder(20,20,20,20)));
        GridBagConstraints g=new GridBagConstraints();
        g.insets=new Insets(8,8,8,8);
        g.fill=GridBagConstraints.HORIZONTAL;

        addField(form,g,0,"Student Name",student);
        addField(form,g,1,"Book",book);
        addField(form,g,2,"Issue Duration",issue);

        JPanel summary=new JPanel(new GridLayout(2,2,8,8));
        summary.setBackground(card);
        summary.setBorder(new TitledBorder("Issue Details"));
        summary.add(new JLabel("Library Fee"));
        summary.add(fee);

        JButton calc=button("Calculate Fee",new Color(59,130,246));
        JButton issueBtn=button("Issue Book",new Color(16,185,129));
        JButton clear=button("Clear",new Color(239,68,68));

        JPanel actions=new JPanel();
        actions.setOpaque(false);
        actions.add(calc);
        actions.add(issueBtn);
        actions.add(clear);

        g.gridx=0;g.gridy=3;g.gridwidth=2;
        form.add(actions,g);
        g.gridx=2;g.gridy=0;g.gridheight=4;
        form.add(summary,g);

        root.add(form,BorderLayout.CENTER);

        JTable table=new JTable(model);
        table.setRowHeight(28);
        JScrollPane sp=new JScrollPane(table);
        sp.setBorder(new TitledBorder("Issued Books"));
        root.add(sp,BorderLayout.SOUTH);

        calc.addActionListener(e->calculate());

        issueBtn.addActionListener(e->{
            if(student.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Enter student name.");
                return;
            }
            calculate();
            model.addRow(new Object[]{
                student.getText(),
                book.getSelectedItem(),
                issue.getSelectedItem(),
                fee.getText(),
                "Issued"
            });
            JOptionPane.showMessageDialog(this,"Book Issued Successfully!");
        });

        clear.addActionListener(e->{
            student.setText("");
            fee.setText("₹0");
            issue.setSelectedIndex(0);
            book.setSelectedIndex(0);
        });
    }

    void calculate(){
        int amount=switch((String)issue.getSelectedItem()){
            case "14 Days"->80;
            case "30 Days"->150;
            default->40;
        };
        fee.setText("₹"+amount);
    }

    void addField(JPanel p,GridBagConstraints g,int y,String lbl,Component c){
        g.gridx=0;g.gridy=y;g.gridwidth=1;g.gridheight=1;
        p.add(new JLabel(lbl),g);
        g.gridx=1;
        p.add(c,g);
    }

    JButton button(String t,Color c){
        JButton b=new JButton(t);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,13));
        return b;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new LibraryManagementSystem().setVisible(true));
    }
}
