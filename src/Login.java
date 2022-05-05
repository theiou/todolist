import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private String pass = "artemHELOOY1";
    public Login(){
        setTitle("Log IN");
        setBounds(400, 250, 750, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel leftBluePanel = new JPanel();
        leftBluePanel.setBackground(Color.blue);
        setResizable(false);
        leftBluePanel.setSize(400, 500);
        add(leftBluePanel);
        JLabel enterPass = new JLabel("Enter Password");
        enterPass.setFont(new Font("Arial", Font.BOLD, 25));
        add(enterPass);
        enterPass.setSize(300, 100);
        enterPass.setLocation(470, 100);
        JPasswordField jPasswordField = new JPasswordField();
        add(jPasswordField);
        jPasswordField.setSize(230, 25);
        jPasswordField.setLocation(450, 200);
        JButton apply = new JButton("Apply");
        apply.setSize(80, 36);
        apply.setLocation(522, 235);
        add(apply);
        setVisible(false);
        String a = String.valueOf(jPasswordField.getPassword());
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pass.equals(String.valueOf(jPasswordField.getPassword()))){
                    setVisible(false);
                    Main.jFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Неверный пароль");
                    jPasswordField.setText("");
                }
            }
        });
    }
}
