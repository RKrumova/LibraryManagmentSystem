import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.time.LocalDate;

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
        tableBooks.addMouseListener(new BooksMouseAction());
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
        this.setTitle("Library managment");
        this.setVisible(true);
    }
    public void messagePopUp(){ JOptionPane.showMessageDialog(this, "Invalid information"); }
    public void bookPopUp() { JOptionPane.showMessageDialog(this, "This book already exist");}
    public void messagePopUpSuccess() { JOptionPane.showMessageDialog(this, "You have succeeded");}
    public void deletePopUp(){
        //Not done
        System.out.println("Account has been deleted");
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
            String sql = "select * from userinformation where username like '%" + username.toString() + "%'";
            state = conn.prepareStatement(sql);
            result = state.executeQuery();
            tableUsers.setModel(new MyModel(result));
            System.out.println(tableUsers.getRowCount());
            //fnameT.setText(tableUsers.getValueAt(1, 1).toString());
            fnameT.setText(tableUsers.getValueAt(0, 0).toString());
            lnameT.setText(tableUsers.getValueAt(0, 1).toString());
            ageT.setText(tableUsers.getValueAt(0, 2).toString());
            passwordT.setText(tableUsers.getValueAt(0, 4).toString());
            secretQuestionT.setText(tableUsers.getValueAt(0, 5).toString());
            secretAnswerT.setText(tableUsers.getValueAt(0, 6).toString());
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
            // all
            if (titleT.getText().length() !=0 && authorT.getText().length() != 0 && isbnT.getText().length() != 0) {
                sql = "select * from books where title like '%" + titleT.getText()
                        + "%' and author like '%" + authorT.getText()
                        + "%' and isbn like '%" + isbnT.getText() + "%'";
            } // title and author
            else if (titleT.getText().length() != 0 && authorT.getText().length() != 0 && isbnT.getText().length() == 0) {
                sql = "select * from books where title like '%" + titleT.getText()
                        + "%' and author like '%" + authorT.getText() + "%'";
            } // title and isbn
            else if (titleT.getText().length() != 0 && isbnT.getText().length() != 0 && authorT.getText().length() == 0) {
                sql = "select * from books where title like '%" + titleT.getText()
                        + "%' and isbn like '%" + isbnT.getText() + "%'";
            } // author and isb
            else if (authorT.getText().length() != 0 && isbnT.getText().length() != 0 && titleT.getText().length() == 0) {
                sql = "select * from books where isbn like '%" + isbnT.getText()
                        + "%' and author like '%" + authorT.getText() + "%'";
            }  // Only title
            else if (titleT.getText().length() != 0 && authorT.getText().length() == 0 && isbnT.getText().length() == 0) {
                sql = "select * from books where title like '%" + titleT.getText() + "%'";
            }  // only author
            else if (authorT.getText().length() != 0 && titleT.getText().length() == 0 && isbnT.getText().length() == 0) {
                sql = "select * from books where author like '%" + authorT.getText() + "%'";
                System.out.println(sql + "\n\nauthor");
            } //only isbnT
            else if (isbnT.getText().length() != 0 && titleT.getText().length() == 0 && authorT.getText().length() == 0) {
                sql = "select * from books where isbn like '%" + isbnT.getText() + "%'";
                System.out.println(sql + "\n\nisbn");
            } else {
                messagePopUp();
            }
            try {
                state = conn.prepareStatement(sql);
                result = state.executeQuery();
                tableBooks.setModel(new MyModel(result));
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
    class takeAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "Insert into takenBooks(username, bookISBN, dateTaken) values (?, ?, ?)";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, username);
                state.setString(2, isbnT.getText().toString());
                state.setString(3, "");
//                  how do we take current data ?

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

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
            conn = DBConnection.getConnection();
            String sql = "insert into requestedBooks (title, author, username, approve) values (?,?,?, false)";
            try{
                    state = conn.prepareStatement(sql);
                    state.setString(1, titleT.getText());
                    state.setString(2, authorT.getText());
                    state.setString(3, username);
                    state.execute();
                    messagePopUpSuccess();
            } catch (SQLException ex) {
                messagePopUp();
                ex.printStackTrace();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
    class refreshAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            refreshBooks();
        }
    }
    class BooksMouseAction implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableBooks.getSelectedRow();
            String isbn = tableBooks.getValueAt(row, 0).toString();
            titleT.setText(tableBooks.getValueAt(row, 1).toString());
            authorT.setText(tableBooks.getValueAt(row, 2).toString());
            isbnT.setText(isbn);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    class editAccountAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "update userinformation set fname =?, lname=?, age=?, password=?, question=?, answer = ? where username=?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1,fnameT.getText());
                state.setString(2,lnameT.getText());
                state.setInt(3,Integer.parseInt(ageT.getText()));
                state.setString(4,passwordT.getText());
                state.setString(5, secretQuestionT.getText());
                state.setString(6, secretAnswerT.getText());
                state.setString(7, username);
                state.execute();
                messagePopUpSuccess();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class deleteAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from userinformation where username=?";
            try{
                state = conn.prepareStatement(sql);
                state.setString(1, username);
                state.execute();
                deletePopUp();
                setVisible(false);
                SignUpFrame signFrame = new SignUpFrame();
                signFrame.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                messagePopUp();
            }
        }
    }


}
