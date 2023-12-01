import java.sql.*;
import java.util.Scanner;

public class Administrator extends BaseModel {

	public Administrator(Connection connection, Scanner scanner) {
		super(connection, scanner);
	}

	public void showAdminstratorPanel() {
		while (true) {

			System.out.println("----Operations for Adminstrator Menu----");
			System.out.println("What kinds of operation would you like to perform?");
			System.out.println("1. Create all tables");
			System.out.println("2. Delete all tables");
			System.out.println("3. Load from datafile");
			System.out.println("4. Show content of a table");
			System.out.println("5. Return to the main menu");
			System.out.print("Enter your choice: ");
			int choice = getValidChoice(1, 5);

			switch (choice) {
				case 1:
					try {
						System.out.print("Processing...");
						createAll();
						System.out.println("Done! Database is intialized");
					} catch (SQLException e) {
						System.out.println("");
						System.out.println(e);
					}
					break;
				case 2:
					try {
						System.out.print("Processing...");
						deleteAll();
						System.out.println("Done! Database is removed");
					} catch (Exception e) {
						System.out.println("");
						System.out.println(e);
					}
					break;
				case 3:
					try {
						System.out.print("Type in the Source Data Folder Path: ");
						String path = scanner.nextLine();
						System.out.print("Processing...");
						loadData(path);
						System.out.println("Done! Data is inputted to database!");
					} catch (SQLIntegrityConstraintViolationException e) {
						System.out.println(e);
						System.out.println("Unable to load file to database!");
					} catch (SQLException e) {
						System.out.println(e);
						System.out.println("Data file not found inside the given folder!");
					}

					break;
				case 4:
					try {
						showContentOfTable();
					} catch (SQLException e) {
						System.out.println(e);
					}
					break;
				case 5:
					return;
				default:
					break;
			}
		}
	}

	private void createAll() throws SQLException {
		Statement stmt = connection.createStatement();
		// Category
		stmt.executeUpdate(
				"CREATE TABLE category(cID INT(1) UNSIGNED NOT NULL, cName CHAR(20) NOT NULL, PRIMARY KEY(cID))");

		// Manufacturer
		stmt.executeUpdate(
				"CREATE TABLE manufacturer(mID INT(2) UNSIGNED NOT NULL, mName CHAR(20) NOT NULL, mAddress CHAR(50) NOT NULL, mPhoneNum INT(8) UNSIGNED NOT NULL, PRIMARY KEY (mID))");

		// Part
		stmt.executeUpdate(
				"CREATE TABLE part(pID INT(3) UNSIGNED NOT NULL, pName CHAR(20) NOT NULL, pPrice INT(5) UNSIGNED NOT NULL, mID INT(2) UNSIGNED NOT NULL, cID INT(1) UNSIGNED NOT NULL, pWarranty INT(2) UNSIGNED NOT NULL, pQuantity INT(2) UNSIGNED NOT NULL, PRIMARY KEY (pID), FOREIGN KEY (mID) REFERENCES manufacturer(mID), FOREIGN KEY (cID) REFERENCES category(cID))");

		// Salesperson
		stmt.executeUpdate(
				"CREATE TABLE salesperson(sID INT(2) UNSIGNED NOT NULL, sName CHAR(20) NOT NULL, sAddress CHAR(50) NOT NULL, sPhoneNum INT(8) UNSIGNED NOT NULL, sExperience INT(1) UNSIGNED NOT NULL, PRIMARY KEY (sID))");

		// Transaction
		stmt.executeUpdate(
				"CREATE TABLE transaction(tID INT(4) UNSIGNED NOT NULL, pID INT(3) UNSIGNED NOT NULL, sID INT(2) UNSIGNED NOT NULL, tDate Date, PRIMARY KEY (tID), FOREIGN KEY (pID) REFERENCES part(pID), FOREIGN KEY (sID) REFERENCES salesperson(sID))");
	}

	private void deleteAll() throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE transaction");
		stmt.executeUpdate("DROP TABLE part");
		stmt.executeUpdate("DROP TABLE category");
		stmt.executeUpdate("DROP TABLE manufacturer");
		stmt.executeUpdate("DROP TABLE salesperson");
	}

	private void loadData(String folderPath) throws SQLException, SQLIntegrityConstraintViolationException {

		Statement stmt = connection.createStatement();
		stmt.execute(
				String.format("LOAD DATA LOCAL INFILE './%s/category.txt' REPLACE INTO TABLE category", folderPath));
		stmt.execute(String.format("LOAD DATA LOCAL INFILE './%s/manufacturer.txt' REPLACE INTO TABLE manufacturer",
				folderPath));
		stmt.execute(String.format("LOAD DATA LOCAL INFILE './%s/part.txt' REPLACE INTO TABLE part", folderPath));
		stmt.execute(String.format("LOAD DATA LOCAL INFILE './%s/salesperson.txt' REPLACE INTO TABLE salesperson",
				folderPath));
		stmt.execute("LOAD DATA LOCAL INFILE './" + folderPath
				+ "/transaction.txt' REPLACE INTO TABLE transaction (tID, pID, sID, @date) SET tDate = STR_TO_DATE(@date, '%d/%m/%Y')");
	}

	private void showContentOfTable() throws SQLException {
		System.out.print("Which table would you like to show: ");
		String tableName = scanner.nextLine();
		System.out.println("Content of table " + tableName.toLowerCase() + ":");
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s", tableName.toLowerCase()));

		switch (tableName.toLowerCase()) {
			case "category":
				System.out.println("| cID | cName |");
				break;
			case "part":
				System.out.println("| pID | pName | pPrice | mID | cID | pQuantity | pWarranty |");
				break;
			case "manufacturer":
				System.out.println("| mID | mName | mAddress | mPhoneNum |");
				break;
			case "salesperson":
				System.out.println("| sID | sName | sAddress | sPhoneNum | sExperience |");
				break;
			case "transaction":
				System.out.println("| tID | pID | sID | tDate |");
				break;
			default:
				System.out.println(String.format("No table named %s found", tableName.toLowerCase()));
				return;
		}

		while (rs.next()) {
			switch (tableName.toLowerCase()) {
				case "category":
					System.out.println(String.format("| %d | %s |", rs.getInt("cID"), rs.getString("cName")));
					break;
				case "part":
					System.out.println(String.format("| %d | %s | %d | %d | %d | %d | %d |", rs.getInt("pID"),
							rs.getString("pName"), rs.getInt("pPrice"), rs.getInt("mID"), rs.getInt("cID"),
							rs.getInt("pQuantity"), rs.getInt("pWarranty")));
					break;
				case "manufacturer":
					System.out.println(String.format("| %d | %s | %s | %d |", rs.getInt("mID"), rs.getString("mName"),
							rs.getString("mAddress"), rs.getInt("mPhoneNum")));
					break;
				case "salesperson":
					System.out.println(
							String.format("| %d | %s | %s | %d | %d |", rs.getInt("sID"), rs.getString("sName"),
									rs.getString("sAddress"), rs.getInt("sPhoneNum"), rs.getInt("sExperience")));
					break;
				case "transaction":
					System.out.println(String.format("| %d | %d | %d | %s |", rs.getInt("tID"), rs.getInt("pID"),
							rs.getInt("sID"), rs.getString("tDate")));
					break;
				default:
					System.out.println(String.format("No table named %s found", tableName.toLowerCase()));
					return;
			}
		}

		System.out.println("End of Query\n");
	}
}
