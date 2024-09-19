package laboratorio3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/laboratorio3/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
