import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class NewPassword extends JFrame {
    Connection conn=null;
    JPanel upPanel = new JPanel();
    JPanel downPanel = new JPanel();
    JLabel usernameLabel = new JLabel();
    JLabel secretQuestionL = new JLabel("");
    JLabel questionL = new JLabel("question");
    JLabel secretAnswerL = new JLabel("Secret answer");
    JLabel newPasswordL = new JLabel("New password");
    JTextField answerT = new JTextField();
    JTextField passwordT = new JTextField();
    JButton loginButton = new JButton("Go to login");
    JButton changePassButton = new JButton("Change");
    public NewPassword(String usernameT){
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));
        upPanel.setLayout(new GridLayout(3, 2));

        upPanel.add(secretQuestionL);   upPanel.add(questionL);
        upPanel.add(secretAnswerL);     upPanel.add(answerT);
        upPanel.add(newPasswordL);      upPanel.add(passwordT);
        downPanel.add(loginButton);
        downPanel.add(changePassButton);
        loginButton.addActionListener(new GoToLogin());
        changePassButton.addActionListener(new ChangePassAction());
        this.add(upPanel);
        this.add(downPanel);
        this.setVisible(true);
    }

    class GoToLogin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        }
    }
    class ChangePassAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
