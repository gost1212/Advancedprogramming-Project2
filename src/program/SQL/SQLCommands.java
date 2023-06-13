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

   public static int addEntry(String type, String model, float price, int count, String deliveryDate) {
    PreparedStatement stmt = null;
    try {
        stmt = conn.prepareStatement(INSERT_STATMENT);
        stmt.setString(1, type);
        stmt.setString(2, model);
        stmt.setFloat(3, price);
        stmt.setInt(4, count);
        stmt.setString(5, deliveryDate);
        return stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Failed to add entry. Error: " + e.getMessage());
        e.printStackTrace();
        return -1;
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close statement. Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

    private final static String SELECT_TYPE_MODEL_STATMENT = "select * from productstbl_saad_abdullah where TYPE like ? or Model like ?;";//base Type Model statment, if given answer.

    private final static String SELECT_ALL_STATMENT = """
                                                       select * from `productstbl_saad_abdullah`;
                                                       """;//base select * statment, in case if input was empty.
    
    public static ResultSet searchByTypeModel(String typeModel){
        PreparedStatement stmt = null;
        if(typeModel.length() == 0){
            try {
                stmt = conn.prepareStatement(SELECT_ALL_STATMENT);
            } catch (SQLException e) {
                System.out.println("Failed to create statment. error: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }else{
            try {
                stmt = conn.prepareStatement(SELECT_TYPE_MODEL_STATMENT);
            } catch (SQLException e) {
                System.out.println("Failed to create statment. error: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        if(typeModel.length() == 0){
            ResultSet result = null;

            try {
                result = stmt.executeQuery();
            } catch (SQLException e) {
                System.out.println("failed to execute query. error: " + e.getMessage());
                e.printStackTrace();
                return null;
            }

            return result;
        }

        try {
            stmt.setString(1, typeModel);
            stmt.setString(2, typeModel);
        } catch (SQLException e) {
            System.out.println("failed to set statment parameters. error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        ResultSet result = null;

        try {
            result = stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("failed to execute query. error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return result;
    }

    private final static String DELETE_ID_STATMENT = """
                                                     delete from productstbl_saad_abdullah where id = ?;
                                                     """;

    public static int deleteByID(int id) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE_ID_STATMENT);
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete record. Error: " + e.getMessage());
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close statement. Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection.");
            e.printStackTrace();
        }
    }
    
}
