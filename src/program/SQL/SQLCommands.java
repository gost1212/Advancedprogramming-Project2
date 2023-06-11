package program.SQL;

import java.sql.*;

public class SQLCommands {
    
    private static final String database_URL = "jdbc:mysql://localhost:3306/productsdb_alghamdi_abubakr";
    private static final String user = "root";
    private static final String password = "sqlsqlsql";
    private static Connection conn = null;

    static{
        try {
            conn = DriverManager.getConnection(database_URL, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            if(!conn.isValid(5)){
                System.out.println("Timed out.");
                System.exit(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private final static String INSERT_STATMENT = """
                                          INSERT INTO ProductsTBL_Saad_Abdullah (Type, Model, Price, Count, DeliveryDate)
                                          VALUES (?, ?, ?, ?, ?);
                                          """;//base inseart statment.

    public static int addEntry(String type, String model, float price, int count, String deliveryDate){
        return 0;
    }

    private final static String SELECT_TYPE_MODEL_STATMENT = """
                                                       select * from `productstbl_saad_abdullah` where TYPE like ? or Model like ?;
                                                       """;//base Type Model statment, if given answer.

    private final static String SELECT_TYPE_MODEL_LENGTH_STATMENT = """
                                                         select count(*) from `productstbl_saad_abdullah` where TYPE like ? or Model like ?;
                                                         """;//used to know how many entries did we get.

    private final static String SELECT_ALL_STATMENT = """
                                                       select * from `productstbl_saad_abdullah`;
                                                       """;//base select * statment, in case if input was empty.

    private final static String SELECT_ALL_LENGTH_STATMENT = """
                                                       select count(*) from `productstbl_saad_abdullah`;
                                                       """;//used to know how many entries did we get.
    
    public static ResultSet searchByTypeModel(String typeModel){
        return null;
    }

    private final static String DELETE_ID_STATMENT = """
                                                     delete from productstbl_saad_abdullah where id = ?;
                                                     """;

    public static int deleteByID(int id){
        return 0;
    }

    public static void closr(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to clost connection.");
            e.printStackTrace();
        }
    }
}
