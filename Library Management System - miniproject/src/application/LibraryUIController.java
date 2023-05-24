package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utilities.*;

public class LibraryUIController implements Initializable {
	Connection con = null;
	
	@FXML
	private TableView<Book> booksTable; 
	
    @FXML
    private TableColumn<Book, String> Author;

    @FXML
    private TableColumn<Book, String> Edition;

    @FXML
    private TableColumn<Book, String> Name;

    @FXML
    private TableColumn<Book, String> Publisher;
    
    ObservableList<Book> data = FXCollections.observableArrayList();
   
    @FXML
    private Button addBTN;

    @FXML
    private Text authorLabel;

    @FXML
    private TextField authorName;

    @FXML
    private Text bookLabel;

    @FXML
    private TextField bookName;

    @FXML
    private TableView<?> booksTabel;

    @FXML
    private Button deleteBTN;

    @FXML
    private Text editionLabel;

    @FXML
    private TextField editionName;

    @FXML
    private AnchorPane formSection;

    @FXML
    private Text publisherLabel;

    @FXML
    private TextField publisherName;

    @FXML
    private Label viewLabel;

    @FXML
    private AnchorPane viewSection;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    public void addBTNOnClicked(ActionEvent event1)throws IOException {
    
    	if(bookName.getText().isBlank() == false && authorName.getText().isBlank() == false && publisherName.getText().isBlank() == false && editionName.getText().isBlank() == false) {
    		
    		addbook();
    		
    		data.add(new Book(
    				bookName.getText(),
    				authorName.getText(),
    				publisherName.getText(),
    				editionName.getText()
    		));
    		bookName.clear();
			authorName.clear();
			publisherName.clear();
			editionName.clear();
    		
    	}else {
    		System.out.println("Please fill in all the details");
    	}
    }
		

	@FXML
    public void deleteBTNOnClicked(ActionEvent event2)throws IOException {
		if(bookName.getText().isBlank() == false && authorName.getText().isBlank() == false && publisherName.getText().isBlank() == false && editionName.getText().isBlank() == false) {
    		
    		deletebook();
    		bookName.clear();
			authorName.clear();
			publisherName.clear();
			editionName.clear();
    		
    	}else {
    		System.out.println("Please fill in all the details");
    	}

    }
    
    //function to add a new book
    public void addbook() {
    	try {
    		con = DButil.getConnection();
        	String bookadd = "Insert into books(bookName,author,publisherName,edition) values(?,?,?,?)";
        	PreparedStatement p=con.prepareStatement(bookadd);
			p.setString(1,bookName.getText());
			p.setString(2,authorName.getText()); 
			p.setString(3,publisherName.getText());
			p.setString(4,editionName.getText());
			p.executeUpdate();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    //Function to delete the book
    public void deletebook() {
    	try {
    		con = DButil.getConnection();
        	String bookdel = "Delete from books where bookName = ? and author = ? and publisherName = ? and edition = ?";
        	PreparedStatement p=con.prepareStatement(bookdel);
			p.setString(1,bookName.getText());
			p.setString(2,authorName.getText()); 
			p.setString(3,publisherName.getText());
			p.setString(4,editionName.getText());
			p.executeUpdate();
			
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    //Function to fetch the data from the Database and display it in the GUI table
    public void fetchTable() {
    	try {
    		con = DButil.getConnection();
        	String fetch = "Select * from books";
        	PreparedStatement p=con.prepareStatement(fetch);
			ResultSet rs = p.executeQuery();
			
			while(rs.next()) {
				data.add(new Book(
	    				rs.getString("bookName"),
	    				rs.getString("author"),
	    				rs.getString("publisherName"),
	    				rs.getString("edition")
	    		));
			}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    //Defining a data model for a book object
    public class Book {

    	final String bookname;
    	final String authorname;
    	final String publishername;
    	final String editionname;

        public Book(String bName, String aName, String pName, String eName){
            this.bookname = bName;
            this.authorname = aName;
            this.publishername = pName;
            this.editionname = eName;
        }

    	public String getBookname() {
    		return bookname;
    	}

    	public String getAuthorname() {
    		return authorname;
    	}

    	public String getPublishername() {
    		return publishername;
    	}

    	public String getEditionname() {
    		return editionname;
    	}
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		fetchTable();
		Name.setCellValueFactory(new PropertyValueFactory<Book, String>("bookname"));
        Author.setCellValueFactory(new PropertyValueFactory<Book, String>("authorname"));
        Publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publishername"));
        Edition.setCellValueFactory(new PropertyValueFactory<Book, String>("editionname"));
        booksTable.setItems(data);
		
	}
    
}


