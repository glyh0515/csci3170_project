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
