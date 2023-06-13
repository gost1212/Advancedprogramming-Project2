package program.MenuBar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import program.Pages.SearchPage;
import program.Pages.DeletePage;

import static program.Pages.AddPage.setAddPage;
import static program.SQL.SQLCommands.close;
import static program.MainProgram.credit;

public class Stage_MenuBar {
    
    private static MenuBar menuBar = new MenuBar();

    private static Menu products = new Menu("Products");
    private static Menu product = new Menu("Product");

    private static MenuItem add = new MenuItem("Add");
    private static MenuItem search = new MenuItem("Search");
    private static MenuItem delete = new MenuItem("Delete");
    private static MenuItem exit = new MenuItem("Exit");

    static{
        product.getItems().addAll(add, search, delete);
        products.getItems().addAll(product, exit);

        menuBar.getMenus().add(products);
    }

    public static void setMenuBare(Pane root){
        add.setOnAction(e->{
            root.getChildren().remove(2);
            setAddPage(root);
        });
        search.setOnAction(e->{
            root.getChildren().remove(2);
            SearchPage.setSearchPage(root);
            SearchPage.getTable();
        });
        delete.setOnAction(e->{
            root.getChildren().remove(2);
            DeletePage.setDeletePage(root);
            DeletePage.getTable();
        });
        exit.setOnAction(e->{
            close();
            System.exit(0);
        });
        root.getChildren().addAll(menuBar);
    }
}
