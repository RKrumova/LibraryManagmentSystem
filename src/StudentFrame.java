import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFrame extends JFrame {
    static String username;
    Connection conn=null;
    PreparedStatement state=null;
    ResultSet result=null;
    JPanel bookPanel = new JPanel();
    JPanel profilePanel = new JPanel();
    JTabbedPane tab = new JTabbedPane();
//                Books tab
    JPanel upBookPanel = new JPanel();
    JPanel buttonsBookPanel = new JPanel();
    JPanel takenBookPanel = new JPanel();
    JPanel allBookPanel = new JPanel();
    JLabel titleLabel = new JLabel("Title");
    JLabel authorLabel = new JLabel("Author");
    JLabel isbnL = new JLabel("ISBN");
    JTextField titleT = new JTextField();
    JTextField authorT = new JTextField();
    JTextField isbnT = new JTextField();
    JButton searchBookButton = new JButton("search book");
    JButton takeBookButton = new JButton("Take book");
    JButton requestBookButton = new JButton("Request book");
    JButton clearButton= new JButton("Clear form");
    JButton refreshButton = new JButton("Refresh table");
    JTable tableBooks = new JTable();
    JScrollPane booksScroll = new JScrollPane(tableBooks);
    JTable takenBooks = new JTable();
    JScrollPane takenScroll = new JScrollPane(takenBooks);
//                Profile tab
    JLabel message = new JLabel("You can and shouldnt edit username");
    JLabel fnameL = new JLabel("First name");
    JLabel lnameL=new JLabel("LastName");
    JLabel ageL = new JLabel("Age:");
    JLabel usernameL = new JLabel("You can't edit your username");
    JLabel passwordL=new JLabel("Password: ");
    JLabel secretQuestionL = new JLabel("Secret question:");
    JLabel secretAnswerL = new JLabel("Answer:");
    JTextField fnameT=new JTextField();
    JTextField lnameT=new JTextField();
    JTextField ageT=new JTextField();
    JLabel usernameTL = new JLabel("username: " + username);
    JTextField passwordT=new JTextField();
    JTextField secretQuestionT = new JTextField();
    JTextField secretAnswerT = new JTextField();
    JButton editButton = new JButton("Edit information");
    JButton deleteButton = new JButton("DELETE");
    JTable tableUsers = new JTable();
    public StudentFrame(){
        System.out.println("I am in public student and I am " + username);
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tab);
        tab.add(bookPanel, "Books");
        tab.add(profilePanel, "Prolile");
        bookPanel.setLayout(new GridLayout(4,1));
        bookPanel.add(upBookPanel);
        bookPanel.add(buttonsBookPanel);
        bookPanel.add(takenBookPanel);
        bookPanel.add(allBookPanel);
        upBookPanel.setLayout(new GridLayout(3, 2));
        upBookPanel.add(titleLabel);                          upBookPanel.add(titleT);
        upBookPanel.add(authorLabel);                         upBookPanel.add(authorT);
        upBookPanel.add(isbnL);                               upBookPanel.add(isbnT);
        buttonsBookPanel.add(searchBookButton);
        buttonsBookPanel.add(takeBookButton);
        buttonsBookPanel.add(requestBookButton);
        buttonsBookPanel.add(refreshButton);
        buttonsBookPanel.add(clearButton);
        searchBookButton.addActionListener(new searchAction());
        takeBookButton.addActionListener(new takeAction());
        requestBookButton.addActionListener(new requestAction());
        clearButton.addActionListener(new clearBookAction());
        refreshButton.addActionListener(new refreshAction());
        takenScroll.setPreferredSize(new Dimension(350, 150));
        takenBookPanel.add(takenScroll);
        booksScroll.setPreferredSize(new Dimension(350, 150));
        allBookPanel.add(booksScroll);
        clearBookForm();
        refreshBooks();
        profilePanel.setLayout(new GridLayout(8, 2));
        profilePanel.add(usernameL);             profilePanel.add(usernameTL);
        profilePanel.add(fnameL);                profilePanel.add(fnameT);
        profilePanel.add(lnameL);                profilePanel.add(lnameT);
        profilePanel.add(ageL);                  profilePanel.add(ageT);
        profilePanel.add(passwordL);             profilePanel.add(passwordT);
        profilePanel.add(secretQuestionL);       profilePanel.add(secretQuestionT);
        profilePanel.add(secretAnswerL);         profilePanel.add(secretAnswerT);
        profilePanel.add(editButton);            profilePanel.add(deleteButton);
        editButton.addActionListener(new editAccountAction());
        deleteButton.addActionListener(new deleteAction());
        fillDataUser();
        this.setVisible(true);
    }
    public void messagePopUp(){ JOptionPane.showMessageDialog(this, "Invalid information"); }
    public void deletePopUp(){
        //Not done
        System.out.println("NOt done");
    }
    public void clearBookForm() {
        titleT.setText("");
        authorT.setText("");
        isbnT.setText("");
    }
    public void refreshBooks(){
        conn = DBConnection.getConnection();
        try{
            state = conn.prepareStatement("select * from books");
            result = state.executeQuery();
            tableBooks.setModel(new MyModel(result));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillDataUser(){

        conn = DBConnection.getConnection();
        try {
            System.out.println("I go in the try");
            state = conn.prepareStatement("select * from userinformation where username = '%" + username.toString() + "%'");
            result = state.executeQuery();
            tableUsers.setModel(new MyModel(result));
            System.out.println("rows: " + tableUsers.getRowCount() + "\ncolums: " + tableUsers.getColumnCount());
            fnameT.setText(tableUsers.getValueAt(0, 1).toString());
            lnameT.setText(tableUsers.getValueAt(0, 2).toString());
            ageT.setText(tableUsers.getValueAt(0, 3).toString());
            passwordT.setText(tableUsers.getValueAt(0, 5).toString());
            secretQuestionT.setText(tableUsers.getValueAt(0, 6).toString());
            secretAnswerT.setText(tableUsers.getValueAt(0, 7).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //DONT LET ME PASS
    }
    class searchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = null;
/*
            // all
            if (titleT.getText() != null && authorT.getText() != null && isbnT.getText() != null) {
                sql = "select * from books where title like '%" + titleT.getText()
                        + "%' and author like '%" + authorT.getText()
                        + "%' and isbn like '%" + isbnT + "%'";
            } // title and author
            else if (titleT.getText() != null && authorT.getText() != null && isbnT.getText() == null) {
                sql = "select * from books where title like '%" + titleT.getText()
                        + "%' and author like '%" + authorT.getText() + "%'";
            } // title and isbn
            else if (titleT.getText() != null && isbnT.getText() != null && authorT.getText() == null) {
                sql = "select * from books where title like '%" + titleT.getText()
                        + "%' and isbn like '%" + isbnT + "%'";
            } // author and isb
            else if (authorT.getText() != null && isbnT.getText() != null && titleT.getText() == null) {
                sql = "select * from books where isbn like '%" + isbnT.getText()
                        + "%' and author like '%" + authorT.getText() + "%'";
            }  // Only title
            else if (titleT.getText() != null && authorT.getText() == null && isbnT.getText() == null) {
                sql = "select * from books where title like '%" + titleT.getText() + "%'";
            }  // only author
            else if (authorT.getText() != null && titleT.getText() == null && isbnT.getText() == null) {
                sql = "select * from books where author like '%" + authorT.getText() + "%'";
                System.out.println(sql + "\n\nauthor");
            } //only isbnT
            else if (isbnT.getText() != null && titleT.getText() == null && authorT.getText() == null) {
                sql = "select * from books where isbn like '%" + isbnT.getText() + "%'";
                System.out.println(sql + "\n\nisbn");
            } //incorect output */
            if (titleT.getText() != null) {
                sql = "select * from books where title like '%" + titleT.getText() + "%'";
                try {
                    state = conn.prepareStatement(sql);
                    result = state.executeQuery();
                    tableBooks.setModel(new MyModel(result));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                messagePopUp();
            }

        }
    }
    class takeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            messagePopUp();
        }
    }
    class clearBookAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            clearBookForm();
        }
    }
    class requestAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            messagePopUp();
        }
    }
    class refreshAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            refreshBooks();
        }
    }
    class editAccountAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    class deleteAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            deletePopUp();
        }
    }
}
