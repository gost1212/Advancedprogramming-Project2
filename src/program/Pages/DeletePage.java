package program.Pages;

import static program.Pages.SearchPage.table;
import static program.Pages.SearchPage.printTable;
import static program.SQL.SQLCommands.searchByTypeModel;
import static program.SQL.SQLCommands.deleteByID;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DeletePage {

    private static Pane deletePane = new Pane();

    private static TextArea id = new TextArea();

    private static Button delete = new Button("Delete");
    
    private static Label status = new Label();
    private static Label statusResult = new Label();

    static{
        id.setLayoutX(300);
        id.setLayoutY(40);
        id.setPrefSize(150, 30);
        id.focusedProperty().addListener((no, no2, newFocusedState)->{
            if(newFocusedState){//whenever this object regains focus, frequency depends on whether this object gains or loses focus.
                statusResult.setVisible(false);
            }
        });

        delete.setLayoutX(500);
        delete.setLayoutY(40);
        delete.setPrefSize(100, 30);
        delete.setOnMouseClicked(e->{
            int code = -1;
            try{
                code = deleteByID(Integer.parseInt(id.getText()));
            }catch(Exception ex){
                statusResult.setText("Error, please enter numbers only.");
                statusResult.setTextFill(Color.RED);
            }

            if(code == 1){//if a record got deleted
                statusResult.setText("Success");
                statusResult.setTextFill(Color.GREEN);
                statusResult.setVisible(true);
            }else if(code == 0){//if no record got deleted
                statusResult.setText("No changes occurred.");
                statusResult.setTextFill(Color.RED);
                statusResult.setVisible(true);
            }else{//if SQL error occurred.
                statusResult.setText("Error, failed to delete record.");
                statusResult.setTextFill(Color.RED);
                statusResult.setVisible(true);
            }

            printTable(searchByTypeModel(""));
        });

        status.setLayoutX(20);
        status.setLayoutY(43);
        status.setPrefSize(70, 30);
        status.setFont(Font.font("Arial", 16));
        status.setText("Status:");

        statusResult.setLayoutX(70);
        statusResult.setLayoutY(43);
        statusResult.setPrefSize(240, 30);
        statusResult.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        statusResult.setVisible(false);

        deletePane.getChildren().addAll(id, delete, status, statusResult);
        deletePane.setLayoutY(25);
    }

    public static void setDeletePage(Pane root){
        if(root.getChildren().size() == 2){
            root.getChildren().remove(1);
        }

        statusResult.setVisible(false);

        root.getChildren().add(deletePane);
        printTable(searchByTypeModel(""));
    }

    public static void getTable(){
        if(!deletePane.getChildren().contains(table))
            deletePane.getChildren().add(table);
    }
    
}
