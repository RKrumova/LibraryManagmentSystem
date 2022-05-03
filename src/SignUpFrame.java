import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

//signFrame in main
public class SignUpFrame extends JFrame{
    Connection conn=null;
    PreparedStatement state=null;
    ResultSet result=null;
    int id=-1;
    //make panels
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    //Adding information
    JLabel fnameL = new JLabel("First name");
    JLabel lnameL=new JLabel("LastName");
    JLabel ageL = new JLabel("Age:");
    JLabel emailL = new JLabel("Email address:");
    JTextField fnameT=new JTextField();
    JTextField lnameT=new JTextField();
    JTextField ageT=new JTextField();
    JTextField emailT = new JTextField();
    //login information
    JLabel usernameL = new JLabel("Enter username");
    JLabel password1L=new JLabel("Enter your password: ");
    JLabel password2L=new JLabel("Enter your password again: ");
    JLabel secretQuestionL = new JLabel("Enter secret question:");
    JLabel secretAnswerL = new JLabel("Answer:");
    JTextField usernameT=new JTextField();
    JTextField password1T=new JTextField();
    JTextField password2T=new JTextField();
    JTextField secretQuestionT = new JTextField();
    JTextField secretAnswerT = new JTextField();
    //buttons
    JButton createButton = new JButton("Create new user");
    JButton loginButton = new JButton("Go to login");
    JButton clearButton = new JButton("Clear data");
    public SignUpFrame (){
        //SignUpFrame signFrame = new SignUpFrame();
        JPanel panel = new JPanel();
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setLayout(new GridLayout(2, 1));
        upPanel.setLayout(new GridLayout(8, 1));
        upPanel.add(fnameL);            upPanel.add(fnameT);
        upPanel.add(lnameL);            upPanel.add(lnameT);
        upPanel.add(ageL);              upPanel.add(ageT);
        upPanel.add(usernameL);         upPanel.add(usernameT);
        upPanel.add(password1L);        upPanel.add(password1T);
        upPanel.add(password2L);        upPanel.add(password2T);
        upPanel.add(secretQuestionL);   upPanel.add(secretQuestionT);
        upPanel.add(secretAnswerL);     upPanel.add(secretAnswerT);
        this.add(upPanel);
        //downPanel
        downPanel.add(createButton);
        createButton.addActionListener(new AddAction());
        downPanel.add(loginButton);
        loginButton.addActionListener(new GoToLogin());
        downPanel.add(clearButton);
        clearButton.addActionListener(new ClearAction());
        //clearButton.addActionListener(new SignUpFrame.clearForm());
        this.add(downPanel);
        this.setVisible(true);
    }
    public void clearForm() {
        fnameT.setText("");
        lnameT.setText("");
        ageT.setText("");
        emailT.setText("");
        usernameT.setText("");
        password1T.setText("");
        password2T.setText("");
        secretQuestionT.setText("");
        secretAnswerT.setText("");
    }
    public boolean passwordCheck(){
        if(password1T.equals(password2T)){
            return true;
        } else{
            //Print that the two passwords dot match
            return false;
        }
    }
    class AddAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String sql = "Insert into userinformation(fname,lname,sex,age,salary,hotel,room)" + "values(?,?,?,?,?,?,?)";
            try {
                state=conn.prepareStatement(sql);
                state.setString(1,fnameT.getText());
                state.setString(2,lnameT.getText());
                state.setInt(3,Integer.parseInt(ageT.getText()));
                state.setString(4, usernameT.getText());
                state.setString(5, password1T.getText());
                state.setString(6, secretQuestionT.getText());
                state.setString(7, secretAnswerT.getText());
                state.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class ClearAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clearForm();
        }
    }
    class GoToLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        }
    }
    class MouseAction implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //int
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
