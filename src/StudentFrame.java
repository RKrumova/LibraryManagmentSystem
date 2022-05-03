import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentFrame extends JFrame {
    Connection conn=null;
    PreparedStatement state=null;
    ResultSet result=null;

    JPanel booksPanel = new JPanel();
    JPanel takenPanel = new JPanel();
    JPanel midPanel = new JPanel(); //DONT FORGET TO RENAME IT
    JPanel allBooksPanel = new JPanel();
    //profile
    JPanel profilePanel = new JPanel();
    JTabbedPane tab = new JTabbedPane();
    public StudentFrame() {
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        booksPanel.setLayout(new GridLayout(3, 1));
        profilePanel.setLayout(new GridLayout(2, 1));

        this.setVisible(true);
    }
}
