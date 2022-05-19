import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//
public class AdminFrame extends JFrame {
    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    JPanel booksPanel = new JPanel();
    JPanel addNewUserPanel = new JPanel();
    JPanel viewPanel = new JPanel();
    JPanel addColleaguePanel = new JPanel();
    JPanel upBookPanel = new JPanel();
    JPanel midBookPanel = new JPanel();
    JPanel downBookPanel = new JPanel();
    JPanel viewUpPanel = new JPanel();
    JPanel viewMidPanel = new JPanel();
    JPanel viewDownPanel = new JPanel();
    JTabbedPane tab = new JTabbedPane();
    JLabel titleLabel = new JLabel("Title");
    JLabel authorLabel = new JLabel("Author");
    JLabel quantityLabel = new JLabel("Quantity");
    JLabel isbnLabel = new JLabel("ISBN");
    JTextField titleT = new JTextField();
    JTextField authorT = new JTextField();
    JTextField quantityT = new JTextField();
    JTextField isbnT = new JTextField();
    JButton addBookButton = new JButton("Add");
    JButton editBookButton = new JButton("Edit");
    JButton removeBookButton = new JButton("Delete");
    JButton refreshBookButton = new JButton("Refresh");
    JTable tableBooks=new JTable();
    JScrollPane myBookScroll=new JScrollPane(tableBooks);
    JButton searchBookButton = new JButton("Search");
    //---------addNewUserPanel---------
    JLabel fnameL = new JLabel("First name");
    JLabel lnameL=new JLabel("LastName");
    JLabel ageL = new JLabel("Age:");
    JLabel usernameL = new JLabel("Enter username");
    JLabel passwordL=new JLabel("Enter your password: ");
    JLabel secretQuestionL = new JLabel("Enter secret question:");
    JLabel secretAnswerL = new JLabel("Answer:");
    JTextField fnameT=new JTextField();
    JTextField lnameT=new JTextField();
    JTextField ageT=new JTextField();
    JTextField usernameT=new JTextField();
    JTextField passwordT=new JTextField();
    JTextField secretQuestionT = new JTextField();
    JTextField secretAnswerT = new JTextField();
    JButton createButton = new JButton("Create new user");
    JButton editUserButton = new JButton("Edit user");
    //--------------------add new worker
    JLabel fnameLC = new JLabel("First name");
    JLabel lnameLC=new JLabel("LastName");
    JLabel ageLC = new JLabel("Age:");
    JLabel usernameLC = new JLabel("Enter username");
    JLabel passwordLC=new JLabel("Enter password: ");
    JLabel egnLC = new JLabel("EGN");
    JLabel emailLC = new JLabel("Email");
    JLabel cityLC = new JLabel("City");
    JLabel addressLC = new JLabel("Address");
    JLabel numberLC = new JLabel("Phone number");
    JTextField fnameTC=new JTextField();
    JTextField lnameTC=new JTextField();
    JTextField ageTC=new JTextField();
    JTextField usernameTC=new JTextField();
    JTextField passwordTC=new JTextField();
    JTextField egnTC = new JTextField();
    JTextField  emailTC = new JTextField();
    JTextField  cityTC = new JTextField();
    JTextField  addressTC = new JTextField();
    JTextField  numberTC = new JTextField();
    String[] item= {"Librarian", "Library Managers", "Library Directors", "Public relations", "Accounting", "Human Resources", "Technicians"};
    JComboBox<String> jobs =new JComboBox<String>(item);
    //------------------------------------------SEARCH-----------------------------------------------------------
    String[] isbnArray;
    JLabel eusernameL = new JLabel("Enter user");
    JTextField usernameEditSearchTF = new JTextField();
    JButton searchUserButton = new JButton("Search by user");
    JTable tableUsers=new JTable();
    JScrollPane usersScroll = new JScrollPane(tableUsers);
    JLabel fnameLS = new JLabel("First name");
    JLabel lnameLS=new JLabel("LastName");
    JLabel usernameLS = new JLabel("Username");
    JComboBox<String> booksCombo = new JComboBox<String>(isbnArray);
    JTextField fnameTS=new JTextField();
    JTextField lnameTS=new JTextField();
    JTextField usernameTS=new JTextField();

    public AdminFrame(){
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tab.add(booksPanel, "Books");
        tab.add(addColleaguePanel, "Add new colleague");
        tab.add(addNewUserPanel, "Add users information");
        tab.add(viewPanel, "View users");

        //books
        booksPanel.setLayout(new GridLayout(3, 1));
        booksPanel.add(upBookPanel);
        booksPanel.add(midBookPanel);
        booksPanel.add(downBookPanel);
        upBookPanel.setLayout(new GridLayout(4, 2));
        upBookPanel.add(titleLabel);      upBookPanel.add(titleT);
        upBookPanel.add(authorLabel);     upBookPanel.add(authorT);
        upBookPanel.add(quantityLabel);   upBookPanel.add(quantityT);
        upBookPanel.add(isbnLabel);       upBookPanel.add(isbnT);
        midBookPanel.add(addBookButton);
        midBookPanel.add(editBookButton);
        midBookPanel.add(removeBookButton);
        midBookPanel.add(refreshBookButton);
        midBookPanel.add(searchBookButton);
        addBookButton.addActionListener(new addBookAction());
        editBookButton.addActionListener(new editBooksAction());
        searchBookButton.addActionListener(new searchBookAction());
        removeBookButton.addActionListener(new removeBookAction());
        refreshBookButton.addActionListener(new refreshBookAction());
        myBookScroll.setPreferredSize(new Dimension(350, 150));
        downBookPanel.add(myBookScroll);
        refreshBooks();
        tableBooks.addMouseListener(new BooksMouseAction());
        // --------------------------NEW   USER
        addNewUserPanel.setLayout(new GridLayout(8,1));
        addNewUserPanel.add(fnameL);                addNewUserPanel.add(fnameT);
        addNewUserPanel.add(lnameL);                addNewUserPanel.add(lnameT);
        addNewUserPanel.add(ageL);                  addNewUserPanel.add(ageT);
        addNewUserPanel.add(usernameL);             addNewUserPanel.add(usernameT);
        addNewUserPanel.add(passwordL);             addNewUserPanel.add(passwordT);
        addNewUserPanel.add(secretQuestionL);       addNewUserPanel.add(secretQuestionT);
        addNewUserPanel.add(secretAnswerL);         addNewUserPanel.add(secretAnswerT);
        addNewUserPanel.add(createButton);          addNewUserPanel.add(editUserButton);
        createButton.addActionListener(new AddUserAction());
        editUserButton.addActionListener(new EditUserAction());
        // ------------------ NEW COLLEAGUE
        JButton creteColleagueButton = new JButton("Create new college");
        addColleaguePanel.setLayout(new GridLayout(12,1));
        addColleaguePanel.add(fnameLC);                     addColleaguePanel.add(fnameTC);
        addColleaguePanel.add(lnameLC);                     addColleaguePanel.add(lnameTC);
        addColleaguePanel.add(egnLC);                       addColleaguePanel.add(egnTC);
        addColleaguePanel.add(ageLC);                       addColleaguePanel.add(ageTC);
        addColleaguePanel.add(usernameLC);                  addColleaguePanel.add(usernameTC);
        addColleaguePanel.add(passwordLC);                  addColleaguePanel.add(passwordTC);
        addColleaguePanel.add(emailLC);                     addColleaguePanel.add(emailTC);
        addColleaguePanel.add(cityLC);                      addColleaguePanel.add(cityTC);
        addColleaguePanel.add(addressLC);                   addColleaguePanel.add(addressTC);
        addColleaguePanel.add(numberLC);                    addColleaguePanel.add(numberTC);
        addColleaguePanel.add(jobs);                        addColleaguePanel.add(creteColleagueButton);
        creteColleagueButton.addActionListener(new AddCollegeAction());
        //----------------------USERS------------------------------
        viewPanel.setLayout(new GridLayout(3, 1));
        viewUpPanel.setLayout(new GridLayout(1,2));
        viewPanel.add(viewUpPanel);
        viewPanel.add(viewMidPanel);
        viewPanel.add(viewDownPanel);
        viewUpPanel.add(usernameLS);
        viewUpPanel.add(usernameTS);
        viewMidPanel.add(searchUserButton);
        viewDownPanel.add(usersScroll);

        searchUserButton.addActionListener(new SearchUserAction());
        usersScroll.setPreferredSize(new Dimension(400, 450));
        refreshUsers();

        this.add(tab);
        this.setVisible(true);
    }

//    ----------------------------------------------------------------------------------------------
    public void messagePopUp(){
        JOptionPane.showMessageDialog(this, "Invalid information");
    }
    public void createdPopUp() { JOptionPane.showMessageDialog(this, "Successfully created account");}
    public void clearFormWorker(){
        fnameTC.setText("");
        lnameTC.setText("");
        egnTC.setText("");
        ageTC.setText("");
        usernameTC.setText("");
        passwordTC.setText("");
        emailTC.setText("");
        cityTC.setText("");
        addressTC.setText("");
        numberTC.setText("");
    }
    public void clearFormUser(){
        fnameT.setText("");
        lnameT.setText("");
        ageT.setText("");
        usernameT.setText("");
        passwordT.setText("");
        secretQuestionT.setText("");
        secretAnswerT.setText("");
    }
    public void clearFormBook(){
        titleT.setText("");
        authorT.setText("");
        quantityT.setText("");
        isbnT.setText("");
    }
    public void refreshBooks(){
        conn = DBConnection.getConnection();
        try{
        state = conn.prepareStatement("select * from books");
        result = state.executeQuery();
        tableBooks.setModel(new MyModel(result));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void refreshUsers(){
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("select fname, lname, age, username, password from userinformation;");
            state = conn.prepareStatement("select fname, lname, age, username, password from workers");
            result = state.executeQuery();
        } catch (SQLException e) {
            messagePopUp();
            e.printStackTrace();
        } catch (Exception e) {
            messagePopUp();
            e.printStackTrace();
        }
    }
    class addBookAction implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if (!isbnT.getText().isEmpty() && !titleT.getText().isEmpty() & !authorT.getText().isEmpty() && !quantityT.getText().isEmpty() && Integer.parseInt(quantityT.getText()) > 0) {
                conn = DBConnection.getConnection();
                String sql = "Insert into books(isbn, title, author, quantity) values(?, ?, ?, ?)";
                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, isbnT.getText());
                    state.setString(2, titleT.getText());
                    state.setString(3, authorT.getText());
                    state.setInt(4, Integer.parseInt(quantityT.getText()));
                    state.execute();
                    refreshBooks();
                    clearFormBook();
                } catch (SQLException ex) {
                    messagePopUp();
                    ex.printStackTrace();
                }
            } else {
                messagePopUp();
            }
        }
    }
    class editBooksAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "update books set title=?, author = ?, quantity =? where isbn = ?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, titleT.getText());
                state.setString(2, authorT.getText());
                state.setInt(3, Integer.parseInt(quantityT.getText()));
                state.setString(4, isbnT.getText());
                state.execute();
                refreshBooks();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    class searchBookAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = null;
            if (titleT.getText() != null){
                sql = "select * from books where title like '%" + titleT.getText() + "%'";
            } else if (authorT.getText() != null){
                sql = "select * from books where author like '%" + authorT.getText() + "%'";
                System.out.println(sql + "\n\nauthor");
            } else if (quantityT.getText() != null){
                sql = "select * from books where quantity like '%" + quantityT.getText() + "%'";
                System.out.println(sql + "\n\nquantity");
            }else if (isbnT.getText() != null){
                sql = "select * from books where isbn like '%" + isbnT.getText() + "%'";
                System.out.println(sql + "\n\nisbn");
            } else{
                messagePopUp();
            }
            try{
                state = conn.prepareStatement(sql);
                result = state.executeQuery();
                tableBooks.setModel(new MyModel(result));
            } catch (SQLException ex) {
                messagePopUp();
                ex.printStackTrace();
            } catch (Exception ex) {
                messagePopUp();
                ex.printStackTrace();
            }
        }
    }
    class removeBookAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "delete from books where isbn=?";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, isbnT.getText());
                state.execute();
                refreshBooks();
                clearFormBook();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
    class refreshBookAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            refreshBooks();
        }
    }
    class BooksMouseAction implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = tableBooks.getSelectedRow();
            //id = Integer.parseInt(table.getValueAt(row, 0).toString());
            String isbn = tableBooks.getValueAt(row, 0).toString();
            isbnT.setText(tableBooks.getValueAt(row, 0).toString());
            titleT.setText(tableBooks.getValueAt(row, 1).toString());
            authorT.setText(tableBooks.getValueAt(row, 2).toString());
            quantityT.setText(tableBooks.getValueAt(row, 3).toString());
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
    class AddCollegeAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "Insert into workers(fname, lname,egn, age, username,password, email, city, address,number,position) values (?,?,?,?,?,?,?,?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1, fnameTC.getText());
                state.setString(2, lnameTC.getText());
                state.setString(3, egnTC.getText());
                state.setInt(4, Integer.parseInt(ageTC.getText()));
                state.setString(5, "admin"+usernameTC.getText());
                state.setString(6, passwordTC.getText());
                state.setString(7, emailTC.getText());
                state.setString(8, cityTC.getText());
                state.setString(9, addressTC.getText());
                state.setString(10, numberTC.getText());
                state.setString(11, jobs.getSelectedItem().toString());
                state.execute();
                createdPopUp();
                clearFormWorker();
            } catch (SQLException ex) {
                ex.printStackTrace();
                messagePopUp();
            }
        }
    }
    class AddUserAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "Insert into userinformation(fname,lname, age, username, password, question, answer) values(?,?,?,?,?,?,?)";
            try {
                state = conn.prepareStatement(sql);
                state.setString(1,fnameT.getText());
                state.setString(2,lnameT.getText());
                state.setInt(3,Integer.parseInt(ageT.getText()));
                state.setString(4, usernameT.getText());
                state.setString(5, passwordT.getText());
                state.setString(6, secretQuestionT.getText());
                state.setString(7, secretAnswerT.getText());
                state.execute();
                createdPopUp();
                clearFormUser();
            } catch (SQLException ex) {
                messagePopUp();
                ex.printStackTrace();
            }
        }
    }
    class EditUserAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            /*
            conn = DBConnection.getConnection();
            if (usernameTE.getText().contains("admin")){
                String sql = "update userinformation set fname=?, lname=?, age=?, username=?, password=?";
                state.setString();
                state.setString();
                state.setString();
                state.setString();
                state.setString();
                state.executeQuery();
            } else{
                String sql = "update workers set fname=?, lname=?, age=?, city=?, address=?, number=?";
                state.setString();
                state.setString();
                state.setString();
                state.setString();
                state.setString();
                state.setString();
                state.setString();
                state.executeQuery();
            }
        }
     */
        }
    }
    class SearchUserAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            // How to print data from both tables
            String sql = "select fname, lname, age, username, password from userinformation where username like '%" + usernameEditSearchTF.getText() + "%';" +
                    "select fname, lname, age, username, password from workers where username like '%" +  usernameEditSearchTF.getText() + "%'";
            try {
                state = conn.prepareStatement(sql);
                result = state.executeQuery();
                tableUsers.setModel(new MyModel(result));
            } catch (SQLException ex) {
                messagePopUp();
                ex.printStackTrace();
            } catch (Exception ex) {
                messagePopUp();
                ex.printStackTrace();
            }
        }
    }
}