import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

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
    JPanel buttonBookPanel = new JPanel();
    JPanel requestedBooksPanel = new JPanel();
    JPanel downBookPanel = new JPanel();
    JPanel viewUpPanel = new JPanel();
    JPanel viewButtonsPanel = new JPanel();
    JPanel viewRequestPanel = new JPanel();

    JPanel viewUsersPanel = new JPanel();
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
    JTable requestedBooks = new JTable();
    JScrollPane requestScroll = new JScrollPane(requestedBooks);
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
    String[] jobsItem= {"Librarian", "Library Managers", "Library Directors", "Public relations", "Accounting", "Human Resources", "Technicians"};
    JComboBox<String> jobs =new JComboBox<String>(jobsItem);
    //------------------------------------------SEARCH-----------------------------------------------------------
    //?????????????????????????????????????????????????
    JLabel usernameLS = new JLabel("Username");
    JLabel titleLS = new JLabel("Title");
    JLabel authorLS = new JLabel("Author");
    JTextField usernameTS=new JTextField();
    JTextField titleTS = new JTextField();
    JTextField authorTS = new JTextField();
    JButton searchUserButton = new JButton("Search user");
    JButton searchUserRequests = new JButton("Search requests");
    JButton cleanSearchButton = new JButton("Clean");
    JButton refreshSearchButton = new JButton("Refresh");
    JTable tableUsers=new JTable();
    JTable requestTable = new JTable();
    JScrollPane usersScroll = new JScrollPane(tableUsers);
    JScrollPane requestSearchScroll = new JScrollPane(requestTable);//
    public AdminFrame(){
        this.setSize(400,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tab.add(booksPanel, "Books");
        tab.add(addColleaguePanel, "Add new colleague");
        tab.add(addNewUserPanel, "Add users information");
        tab.add(viewPanel, "View users");

        //books
        booksPanel.setLayout(new GridLayout(4, 1));
        booksPanel.add(upBookPanel);
        booksPanel.add(buttonBookPanel);
        booksPanel.add(requestedBooksPanel);
        booksPanel.add(downBookPanel);
        upBookPanel.setLayout(new GridLayout(4, 2));
        upBookPanel.add(titleLabel);      upBookPanel.add(titleT);
        upBookPanel.add(authorLabel);     upBookPanel.add(authorT);
        upBookPanel.add(quantityLabel);   upBookPanel.add(quantityT);
        upBookPanel.add(isbnLabel);       upBookPanel.add(isbnT);
        buttonBookPanel.add(addBookButton);
        buttonBookPanel.add(editBookButton);
        buttonBookPanel.add(removeBookButton);
        buttonBookPanel.add(refreshBookButton);
        buttonBookPanel.add(searchBookButton);
        addBookButton.addActionListener(new addBookAction());
        editBookButton.addActionListener(new editBooksAction());
        searchBookButton.addActionListener(new searchBookAction());
        removeBookButton.addActionListener(new removeBookAction());
        refreshBookButton.addActionListener(new refreshBookAction());
        myBookScroll.setPreferredSize(new Dimension(350, 150));
        requestScroll.setPreferredSize(new Dimension(350, 150));
        downBookPanel.add(myBookScroll);
        requestedBooksPanel.add(requestScroll);
        refreshBooks();
        refreshRequest();
        requestedBooks.addMouseListener(new RequestedMouseAction());
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
        //----------------------view------------------------------
        viewPanel.setLayout(new GridLayout(4, 1));
        viewUpPanel.setLayout(new GridLayout(3,2));
        viewPanel.add(viewUpPanel);
        viewPanel.add(viewButtonsPanel);
        viewPanel.add(viewUsersPanel);
        viewPanel.add(viewRequestPanel);
        viewUpPanel.add(usernameLS);                viewUpPanel.add(usernameTS);
        viewUpPanel.add(titleLS);                viewUpPanel.add(titleTS);
        viewUpPanel.add(authorLS);                viewUpPanel.add(authorTS);
        viewButtonsPanel.add(searchUserButton);
        viewButtonsPanel.add(searchUserRequests);
        viewButtonsPanel.add(refreshSearchButton);
        viewButtonsPanel.add(cleanSearchButton);
        viewRequestPanel.add(requestSearchScroll);
        viewUsersPanel.add(usersScroll);
        searchUserRequests.addActionListener(new userRequestAction());
        searchUserButton.addActionListener(new SearchUserAction());
        refreshSearchButton.addActionListener(new refreshSearchAction());
        cleanSearchButton.addActionListener(new cleanSearchAction());
        usersScroll.setPreferredSize(new Dimension(350, 150));
        requestSearchScroll.setPreferredSize(new Dimension(350, 150));
        refreshUsers();
        refreshSearch();
        refreshUserSearch();
        this.add(tab);
        setTitle("Library managment: admin");
        this.setVisible(true);
    }

//    ----------------------------------------------------------------------------------------------
    public void messagePopUp(){
        JOptionPane.showMessageDialog(this, "Invalid information");
    }
    public void createdPopUp(String item) { JOptionPane.showMessageDialog(this, "Successfully created " + item);}
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
        jobs.setSelectedIndex(0);
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
    public void refreshRequest(){
        conn = DBConnection.getConnection();
        try{
            state = conn.prepareStatement("SELECT id, title, author, username FROM REQUESTEDBOOKS where approve = false");
            result = state.executeQuery();
            requestedBooks.setModel(new MyModel(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refreshUsers(){
        conn = DBConnection.getConnection();
        try {
            //GET ERROR HERE BECAUSE IT ISNT WORKING CORRECTLY
            state = conn.prepareStatement("select fname, lname, age, username, password from userinformation;");
            //state = conn.prepareStatement("select fname, lname, age, username, password from workers");
            result = state.executeQuery();
        } catch (SQLException e) {
            messagePopUp();
            e.printStackTrace();
        } catch (Exception e) {
            messagePopUp();
            e.printStackTrace();
        }
    }
    public void refreshSearch(){
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("SELECT id, title, author, username FROM REQUESTEDBOOKS where approve = false");
            result = state.executeQuery();
            requestedBooks.setModel(new MyModel(result));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refreshUserSearch(){
        try{
            state = conn.prepareStatement("select fname, lname, age, username, password from userinformation");
            result = state.executeQuery();
            tableUsers.setModel(new MyModel(result));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addBook(){
        if (!isbnT.getText().isEmpty() && !titleT.getText().isEmpty() & !authorT.getText().isEmpty() && !quantityT.getText().isEmpty() && Integer.parseInt(quantityT.getText()) > 0) {
            conn = DBConnection.getConnection();
            try{
                String sqlRequest = "select * from requestedbooks where title like '" + titleT.getText() +  "' and author like '" +  authorT.getText() + "'";
                state = conn.prepareStatement(sqlRequest);
                ResultSet resultSet = state.executeQuery();
                if(resultSet.next()){
                    sqlRequest = "update requestedbooks set approve = true where title like '" + titleT.getText() +  "' and author like '" +  authorT.getText() + "'";
                    state = conn.prepareStatement(sqlRequest);
                    state.execute();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                createdPopUp("book.");
            } catch (SQLException ex) {
                messagePopUp();
                ex.printStackTrace();
            }
        } else {
            messagePopUp();
        }
    }
    class addBookAction implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
             addBook();
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
            refreshRequest();

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
    class RequestedMouseAction implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e){
            int row = requestedBooks.getSelectedRow();
            int id = Integer.parseInt(requestedBooks.getValueAt(row, 0).toString());
            titleT.setText(requestedBooks.getValueAt(row, 1).toString());
            authorT.setText(requestedBooks.getValueAt(row, 2).toString());
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
                createdPopUp("colleague.");
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
                createdPopUp(" account");
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
            String sql = null;
            if (usernameTS.getText().contains("admin")){
                sql = "select fname, lname, age, username, password from workers where username like '%" +  usernameTS.getText() + "%'";
            } else {
                sql = "select fname, lname, age, username, password from userinformation where username like '%" + usernameTS.getText() + "%'";
            }try {
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
    class userRequestAction implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent e) {
            System.out.println("I am in search request probably by button");
            conn = DBConnection.getConnection();
            String sql = null;
            if (titleTS.getText().length() != 0 && authorTS.getText().length() != 0 && usernameTS.getText().length() != 0) {
                sql = "select * from requestedbooks where title like '" + titleTS.getText()
                        + "%' and author like '%" + authorTS.getText()
                        + "%' and username like '%" + usernameTS.getText() + "%'";
            } else if (titleTS.getText().length() != 0 && authorTS.getText().length() == 0 && usernameTS.getText().length() == 0) {
                sql = "select * from requestedBooks where title like '" + titleTS.getText() + "'";
            } else if (authorTS.getText().length() != 0 && usernameTS.getText().length() == 0 && titleTS.getText().length() == 0) {
                sql = "select * from requestedBooks where author like '" + authorTS.getText() + "'";
            } else if (usernameTS.getText().length() != 0 && titleTS.getText().length() == 0 && authorTS.getText().length() == 0) {
                sql = "select * from requestedBooks where username like '" + usernameTS.getText() + "'";
            } else if (usernameTS.getText().length() != 0 && titleTS.getText().length() != 0 && authorTS.getText().length() == 0) {
                sql = "select * from requestedbooks where username like '" + usernameTS.getText()
                        + "%' and title like '%" + titleTS.getText() + "%'";
            } else if (usernameTS.getText().length() != 0  && authorTS.getText().length() != 0 && titleTS.getText().length() == 0){
                sql = "select * from requestedbooks where username like '" + usernameTS.getText()
                        + "%' and author like '%" + authorTS.getText() + "%'";
            } else if (titleTS.getText().length() != 0 && authorTS.getText().length() != 0 && usernameTS.getText().length() == 0) {
                sql = "select * from requestedbooks where title like '" + titleTS.getText()
                        + "%' and author like '%" + authorTS.getText() + "%'";
            } else {
                messagePopUp();
                sql ="select * from requestedbooks";
            }
            try {
                state = conn.prepareStatement(sql);
                result = state.executeQuery();
                requestTable.setModel(new MyModel(result));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    class refreshSearchAction  implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            refreshSearch();
            refreshUserSearch();
        }
    }
    class cleanSearchAction implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent event){
            usernameTS.setText("");
            titleTS.setText("");
            authorTS.setText("");

        }
    }
}