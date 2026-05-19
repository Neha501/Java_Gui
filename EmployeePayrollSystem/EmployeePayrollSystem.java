
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeePayrollSystem extends JFrame {

    JTextField nameField = new JTextField(15);
    JTextField idField = new JTextField(15);
    JTextField salaryField = new JTextField(15);

    JComboBox<String> deptBox = new JComboBox<>(new String[]{
            "IT","HR","Finance","Marketing","Sales"
    });

    JLabel hraLabel=new JLabel("₹0");
    JLabel daLabel=new JLabel("₹0");
    JLabel taxLabel=new JLabel("₹0");
    JLabel netLabel=new JLabel("₹0");

    DefaultTableModel model=new DefaultTableModel(
            new String[]{"Name","ID","Department","Net Salary"},0);

    public EmployeePayrollSystem(){

        setTitle("Employee Payroll Management");
        setSize(950,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg=new Color(248,250,252);

        JPanel root=new JPanel(new BorderLayout(15,15));
        root.setBackground(bg);
        root.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setContentPane(root);

        JLabel title=new JLabel("Employee Payroll Management",SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(new Color(99,102,241));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setBorder(BorderFactory.createEmptyBorder(15,10,15,10));
        root.add(title,BorderLayout.NORTH);

        JPanel form=new JPanel(new GridLayout(8,2,10,10));
        form.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        form.add(new JLabel("Employee Name"));
        form.add(nameField);

        form.add(new JLabel("Employee ID"));
        form.add(idField);

        form.add(new JLabel("Department"));
        form.add(deptBox);

        form.add(new JLabel("Basic Salary"));
        form.add(salaryField);

        form.add(new JLabel("HRA (20%)"));
        form.add(hraLabel);

        form.add(new JLabel("DA (10%)"));
        form.add(daLabel);

        form.add(new JLabel("Tax (5%)"));
        form.add(taxLabel);

        form.add(new JLabel("Net Salary"));
        form.add(netLabel);

        JPanel center=new JPanel(new BorderLayout());
        center.add(form,BorderLayout.NORTH);

        JTable table=new JTable(model);
        center.add(new JScrollPane(table),BorderLayout.CENTER);

        root.add(center,BorderLayout.CENTER);

        JPanel buttons=new JPanel();

        JButton calc=new JButton("Calculate Salary");
        JButton slip=new JButton("Generate Slip");
        JButton clear=new JButton("Clear");

        buttons.add(calc);
        buttons.add(slip);
        buttons.add(clear);

        root.add(buttons,BorderLayout.SOUTH);

        calc.addActionListener(e->calculate());

        slip.addActionListener(e->{
            if(nameField.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Enter employee details.");
                return;
            }
            calculate();
            model.addRow(new Object[]{
                    nameField.getText(),
                    idField.getText(),
                    deptBox.getSelectedItem(),
                    netLabel.getText()
            });

            JOptionPane.showMessageDialog(this,
                    "Salary Slip\n\n"+
                    "Employee : "+nameField.getText()+
                    "\nDepartment : "+deptBox.getSelectedItem()+
                    "\nNet Salary : "+netLabel.getText());
        });

        clear.addActionListener(e->{
            nameField.setText("");
            idField.setText("");
            salaryField.setText("");
            hraLabel.setText("₹0");
            daLabel.setText("₹0");
            taxLabel.setText("₹0");
            netLabel.setText("₹0");
        });
    }

    void calculate(){
        try{
            double salary=Double.parseDouble(salaryField.getText());
            double hra=salary*0.20;
            double da=salary*0.10;
            double tax=salary*0.05;
            double net=salary+hra+da-tax;

            hraLabel.setText("₹"+String.format("%.2f",hra));
            daLabel.setText("₹"+String.format("%.2f",da));
            taxLabel.setText("₹"+String.format("%.2f",tax));
            netLabel.setText("₹"+String.format("%.2f",net));

        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Enter valid salary.");
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new EmployeePayrollSystem().setVisible(true));
    }
}
