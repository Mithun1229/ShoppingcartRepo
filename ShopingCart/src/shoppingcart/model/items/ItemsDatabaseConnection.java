package shoppingcart.model.items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ItemsDatabaseConnection {

	public static final String Driver = "oracle.jdbc.driver.OracleDriver";
	public static final String ConnectionName = "jdbc:oracle:thin:@localhost:1521:orcl2";
	public static final String ConnectionUserName = "c##mithun";
	public static final String ConnectionPassword = "Connect14";
	Connection con;
	PreparedStatement prepdStetmnt = null;
	// java.util.Date d1 = new java.util.Date();
	// Scanner input = new Scanner(System.in);
	String createTable = "CREATE TABLE ListofItems7(Item_ID number(5), Item_Name varchar2(15), Item_category varchar2(5),Item_Price number(5), "
			+ "Item_Quantity number(5),primary key (Item_ID))";
	int ID_Gen = 999;

	void estblishDBConnection() {

		try {
			Class.forName(Driver);
			System.out.println("Logs.........\nLoaded Oracle drivers");
			con = DriverManager.getConnection(ConnectionName, ConnectionUserName, ConnectionPassword);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Established Database Connection");
	}

	void fillItemsList() throws SQLException {

		try {
			System.out.println("Creating ListofItems table");
			prepdStetmnt = con.prepareStatement(createTable);
			
			prepdStetmnt.executeUpdate();

			System.out.println("Filling Items Database with dummy values for testing purpose.");
			prepdStetmnt = con.prepareStatement(
					"Insert into ListofItems7(Item_ID,Item_Name,Item_category,Item_Price,Item_Quantity) VALUES(?,?,?,?,?)");

			System.out.println("Table ListofItems7 created");

			for (int i = 0; i < 10; i++) {
				ID_Gen = ID_Gen + i;
				prepdStetmnt.setInt(1, ID_Gen);
				switch (i) {

				case 0:
					prepdStetmnt.setString(2, "Bread");
					prepdStetmnt.setString(3, "Food");
					prepdStetmnt.setDouble(4, 2.7);
					prepdStetmnt.setInt(5, 25);
					break;
				case 1:
					prepdStetmnt.setString(2, "T-shirt");
					prepdStetmnt.setString(3, "Clothing");
					prepdStetmnt.setDouble(4, 25.2);
					prepdStetmnt.setInt(5, 65);
					break;
				case 2:
					prepdStetmnt.setString(2, "Orange");
					prepdStetmnt.setString(3, "Fruits and Vegetables");
					prepdStetmnt.setDouble(4, 0.99);
					prepdStetmnt.setInt(5, 200);
					break;
				case 3:
					prepdStetmnt.setString(2, "Mushrooms");
					prepdStetmnt.setString(3, "Fruits and Vegetables");
					prepdStetmnt.setDouble(4, 3.2);
					prepdStetmnt.setInt(5, 12);
					break;
				case 4:
					prepdStetmnt.setString(2, "Paper");
					prepdStetmnt.setString(3, "Stationeries");
					prepdStetmnt.setDouble(4, 12.5);
					prepdStetmnt.setInt(5, 11);
					break;
				case 8:
					prepdStetmnt.setString(2, "Drone");
					prepdStetmnt.setString(3, "Electronics");
					prepdStetmnt.setDouble(4, 25);
					prepdStetmnt.setInt(5, 15);
					break;
				case 9:
					prepdStetmnt.setString(2, "Laptop");
					prepdStetmnt.setString(3, "Electronics");
					prepdStetmnt.setDouble(4, 250);
					prepdStetmnt.setInt(5, 10);
					break;
				case 10:
					prepdStetmnt.setString(2, "Bannana");
					prepdStetmnt.setString(3, "Fruits and Vegetables");
					prepdStetmnt.setDouble(4, 2.5);
					prepdStetmnt.setInt(5, 15);
					break;
				default:
						break;
				}
				prepdStetmnt.addBatch();
			}
			prepdStetmnt.executeBatch();
			System.out.println("Added few test values to Items list");

		} catch (Exception e) {
			System.out.println("Table exists,continuing with other functions\n\n"
					+ "==============================================\n\n");
		}
		
		
	}

	void getListofItems() throws SQLException {
		prepdStetmnt = con.prepareStatement("select Item_ID,Item_Name,Item_category,Item_Price,Item_Quantity from ListofItems7");
		ResultSet rs = prepdStetmnt.executeQuery();
		System.out.println("Item_ID     Item_Name");
		while (rs.next()) {
			System.out.println("   " + rs.getInt(1) + "		   " + rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4));

		}

	}
	
	public static void main(String[] args) throws SQLException {
		ItemsDatabaseConnection l1 = new ItemsDatabaseConnection();
		l1.estblishDBConnection();
		l1.fillItemsList();
		l1.getListofItems();
	}

	/*
	void getEmployeedetails(int empid) throws SQLException {

		prepdStetmnt = con
				.prepareStatement("select Emp_ID,Emp_Name,Emp_Sex,Emp_Joined_Date from ListOfEmployees where Emp_ID=?");

		prepdStetmnt.setInt(1, empid);
		ResultSet rs = prepdStetmnt.executeQuery();
		while (rs.next()) {
			System.out.println("Emp_ID: " + rs.getInt(1) + " ");
			System.out.println("Emp_Name: " + rs.getString(2) + " ");
			System.out.println("Emp_Sex: " + rs.getString(3) + " ");
			System.out.println("Emp_Joined_Date: " + rs.getDate(4) + " ");

		}

	}

	void updateEmployeedetails(int empid) throws SQLException {

		prepdStetmnt = con
				.prepareStatement("update ListOfEmployees set Emp_Name=?,Emp_Sex=?,Emp_Joined_Date=? where Emp_ID=?");

		prepdStetmnt.setInt(4, empid);
		System.out.println("Enter Employee Name");
		prepdStetmnt.setString(1, input.next());
		System.out.println("Enter Employee Sex: M/F");
		prepdStetmnt.setString(2, input.next());
		System.out.println(
				"Enter Employee Joining date\nIf the date is today then select 1\nTo enter the date manually, select 2");
		if (input.nextInt() == 1)
			prepdStetmnt.setDate(3, new java.sql.Date(d1.getTime()));
		else
			prepdStetmnt.setDate(4, new java.sql.Date(d1.getTime()));
		prepdStetmnt.executeUpdate();

		System.out.println("Employee details updated. Employee updated details:");
		getEmployeedetails(empid);

	}

	void addEmployee() throws SQLException {

		prepdStetmnt = con.prepareStatement("select Emp_ID from ListOfEmployees");
		ResultSet rs = prepdStetmnt.executeQuery();
		while (rs.next()) {
			ID_Gen = rs.getInt(1);

		}

		System.out.println("How many Employees do you wish to add");
		int counter = input.nextInt();

		prepdStetmnt = con.prepareStatement(
				"Insert into ListOfEmployees(Emp_ID,Emp_Name,Emp_Sex,Emp_Joined_Date) VALUES(?,?,?,?)");

		for (int i = 1; i < counter + 1; i++) {
			ID_Gen = ID_Gen + i;
			System.out.println("Employee ID Generated is: " + ID_Gen);
			prepdStetmnt.setInt(1, ID_Gen);
			System.out.println("Enter Employee Name");
			prepdStetmnt.setString(2, input.next());
			System.out.println("Enter Employee Sex: M/F");
			prepdStetmnt.setString(3, input.next());
			System.out.println(
					"Enter Employee Joining date\nIf the date is today then select 1\nTo enter the date manually, select 2");
			if (input.nextInt() == 1)
				prepdStetmnt.setDate(4, new java.sql.Date(d1.getTime()));
			else
				prepdStetmnt.setDate(4, new java.sql.Date(d1.getTime()));
			prepdStetmnt.addBatch();

		}
		prepdStetmnt.executeBatch();
		System.out.println("Employee(s) added to Database");
	}

	public void deleteEmployee(int empid) throws SQLException {
		prepdStetmnt = con.prepareStatement("Delete from ListOfEmployees where EMP_ID=?");
		prepdStetmnt.setInt(1, empid);
		prepdStetmnt.executeUpdate();
		System.out.println("Employee details deleted from Database");

	}
*/
}
