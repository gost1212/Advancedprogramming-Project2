package program.Pages;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import javafx.scene.paint.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import static program.SQL.SQLCommands.addEntry;

public class AddPage{

    private static final Pane addPage = new Pane();

    private static final String[] typeNames = {"Car", "Coolers", "Electronics", "Computers"};

    private static final Label pageHeader = new Label("Add Products");
    private static final Label typeLabel = new Label("Select Type:");
    private static final Label modelLabel = new Label("Enter Model:");
    private static final Label priceLabel = new Label("Enter Price:");
    private static final Label countLabel = new Label("Select Count:");
    private static final Label dateLabel = new Label("Choose Delivery Date:");
    private static final Label result = new Label("test");
    private static final Label typeLabelMessage = new Label("Please select a Type.");
    private static final Label modelLabelMessage = new Label("Please enter something\nin model.");
    private static final Label priceLabelMessage = new Label("please enter a\ncorrect number.");

    private static final ChoiceBox<String> types = new ChoiceBox<>();

    private static final TextArea model = new TextArea();
    private static final TextArea price = new TextArea();

    private static final Slider count = new Slider(0, 10, 0);

    private static final DatePicker date = new DatePicker(LocalDate.now());

    private static final Button connectAdd = new Button("Add");

    static{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

        typeLabelMessage.setLayoutX(50);
        typeLabelMessage.setLayoutY(160);
        typeLabelMessage.setPrefSize(240, 20);
        typeLabelMessage.setTextFill(Color.RED);
        typeLabelMessage.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        typeLabelMessage.setVisible(false);
        
        model.setLayoutX(180);
        model.setLayoutY(105);
        model.setPrefSize(100, 15);
        model.setFont(Font.font("Arial", 14));
        model.setOnMouseClicked(e->{
            result.setVisible(false);
        });

        modelLabel.setLayoutX(180);
        modelLabel.setLayoutY(80);
        modelLabel.setPrefSize(120, 20);
        modelLabel.setFont(Font.font("Arial", 16));

        modelLabelMessage.setLayoutX(180);
        modelLabelMessage.setLayoutY(160);
        modelLabelMessage.setPrefSize(220, 80);
        modelLabelMessage.setTextFill(Color.RED);
        modelLabelMessage.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        modelLabelMessage.setVisible(false);
        
        price.setLayoutX(310);
        price.setLayoutY(105);
        price.setPrefSize(100, 15);
        price.setFont(Font.font("Arial", 14));
        price.setOnMouseClicked(e->{
            result.setVisible(false);
        });

        priceLabel.setLayoutX(310);
        priceLabel.setLayoutY(80);
        priceLabel.setPrefSize(120, 20);
        priceLabel.setFont(Font.font("Arial", 16));

        priceLabelMessage.setLayoutX(310);
        priceLabelMessage.setLayoutY(160);
        priceLabelMessage.setPrefSize(240, 60);
        priceLabelMessage.setTextFill(Color.RED);
        priceLabelMessage.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        priceLabelMessage.setVisible(false);
        
        count.setLayoutX(440);
        count.setLayoutY(105);
        count.setPrefSize(200, 15);
        count.setShowTickMarks(true);
        count.setShowTickLabels(true);
        count.setMajorTickUnit(1);
        count.setMinorTickCount(0);
        count.setBlockIncrement(1);
        count.setOnMouseClicked(e->{
            result.setVisible(false);
        });

        countLabel.setLayoutX(440);
        countLabel.setLayoutY(80);
        countLabel.setPrefSize(120, 20);
        countLabel.setFont(Font.font("Arial", 16));
        
        date.setLayoutX(670);
        date.setLayoutY(105);
        date.setPrefSize(200, 15);
        date.setConverter(converter);
        date.setEditable(false);
        date.setOnMouseClicked(e->{
            result.setVisible(false);
        });

        dateLabel.setLayoutX(670);
        dateLabel.setLayoutY(80);
        dateLabel.setPrefSize(170, 20);
        dateLabel.setFont(Font.font("Arial", 16));

        types.setLayoutX(50);
        types.setLayoutY(105);
        types.setPrefSize(100, 30);//width, height
        types.setOnMouseClicked(e->{
            result.setVisible(false);
        });

        types.getItems().setAll(typeNames);

        connectAdd.setLayoutX(395);
        connectAdd.setLayoutY(250);
        connectAdd.setPrefSize(130, 45);
        connectAdd.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        connectAdd.setOnMousePressed(e->{
            String type = types.getValue();
            if(type == null){
                typeLabelMessage.setVisible(true);
                return;
            }else{
                typeLabelMessage.setVisible(false);
            }

            String model = AddPage.model.getText();
            if(model.length() == 0){
                modelLabelMessage.setVisible(true);
                return;
            }else{
                modelLabelMessage.setVisible(false);
            }

            float price;
            try{
                price = Float.parseFloat(AddPage.price.getText());
            }catch(Exception er){
                priceLabelMessage.setVisible(true);
                return;
            }
            priceLabelMessage.setVisible(false);

            int count = (int)AddPage.count.getValue();
            int result = addEntry(type, model, price, count, AddPage.date.getEditor().getText());
            
            if(result == 1){
                AddPage.result.setText("Success");
                AddPage.result.setTextFill(Color.GREEN);
                AddPage.result.setVisible(true);
            }else if(result == 0){
                AddPage.result.setText("Nothing Changed");
                AddPage.result.setTextFill(Color.RED);
                AddPage.result.setVisible(true);
            }else{
                AddPage.result.setText("SQL error");
                AddPage.result.setTextFill(Color.RED);
                AddPage.result.setVisible(true);
            }

            AddPage.types.setValue("");
            AddPage.model.setText("");
            AddPage.price.setText("");
            AddPage.count.setValue(0);
            AddPage.date.setValue(LocalDate.now());
            return;
        });

        result.setLayoutX(410);
        result.setLayoutY(295);
        result.setPrefSize(130, 45);
        result.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        result.setVisible(false);

        addPage.getChildren().addAll(pageHeader, typeLabel, types, modelLabel, model, priceLabel, price,
                                    countLabel, count, dateLabel, date, connectAdd, result, typeLabelMessage,
                                    modelLabelMessage, priceLabelMessage);
        addPage.setLayoutY(25);
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
}
