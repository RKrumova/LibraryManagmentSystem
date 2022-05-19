import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class NewPassword extends JFrame {
    Connection conn=null;
    static String username;
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    JLabel usernameLabel = new JLabel("Your username: ");
    JLabel questionL = new JLabel("Question: ");
    JLabel secretAnswerL = new JLabel("Secret answer: ");
    JLabel newPasswordL = new JLabel("New password: ");
    JTextField usernameT = new JTextField();
    JLabel secretQuestion = new JLabel();
    JTextField answerT = new JTextField();
    JTextField passwordT = new JTextField();
    JButton searchUserButton = new JButton("Search user");
    JButton loginButton = new JButton("Go to login");
    JButton signButton = new JButton("Go to sign");
    JButton changePassButton = new JButton("Change");
    public NewPassword(){
        if(username.length()>0) {
            usernameT.setText(username);
        }
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));
        upPanel.setLayout(new GridLayout(4, 2));
        upPanel.add(usernameLabel);     upPanel.add(usernameT);
        upPanel.add(questionL);         upPanel.add(secretQuestion);
        upPanel.add(secretAnswerL);     upPanel.add(answerT);
        upPanel.add(newPasswordL);      upPanel.add(passwordT);
        downPanel.add(searchUserButton);        downPanel.add(changePassButton);
        downPanel.add(loginButton);             downPanel.add(signButton);
        System.out.println("in public \n" + usernameT.getText());
        signButton.addActionListener(new GoToSign());
        loginButton.addActionListener(new GoToLogin());
        changePassButton.addActionListener(new ChangePassAction());
        searchUserButton.addActionListener(new searchUserAction());
        this.add(upPanel);
        this.add(downPanel);
        this.setVisible(true);
    }
    public void messagePopUp(){
        JOptionPane.showMessageDialog(this, "You have changed you password");
    }
    public void wrongDataPopUp(String wrong){
        JOptionPane.showMessageDialog(this, "Wrong "+ wrong +". Try again" +
                "\nYou can ask someone who works at the library to help with your account.");
    }
    class searchUserAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("in action perform \n" + usernameT.getText());
            conn = DBConnection.getConnection();
            try{
                String sql = "select question from userinformation where username = '" + usernameT.getText() + "'";
                PreparedStatement state = conn.prepareStatement(sql);
                ResultSet resultSet = state.executeQuery();
                if (resultSet.next()) {
                    String quest = resultSet.getObject(1).toString();
                    secretQuestion.setText(quest);
                } else {
                    wrongDataPopUp("username");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class ChangePassAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            try {
                String sql = "select answer from userinformation where username = '" + usernameT.getText().toString() + "';";
                PreparedStatement state = conn.prepareStatement(sql);
                ResultSet resultSet = state.executeQuery();
                if (resultSet.next()){
                    String correctAnswer = resultSet.getObject(1).toString();
                    System.out.println(correctAnswer);
                    if( correctAnswer.equals(answerT.getText().toString())){
                        if(answerT.getText().length() > 0 ) {
                            sql = "update userinformation set password=? where username = ? and answer = ?";
                            state = conn.prepareStatement(sql);
                            state.setString(1, passwordT.getText());
                            state.setString(2, usernameT.getText());
                            state.setString(3, answerT.getText());
                            state.execute();
                            messagePopUp();
                        } else{
                            wrongDataPopUp("password");
                        }
                    } else {
                        wrongDataPopUp("answer");
                    }
                } else {
                    wrongDataPopUp("username");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
    class GoToLogin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        }
    }

}
