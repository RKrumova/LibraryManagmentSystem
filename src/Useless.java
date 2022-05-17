import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Useless extends JFrame {

    JPanel booksPanel = new JPanel();
    JPanel addNewBookPanel = new JPanel();
    JPanel approveBookPanel = new JPanel();
    JTabbedPane tabBooks = new JTabbedPane();
    public Useless(){
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //
    }
}
