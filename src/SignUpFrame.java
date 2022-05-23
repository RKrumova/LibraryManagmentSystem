import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
//signFrame in main
public class SignUpFrame extends JFrame{
    Connection conn = null;
    PreparedStatement state = null;

    String errorMessage = "";
    //make panels
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    //Adding information
    JLabel fnameL = new JLabel("First name");
    JLabel lnameL=new JLabel("LastName");
    JLabel ageL = new JLabel("Age:");
    JLabel usernameL = new JLabel("Enter username");
    JLabel passwordL=new JLabel("Enter your password: ");
    JLabel secretQuestionL = new JLabel("Enter secret question:");
    JLabel secretAnswerL = new JLabel("Answer:");
    JLabel errorLabel = new JLabel(errorMessage);
    JTextField fnameT=new JTextField();
    JTextField lnameT=new JTextField();
    JTextField ageT=new JTextField();
    JTextField emailT = new JTextField();
    JTextField usernameT=new JTextField();
    JTextField passwordT=new JTextField();
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
        upPanel.setLayout(new GridLayout(7, 1));
        upPanel.add(fnameL);            upPanel.add(fnameT);
        upPanel.add(lnameL);            upPanel.add(lnameT);
        upPanel.add(ageL);              upPanel.add(ageT);
        upPanel.add(usernameL);         upPanel.add(usernameT);
        upPanel.add(passwordL);        upPanel.add(passwordT);
        upPanel.add(secretQuestionL);   upPanel.add(secretQuestionT);
        upPanel.add(secretAnswerL);     upPanel.add(secretAnswerT);
        this.add(upPanel);
        //downPanel
        downPanel.add(errorLabel);
        downPanel.add(createButton);
        createButton.addActionListener(new AddAction());
        downPanel.add(loginButton);
        loginButton.addActionListener(new GoToLogin());
        downPanel.add(clearButton);
        clearButton.addActionListener(new ClearAction());
        //clearButton.addActionListener(new SignUpFrame.clearForm());
        this.add(downPanel);
        this.setTitle("Library managment");
        this.setVisible(true);
    }
    public void clearForm() {
        fnameT.setText("");
        lnameT.setText("");
        ageT.setText("");
        emailT.setText("");
        usernameT.setText("");
        passwordT.setText("");
        secretQuestionT.setText("");
        secretAnswerT.setText("");

    }

    class AddAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "Insert into userinformation(fname,lname, age, username, password, question, answer) values(?,?,?,?,?,?,?)";
            try {
                state=conn.prepareStatement(sql);
                state.setString(1,fnameT.getText());
                state.setString(2,lnameT.getText());
                state.setInt(3,Integer.parseInt(ageT.getText()));
                state.setString(4, usernameT.getText());
                state.setString(5, passwordT.getText());
                state.setString(6, secretQuestionT.getText());
                state.setString(7, secretAnswerT.getText());
                state.execute();
                errorMessage = "Account has been created";
                clearForm();

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
}
