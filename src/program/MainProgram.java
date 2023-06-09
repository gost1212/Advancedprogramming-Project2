package program;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.geometry.Rectangle2D;
import static program.MenuBar.Stage_MenuBar.setMenuBare;

public class MainProgram extends Application{

    private static Pane root = new Pane();

    private static Rectangle2D bounds;
    
    public static double stageWidth;
    public static double stageHieght;

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
        setMenuBare(root);
        Scene scene = new Scene(root, stageWidth, stageHieght);
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
    }
}