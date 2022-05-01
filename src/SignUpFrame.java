import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//signFrame in main
public class SignUpFrame extends MainClass{


    //make panels
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    //
    JLabel fnameL = new JLabel("First name");
    JLabel lnameL=new JLabel("LastName");
    JLabel ageL = new JLabel("Age:");
    JLabel emailL = new JLabel("Email address:");
    JTextField fnameT=new JTextField();
    JTextField lnameT=new JTextField();
    JTextField ageT=new JTextField();
    JTextField emailT = new JTextField();
    //login information
    JLabel usernameL = new JLabel("Enter valid username");
    JLabel password1L=new JLabel("Enter your password: ");
    JLabel password2L=new JLabel("Enter your password again: ");
    JLabel secretQuestionL = new JLabel("Enter secret question:");
    JLabel secretAnswerL = new JLabel("Answer:");
    JTextField usernameT=new JTextField();
    JTextField password1T=new JTextField();
    JTextField password2T=new JTextField();
    JTextField secretQuestionT = new JTextField();
    JTextField secretAnswerT = new JTextField();

    public SignUpFrame (){
        //SignUpFrame signFrame = new SignUpFrame();
        JPanel panel = new JPanel();
        signFrame.setSize(400,600);
        signFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signFrame.add(panel);
        upPanel.setLayout(new GridLayout(5, 2));
        upPanel.add(fnameL);            upPanel.add(fnameT);
        upPanel.add(lnameL);            upPanel.add(lnameT);
        upPanel.add(ageL);              upPanel.add(ageT);
        upPanel.add(usernameL);         upPanel.add(usernameT);
        upPanel.add(password1L);        upPanel.add(password1T);
        upPanel.add(password2L);        upPanel.add(password2T);
        upPanel.add(secretQuestionL);   upPanel.add(secretQuestionT);
        upPanel.add(secretAnswerL);     upPanel.add(secretAnswerT);
        signFrame.setVisible(true);
    }
}
