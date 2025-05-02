// package specific to this expenses app project
package com.example.expensesappproject;

// imports all the necessary libraries for the code to function
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml")); // FXML file
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // sets the width and height
        stage.setTitle("Expense Tracker"); // window title
        stage.setScene(scene); // sets the scene
        stage.show(); // displays the window
    }

    public static void main(String[] args) {
        launch(); // launches JavaFX application
    }
}
