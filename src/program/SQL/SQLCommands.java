package program.SQL;

import java.sql.*;

import javafx.scene.control.Label;

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
        /*
       try {
            Date date = Date.valueOf(deliveryDate);
            addStatement.setString(1, type);
            addStatement.setString(2, model);
            addStatement.setFloat(3, price);
            addStatement.setInt(4, count);
            addStatement.setDate(5, date);
            addStatement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            System.out.println("Failed to execute query. Error: " + e.getMessage());
            return -1;
        }*/
        return -1;
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

    public static void deleteByID(int id, Label statusLabel) {
        /*
        try {
            deleteStatement.setInt(1, id);
            int deleted = deleteStatement.executeUpdate();
            if (deleted == 0) {
                statusLabel.setTextFill(Color.RED);
                statusLabel.setText("Sorry, there's no entry with the required ID.");
            } else {
                statusLabel.setTextFill(Color.GREEN);
                statusLabel.setText("Deleted rows: " + deleted + " rows");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete. Error: " + e.getMessage());
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    public static void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection.");
            e.printStackTrace();
        }
        */
    }
}
