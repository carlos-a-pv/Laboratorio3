package laboratorio3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jdk.jfr.Frequency;
import laboratorio3.MainApp;

import java.io.IOException;

public class LoginController {
    ModelFactoryController modelFactoryController;

    @FXML
    private ImageView imgIcon;
    @FXML
    private TextField tfUser;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Label lbForgetPassword;
    @FXML
    private Label lbRegister;
    @FXML
    private CheckBox remaindMe;

    @FXML
    void initialize(){
        modelFactoryController = ModelFactoryController.getInstance();
        /*try {
           modelFactoryController.startConection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        imgIcon.setImage(new Image("/laboratorio3/resources/book.png"));
        remaindMe.setFocusTraversable(Boolean.FALSE);

        btnLogin.setCursor(Cursor.HAND);
        lbRegister.setCursor(Cursor.HAND);
        lbForgetPassword.setCursor(Cursor.HAND);
        remaindMe.setCursor(Cursor.HAND);

        tfUser.setPromptText("Usuario");
        tfPassword.setPromptText("Contraseña");

        btnLogin.setOnMouseEntered((event) -> {
            btnLogin.setScaleY(1.1);
            btnLogin.setScaleX(1.1);
        });
        btnLogin.setOnMouseExited((event) -> {
            btnLogin.setScaleY(1.0);
            btnLogin.setScaleX(1.0);
        });

        lbForgetPassword.setOnMouseEntered((event) -> {
            lbForgetPassword.setScaleY(1.1);
            lbForgetPassword.setScaleX(1.1);
        });
        lbForgetPassword.setOnMouseExited((event) -> {
            lbForgetPassword.setScaleY(1.0);
            lbForgetPassword.setScaleX(1.0);
        });

        lbRegister.setOnMouseEntered((event) -> {
            lbRegister.setScaleY(1.1);
            lbRegister.setScaleX(1.1);
        });
        lbRegister.setOnMouseExited((event) -> {
            lbRegister.setScaleY(1.0);
            lbRegister.setScaleX(1.0);
        });


        lbForgetPassword.setOnMouseClicked(event -> {
            mostrarMensaje("Notificacion usuario", "Funcionalidad no disponible", "Aun no esta disponible esta funcionalidad", Alert.AlertType.INFORMATION);
        });

        lbRegister.setOnMouseClicked(event -> {
            mostrarMensaje("Notificacion usuario", "Funcionalidad no disponible", "Aun no esta disponible esta funcionalidad", Alert.AlertType.INFORMATION);
        });
    }


    public void onClickLogin() throws IOException {
        String user = tfUser.getText();
        String password = tfPassword.getText();

        if(validarDatos(user,password)){
            //boolean inicio = modelFactoryController.iniciarSesion(user,password);

            if(true){
                mostrarMensaje("Notificación usuario", "Inicio de sesión", "Ha iniciado sesión correctamente", Alert.AlertType.INFORMATION);
                FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/laboratorio3/view/home.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
                Stage stage = new Stage();
                stage.setTitle("Home");
                stage.setScene(scene);
                stage.initOwner(btnLogin.getScene().getWindow());
                btnLogin.getScene().getWindow().hide();
                stage.show();
            }else{
                mostrarMensaje("Error","error al iniciar sesion","El usuario no existe", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarDatos(String user, String password) {
        String mensaje = "";

        if(user == null || user.equals(""))
            mensaje += "El usuario es invalido \n" ;

        if(password == null || password.equals(""))
            mensaje += "La contraseña es invalida \n" ;

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
