package program;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.geometry.Rectangle2D;
import static program.MenuBar.Stage_MenuBar.setMenuBare;
import static program.Pages.AddPage.setAddPage;

public class MainProgram extends Application{

    private static Pane page = new Pane();

    private static Rectangle2D bounds;
    
    public static double stageWidth;
    public static double stageHieght;

    public static final Label credit = new Label("Name: Saad Ali Al-Ghamdi   Email:s441003053@st.uqu.edu.sa   ID: 441003053   Section: 3\n"
                                                        + "Name: Abdullah Omar Suleman Abu-Bakar   Email:s441016265@st.uqu.edu.sa   ID: 441016265   Section: 3");

    static{
        Screen screen = Screen.getPrimary();
        bounds = screen.getBounds();

        stageWidth = bounds.getWidth()/2;
        stageHieght = bounds.getHeight()/2;
    }
    
    public static void main(String[] args) throws Exception {
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        page.getChildren().add(credit);
        setMenuBare(page);
        setAddPage(page);
        Scene scene = new Scene(page, stageWidth, stageHieght);
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
        credit.setLayoutY(30);
        
    }
}
