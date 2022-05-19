import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {
    Connection conn=null;
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    JLabel usernameL = new JLabel("Enter username");
    JLabel passwordL=new JLabel("Enter your password: ");
    JTextField usernameT=new JTextField();
    JPasswordField passwordT = new JPasswordField();
    //JTextField passwordT=new JTextField();
    JLabel errorMessage = new JLabel("");
    JButton loginButton = new JButton("Login");
    JButton signButton = new JButton("Sign up");
    JButton forgotPasswordButton = new JButton("Forgot password");
    JButton clearLoginButton = new JButton("Clear data");
    public LoginFrame(){
        JPanel panel = new JPanel();
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setLayout(new GridLayout(2, 1));
        upPanel.setLayout(new GridLayout(2,2));
        upPanel.add(usernameL);         upPanel.add(usernameT);
        upPanel.add(passwordL);        upPanel.add(passwordT);
        this.add(upPanel);
        downPanel.add(errorMessage);
        downPanel.add(loginButton);
        downPanel.add(signButton);
        downPanel.add(forgotPasswordButton);
        downPanel.add(clearLoginButton);
        loginButton.addActionListener(new loginAction());
        signButton.addActionListener(new GoToSign());
        forgotPasswordButton.addActionListener(new newPasswordAction());
        clearLoginButton.addActionListener(new clearForm());
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
            errorMessage.setText("");
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
    class loginAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                conn = DBConnection.getConnection();
                String username = usernameT.getText();
                String password = passwordT.getText();
                Statement statement = conn.createStatement();
                if(username.contains("admin")) {
                    String sql = "select * from workers where username='" + username + "' and password  ='" + password +"'";
                    ResultSet resultSet = statement.executeQuery(sql);
                    if(resultSet.next()){
                        setVisible(false);
                        AdminFrame adminRight = new AdminFrame();
                        adminRight.setVisible(true);
                    } else {
                        clearForm();
                        errorMessage.setText("Wrong user or password");
                    }

                } else {
                    String sql = "select * from userinformation where username='" + username + "' and password  ='" + password +"'";
                    System.out.println(sql);
                    ResultSet resultSet = statement.executeQuery(sql);

                    if(resultSet.next()) {
                        setVisible(false);
                        StudentFrame.username = username;
                        StudentFrame studentFrame = new StudentFrame();
                        studentFrame.setVisible(true);
                    } else {
                        clearForm();
                        errorMessage.setText("Wrong user or password");
                    }
                }
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class newPasswordAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            NewPassword.username = usernameT.getText();
            NewPassword newPassword = new NewPassword();
            newPassword.setVisible(true);
        }
    }

}
