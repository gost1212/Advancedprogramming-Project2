package program.Pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.ResultSet;
import java.sql.SQLException;
import static program.SQL.SQLCommands.searchByTypeModel;

public class SearchPage {

    public class Products{

        private int ID;
        private String type;
        private String model;
        private float price;
        private int count;
        private String date;

        public Products(int ID, String type, String model, float price, int count, String date){
            this.ID = ID;
            this.type = type;
            this.model = model;
            this.price = price;
            this.count = count;
            this.date = date;
        }

        public int getID(){
            return this.ID;
        }

        public String getType(){
            return this.type;
        }

        public String getModel(){
            return this.model;
        }

        public float getPrice(){
            return this.price;
        }

        public int getCount(){
            return this.count;
        }

        public String getDate(){
            return this.date;
        }

        public void setID(int ID){
            this.ID = ID;
        }

        public void setType(String type){
            this.type = type;
        }

        public void setModel(String model){
            this.model = model;
        }

        public void setPrice(float price){
            this.price = price;
        }

        public void setCount(int count){
            this.count = count;
        }

        public void setDate(String date){
            this.date = date;
        }
    }

    private final static SearchPage productMake = new SearchPage();

    private static Pane searchPane = new Pane();

    public static TableView<Products> table = new TableView<>();

    private static ObservableList<Products> resultList = FXCollections.observableArrayList();
    
    private static TableColumn<Products, String> ID = new TableColumn<>("ID");
    private static TableColumn<Products, String> type = new TableColumn<>("Type");
    private static TableColumn<Products, String> model = new TableColumn<>("Model");
    private static TableColumn<Products, String> price = new TableColumn<>("Price");
    private static TableColumn<Products, String> count = new TableColumn<>("Count");
    private static TableColumn<Products, String> date = new TableColumn<>("Date");

    private static Button search = new Button("Search");

    private static TextArea typeModel = new TextArea();

    private static Label error = new Label();

    static{
        SearchPage s = new SearchPage();

        table.setLayoutX(220);
        table.setLayoutY(100);
        table.setPrefSize(500, 300);
        table.setItems(resultList);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        count.setCellValueFactory(new PropertyValueFactory<>("Count"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));

        ID.prefWidthProperty().bind(table.widthProperty().multiply(0.05));
        type.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        model.prefWidthProperty().bind(table.widthProperty().multiply(0.26));
        price.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        count.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        date.prefWidthProperty().bind(table.widthProperty().multiply(0.19));
        
        table.getColumns().addAll(ID, type, model, price, count, date);

        search.setLayoutX(500);
        search.setLayoutY(40);
        search.setPrefSize(100, 30);
        search.setOnMouseClicked(e->{
            printTable(searchByTypeModel(typeModel.getText()));
        });

        typeModel.setLayoutX(300);
        typeModel.setLayoutY(40);
        typeModel.setPrefSize(150, 30);

        error.setLayoutX(250);
        error.setLayoutY(200);
        error.setPrefSize(450, 100);
        error.setTextFill(Color.RED);
        error.setFont(Font.font("Arial", 40));
        error.setVisible(false);

        searchPane.getChildren().addAll(search, typeModel, error);
        searchPane.setLayoutY(25);
    }

    protected static void printTable(ResultSet set){
        if(set == null){
            error.setText("Error, failed to get data.");
            error.setVisible(true);
            table.setVisible(false);
            return;
        }
        try {
            if(!set.isBeforeFirst()){
                error.setText("Error, no data found.");
                error.setVisible(true);
                table.setVisible(false);
                return;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("failed to ckeck if set is empty or not. error: " + e.getMessage());
            e.printStackTrace();
        }

        error.setVisible(false);
        table.setVisible(true);

        resultList.clear();

        try {
            while(set.next()){
                
                try {
                    Products temp = productMake.new Products(set.getInt(1), set.getString(2),
                     set.getString(3), set.getFloat(4), set.getInt(5), set.getDate(6).toString());

                     resultList.add(temp);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    System.out.println("Reading from set failed. error: " + e.getMessage());
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("failed to walk to next tuple. error: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            set.close();
        } catch (SQLException e) {
            System.out.println("failed to close result set. error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void setSearchPage(Pane root){
        if(root.getChildren().size() == 2){
            root.getChildren().remove(1);
        }
        root.getChildren().add(searchPane);
        resultList.clear();
    }

    public static void getTable(){
        if(!searchPane.getChildren().contains(table))
            searchPane.getChildren().add(table);
    }
}
