import javax.swing.*;
public class AdministrativeRights extends JFrame {

    JPanel booksPanel = new JPanel();

    JPanel addNewUserPanel = new JPanel();
    JPanel addNewBookPanel = new JPanel();
    JPanel approveBookPanel = new JPanel();
    JPanel addColleaguePanel = new JPanel();
    JPanel editUserPanel = new JPanel();
    JTabbedPane tab = new JTabbedPane();
    public AdministrativeRights(){

        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tab.add(booksPanel, "Books");
        tab.add(addNewBookPanel, "Add new book");
        tab.add(approveBookPanel, "Approve requested book");
        tab.add(addColleaguePanel, "Add new colleague");
        tab.add(editUserPanel, "Edit users information");
        this.add(tab);
        this.setVisible(true);
    }
}