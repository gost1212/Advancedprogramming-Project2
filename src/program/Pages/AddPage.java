package program.Pages;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import static program.SQL.SQLCommands.addEntry;

public class AddPage{

    private static Pane addPage = new Pane();
    private static double YPos = 25;

    private static String[] typeNames = {"Car", "Coolers", "Electronics", "Computers"};

    private static Label pageHeader = new Label("Add Products");
    private static Label typeLabel = new Label("Select Type:");
    private static Label modelLabel = new Label("Enter Model:");
    private static Label priceLabel = new Label("Enter Price:");
    private static Label countLabel = new Label("Select Count:");
    private static Label dateLabel = new Label("Choose Delivery Date:");
    private static Label result = new Label("");

    private static ChoiceBox<String> types = new ChoiceBox<>();

    private static TextArea model = new TextArea();
    private static TextArea price = new TextArea();

    private static Slider count = new Slider(0, 10, 0);

    private static DatePicker date = new DatePicker(LocalDate.now());

    private static Button connectAdd = new Button("Add");

    static{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                // Convert LocalDate to String
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                // Convert String to LocalDate
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        };
        
        pageHeader.setLayoutX(10);
        pageHeader.setLayoutY(10);
        pageHeader.setPrefSize(150, 50);
        pageHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        typeLabel.setLayoutX(50);
        typeLabel.setLayoutY(80);
        typeLabel.setPrefSize(120, 20);
        typeLabel.setFont(Font.font("Arial", 16));
        
        model.setLayoutX(180);
        model.setLayoutY(105);
        model.setPrefSize(100, 15);
        model.setFont(Font.font("Arial", 14));

        modelLabel.setLayoutX(180);
        modelLabel.setLayoutY(80);
        modelLabel.setPrefSize(120, 20);
        modelLabel.setFont(Font.font("Arial", 16));
        
        price.setLayoutX(310);
        price.setLayoutY(105);
        price.setPrefSize(100, 15);
        price.setFont(Font.font("Arial", 14));

        priceLabel.setLayoutX(310);
        priceLabel.setLayoutY(80);
        priceLabel.setPrefSize(120, 20);
        priceLabel.setFont(Font.font("Arial", 16));
        
        count.setLayoutX(440);
        count.setLayoutY(105);
        count.setPrefSize(200, 15);
        count.setShowTickMarks(true);
        count.setShowTickLabels(true);
        count.setMajorTickUnit(1);
        count.setMinorTickCount(0);
        count.setBlockIncrement(1);

        countLabel.setLayoutX(440);
        countLabel.setLayoutY(80);
        countLabel.setPrefSize(120, 20);
        countLabel.setFont(Font.font("Arial", 16));
        
        date.setLayoutX(670);
        date.setLayoutY(105);
        date.setPrefSize(200, 15);
        date.setConverter(converter);
        date.setEditable(false);

        dateLabel.setLayoutX(670);
        dateLabel.setLayoutY(80);
        dateLabel.setPrefSize(170, 20);
        dateLabel.setFont(Font.font("Arial", 16));

        types.setLayoutX(50);
        types.setLayoutY(105);
        types.setPrefSize(100, 30);//width, height

        types.getItems().setAll(typeNames);

        connectAdd.setLayoutX(395);
        connectAdd.setLayoutY(250);
        connectAdd.setPrefSize(130, 45);
        connectAdd.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        result.setLayoutX(410);
        result.setLayoutY(295);
        result.setPrefSize(130, 45);
        result.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        connectAdd.setOnMousePressed(e->{
            String type = types.getValue();
            if(type == null){
                System.out.println("Please select a Type.");
                return;
            }

            String model = AddPage.model.getText();
            if(model.length() == 0){
                System.out.println("Please enter something in model.");
                return;
            }
            float price;
            try{
                price = Float.parseFloat(AddPage.price.getText());
            }catch(Exception er){
                System.out.println("please enter a correct number.");
                return;
            }
            int count = (int)AddPage.count.getValue();
            String[] temp = AddPage.date.getEditor().getText().split("/");
            String date = temp[0] + "-" + temp[1] + "-" + temp[2];
            int result = addEntry(type, model, price, count, date);
            
            if(result != 0){
                System.out.println("Failed to add new entry.");
            }else{
                System.out.println("Success.");
            }

            AddPage.types.setValue("");
            AddPage.model.setText("");
            AddPage.price.setText("");
            AddPage.count.setValue(0);
            AddPage.date.setValue(LocalDate.now());
            return;
        });

        addPage.getChildren().addAll(pageHeader, typeLabel, types, modelLabel, model, priceLabel, price,
                                    countLabel, count, dateLabel, date, connectAdd, result);
        addPage.setLayoutY(YPos);
    }

    public static void setAddPage(Pane root){
        if(root.getChildren().size() == 2){
            root.getChildren().remove(1);
        }
        types.setValue(null);
        model.setText("");
        price.setText("");
        count.setValue(0);
        date.setValue(LocalDate.now());
        root.getChildren().add(addPage);
    }

    //date.getEditor().getText()
    //can get date by string
    //format: mm//dd//yyyy
}
