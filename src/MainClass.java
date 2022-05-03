import javax.swing.*;
public class MainClass extends JFrame{
    public static void main(String[] args){
        //sign up
        SignUpFrame signFrame = new SignUpFrame();
        LoginFrame loginFrame = new LoginFrame();
        //dont forget ever
        signFrame.setVisible(true);
        loginFrame.setVisible(false);
        //if admin and user correct or created the close signFrame and open Student or admin

    }
}
