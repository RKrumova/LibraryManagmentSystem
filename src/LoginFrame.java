import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {
    Connection conn=null;
    PreparedStatement state=null;
    ResultSet result=null;
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    JLabel usernameL = new JLabel("Enter username");
    JLabel passwordL=new JLabel("Enter your password: ");
    JTextField usernameT=new JTextField();
    JTextField passwordT=new JTextField();
    JButton loginButton = new JButton("Login");
    JButton adminButton = new JButton("Admin login");
    JButton clearLoginButton = new JButton("Clear data");
    JButton signButton = new JButton("Sign up");
    public LoginFrame(){
        JPanel panel = new JPanel();
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setLayout(new GridLayout(2, 1));
        upPanel.setLayout(new GridLayout(2, 2));
        upPanel.add(usernameL);         upPanel.add(usernameT);
        upPanel.add(passwordL);        upPanel.add(passwordT);
        this.add(upPanel);
        downPanel.add(loginButton);
        downPanel.add(adminButton);
        downPanel.add(clearLoginButton);
        downPanel.add(signButton);
        loginButton.addActionListener(new StudentForm());
        adminButton.addActionListener(new AdminForm());
        clearLoginButton.addActionListener(new clearForm());
        signButton.addActionListener(new GoToSign());
        this.add(downPanel);
        this.setVisible(true);
    }
    public void clearForm() {
        usernameT.setText("");
        passwordT.setText("");
    }
    class clearForm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearForm();
        }
    }
    class GoToSign implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            SignUpFrame signFrame = new SignUpFrame();
            signFrame.setVisible(true);
        }
    }
    class StudentForm implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            StudentFrame studentFrame = new StudentFrame();
            studentFrame.setVisible(true);
        }
    }
    class AdminForm implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            AdministrativeRights adminRight = new AdministrativeRights();
            adminRight.setVisible(true);
        }
    }
}
