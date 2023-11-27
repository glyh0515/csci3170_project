public static String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db60?autoReconnect=true&useSSL=false";
public static String dbUsername = "Group60";
public static String dbPassword = "CSCI3170";

public static Connection connectToMySQL(){
        Connection con = null;
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
        } catch (ClassNotFoundException e){
                System.out.println("[Error]: Java MySQL DB Driver not found!!");
                System.exit(0);
        } catch (SQLException e){
                System.out.println(e);
        }
        return con;
}

public void CreateAll() throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE Category(CID INT(1) UNSIGNED NOT NULL, CNAME CHAR(20) NOT NULL, PRIMARY KEY(CID)");
        stmt.executeUpdate("CREATE TABLE Manufacturer(MID INT(2) UNSIGNED NOT NULL, MNAME CHAR(20) NOT NULL, MADDRESS CHAR(50) NOT NULL, MPHONENUM INT(8) UNSIGNED NOT NULL, PRIMARY KEY (MID)");
        stmt.executeUpdate("CREATE TABLE Salesperson(SID INT(2) UNSIGNED NOT NULL, SNAME CHAR(20) NOT NULL, SADDRESS CHAR(50) NOT NULL, SPHONENUM INT(8) NOT NULL, SEXPER INT(1) NOT NULL, PRIMARY KEY (SID)")
        stmt.executeUpdate("CREATE TABLE Part(PID INT(3) UNSIGNED NOT NULL, PNAME CHAR(20) NOT NULL, PPRICE INT(5) UNSIGNED NOT NULL, ")
        stmt.executeUpdate("")
}
