import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application implements Functions<String, Commodity> {

  public static HashTableMap<String, Commodity> store = new HashTableMap<>();

  @Override
  /**
   * Search the commodity represented by the name
   * 
   * @param name the name of a commodity
   * @return return the commodity represented by its name
   * @throws NoSuchElementException when the commodity can not be found
   *
   */
  public Commodity search(String name) throws NoSuchElementException {
    if (!store.containsKey(name)) {
      throw new NoSuchElementException("This commodity does not exist.");
    }
    return store.get(name);
  }


  @Override
  /**
   * Edit the information of a commodity
   * 
   * @param commodity the commodity that contains the edited information
   * @throws NoSuchElementException when the commodity can not be found
   */
  public void edit(Commodity commodity) throws NoSuchElementException {
    String name = commodity.getName();
    if (!store.containsKey(name)) {
      throw new NoSuchElementException("This commodity does not exist.");
    }
    store.remove(name);
    Double price = commodity.getPrice();
    Double stock = commodity.getStock();
    Double sold = 100 - stock;
    addNewCommodity(new Commodity(name, price, stock, sold, stock - 20 < 0.001));
  }

  @Override
  /**
   * Remove the commodity represented by its name
   * 
   * @param name the name of a commodity
   * @return return the removed commodity
   * @throws NoSuchElementException when the commodity can not be found
   */
  public Commodity remove(String name) throws NoSuchElementException {
    if (!store.containsKey(name)) {
      throw new NoSuchElementException("This commodity does not exist.");
    }
    return store.remove(name);
  }

  @Override
  /**
   * Clear the database
   */
  public void clear() {
    store.clear();
  }

  @Override
  /**
   * Add a commodity into the databse
   * 
   * @param node with the information of the commodity
   * @throws when the input is incorrect
   */
  public boolean addNewCommodity(Commodity node) throws IllegalArgumentException {
    String tempName = node.getName().replaceAll("\\s+", "");
    if (!tempName.matches("[a-zA-Z]+")) {
      throw new IllegalArgumentException("Illegal name");
    }
    store.put(node.getName(), node);
    return true;
  }

  @Override
  /**
   * Add a file with commodities information
   * 
   * @param fileName the name of the specific file
   */
  public boolean addNewCommodityList() {
    try {
      store = LoadCommodity.loadCommodity("CommodityList.csv");
    } catch (FileNotFoundException e) {
      return false;
    }
    return true;
  }

  @Override
  public void start(Stage primaryStage) {
    // top
    Label text1 = new Label("Welcome to the IC Supermarket database!");
    Label text2 = new Label("\nPress bottom buttons to use, or press help to navigate functions.");
    Label interval = new Label("");
    Button help = new Button("Help");
    Label output = new Label("");
    help.setOnAction(event -> {
      output.setText("\nAdd: enter <Name> + <price>, and split with \",\"."
          + "\nAdd file: add a list of commodities from a csv file ."
          + "\nSearch: enter the commodity name which you wanna search."
          + "\nEdit: enter <Name> + <Price> + <Stock>, and split with \",\"."
          + "\nRemove: enter <Name> which will be removed."
          + "\nClear: clear the commodity information database.");
    });
    VBox textArea = new VBox();
    textArea.getChildren().addAll(text1, text2, interval, help);
    textArea.getChildren().add(output);
    textArea.setAlignment(Pos.CENTER);

    // center
    VBox consoleArea = new VBox();
    TextField console = new TextField();
    consoleArea.getChildren().add(console);
    consoleArea.setAlignment(Pos.CENTER);

    // bottom
    Button add = new Button("Add");
    add.setOnAction(event -> {
      String[] inputs = console.getText().split(",");
      try {
        Commodity added = new Commodity(inputs[0], Double.valueOf(inputs[1]));
        if (addNewCommodity(added)) {
          output.setText("\nAdd successfully!");
        }
      } catch (Exception e) {
        output.setText("\nInvalid inputs!");
      }
      console.clear();
    });

    Button addFile = new Button("Add file");
    addFile.setOnAction(event -> {
      if (addNewCommodityList()) {
        output.setText("\nAdd successfully!");
      } else {
        output.setText("\nFile does not exist!");
      }
      console.clear();
    });

    Button search = new Button("Search");
    search.setOnAction(event -> {
      String inputs = console.getText();
      try {
        Commodity result = search(inputs);
        output.setText("\nName: " + result.getName() + "\nPrice: $ " + result.getPrice()
            + "\nStock: " + result.getStock() + " %\nNeed to restock: " + result.isNeedRestock());
      } catch (Exception e) {
        output.setText("\nCommodity does not exist!");
      }
      console.clear();
    });

    Button edit = new Button("Edit");
    edit.setOnAction(event -> {
      String[] inputs = console.getText().split(",");
      try {
        remove(inputs[0]);
        double tempStock = Double.valueOf(inputs[2]);
        Commodity added = new Commodity(inputs[0], Double.valueOf(inputs[1]), tempStock,
            100 - tempStock, (tempStock - 20) < 0.001);
        if (addNewCommodity(added)) {
          output.setText("\nEdit successfully!");
        }
      } catch (Exception e) {
        output.setText("\nCommodity does not exist!");
      }
      console.clear();
    });

    Button remove = new Button("Remove");
    remove.setOnAction(event -> {
      String inputs = console.getText();
      try {
        remove(inputs);
        output.setText("\nRemove successfully!");
      } catch (Exception e) {
        output.setText("\nCommodity does not exist!");
      }
      console.clear();
    });

    Button clear = new Button("Clear");
    clear.setOnAction(event -> {
      store.clear();
      output.setText("\nClear the database successfully!");
      console.clear();
    });

    HBox buttonArea = new HBox(10); // create space between each 2 buttons
    buttonArea.getChildren().addAll(add, addFile, search, edit, remove, clear);
    buttonArea.setAlignment(Pos.CENTER);

    // pane
    BorderPane pane = new BorderPane();
    pane.setPadding(new Insets(20));
    pane.setTop(textArea);
    pane.setCenter(consoleArea);
    pane.setBottom(buttonArea);
    Scene scene = new Scene(pane, 600, 400);
    primaryStage.setScene(scene);
    primaryStage.setTitle("IC Supermarket");
    primaryStage.show();

  }

  public static void main(String[] args) {
    Application.launch();
  }
}
