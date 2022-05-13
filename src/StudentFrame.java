import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentFrame extends JFrame {
    Connection conn=null;
    PreparedStatement state=null;
    ResultSet result=null;
    JPanel bookPanel = new JPanel();
    JPanel takenPanel = new JPanel();
    JPanel listPanel = new JPanel();
    JPanel findBookPanel = new JPanel();
    JPanel profilePanel = new JPanel();
    JTabbedPane tab = new JTabbedPane();
    public StudentFrame() {
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tab.add(bookPanel, "Books");
        tab.add(profilePanel, "Prolile");
        bookPanel.setLayout(new GridLayout(3,1));
        bookPanel.add(takenPanel);
        bookPanel.add(findBookPanel);
        bookPanel.add(listPanel);



        this.setVisible(true);
    }
}
