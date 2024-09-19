package laboratorio3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import laboratorio3.MainApp;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {

    @FXML
    private ImageView imgBuscar;
    @FXML
    private ImageView imgLogo;
    @FXML
    private ImageView imgCambiarPassword;
    @FXML
    private ImageView imgLogout;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnCambiarPassword;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox contenedor;

    ModelFactoryController modelFactoryController;



    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        //String respuesta = modelFactoryController.cargarLibros();


        contenedor.setPadding(new Insets(30));
        contenedor.setSpacing(40);
        //String[] libros = respuesta.split(";");
        cargarLibros(/*libros*/);
        contenedor.setPadding(new Insets(10, 10, 10, 10));
        imgLogo.setImage(new Image("/laboratorio3/resources/book.png"));
        imgBuscar.setImage(new Image("/laboratorio3/resources/buscar.png"));
        imgCambiarPassword.setImage(new Image("/laboratorio3/resources/cambiar.png"));
        imgLogout.setImage(new Image("/laboratorio3/resources/cerrar-sesion.png"));

        tfBuscar.setPromptText("Buscar por titulo, autor o tema...");

        btnBuscar.setCursor(Cursor.HAND);
        btnCerrarSesion.setCursor(Cursor.HAND);
        btnCambiarPassword.setCursor(Cursor.HAND);

        btnBuscar.setOnMouseEntered((event) -> {
            btnBuscar.setScaleY(1.1);
            btnBuscar.setScaleX(1.1);
        });
        btnBuscar.setOnMouseExited((event) -> {
            btnBuscar.setScaleY(1.0);
            btnBuscar.setScaleX(1.0);
        });

        btnCambiarPassword.setOnMouseEntered((event) -> {
            btnCambiarPassword.setScaleY(1.1);
            btnCambiarPassword.setScaleX(1.1);
        });
        btnCambiarPassword.setOnMouseExited((event) -> {
            btnCambiarPassword.setScaleY(1.0);
            btnCambiarPassword.setScaleX(1.0);
        });

        btnCerrarSesion.setOnMouseEntered((event) -> {
            btnCerrarSesion.setScaleY(1.1);
            btnCerrarSesion.setScaleX(1.1);
        });
        btnCerrarSesion.setOnMouseExited((event) -> {
            btnCerrarSesion.setScaleY(1.0);
            btnCerrarSesion.setScaleX(1.0);
        });
    }

    private void cargarLibros(/*String[] libros*/) {
        for (int i = 0; i <= 5/*libros.length-1*/; i++) {
            Pane libro  = new Pane();

            HBox hbox = new HBox(30);
            VBox vBox = new VBox(20);
            libro.setPrefHeight(500);
            libro.setPadding(new Insets(30));
            libro.setStyle("-fx-background-color: #ccc8c8");

            //String[] detalleLibro = libros[i].split(",");

            Label titulo = new Label("detalleLibro[0]");
            Label  autor = new Label("Autor: " + "detalleLibro[1]");
            Label  tema = new Label("Tema: " + "detalleLibro[2]");
            String disponible = "RESERVADO";/*"detalleLibro[3]";*/

            Button btnReservar = new Button("Reservar");

            if(disponible.equals("RESERVADO")){
                btnReservar.setDisable(true);
            }

            btnReservar.setFont(new javafx.scene.text.Font(18));
            btnReservar.setCursor(Cursor.HAND);
            btnReservar.setStyle("-fx-background-color: #000000");
            btnReservar.setTextFill(Color.rgb(255,255,255));
            titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            btnReservar.setOnMouseClicked(event -> {

                boolean respuesta = modelFactoryController.reservarLibro(titulo.getText());
                if(respuesta){
                    mostrarMensaje("Reserva", "Reserva exitosa", "Su reserva se ha realizado correctamente", Alert.AlertType.INFORMATION);
                    btnReservar.setDisable(true);
                }else{
                    mostrarMensaje("Error", "Reserva fallida", "Su reserva no se ha realizado correctamente", Alert.AlertType.ERROR);
                }
            });

            btnReservar.setOnMouseEntered((event) -> {
                btnReservar.setScaleY(1.1);
                btnReservar.setScaleX(1.1);
            });
            btnReservar.setOnMouseExited((event) -> {
                btnReservar.setScaleY(1.0);
                btnReservar.setScaleX(1.0);
            });
            vBox.getChildren().addAll(titulo, autor, tema);
            hbox.getChildren().addAll(vBox, btnReservar);
            libro.getChildren().addAll(hbox);
            contenedor.getChildren().add(libro);
        }
    }


    public void onClickCambiarPassword(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/laboratorio3/view/cambio-password.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 300);
        Stage stage = new Stage();
        stage.setTitle("Cambio de contraseña");
        stage.setScene(scene);
        stage.initOwner(btnCambiarPassword.getScene().getWindow());
        //btnCambiarPassword.getScene().getWindow().hide();
        stage.show();

    }

    public void onClickCerrarSesion(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/laboratorio3/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        Stage stage = new Stage();
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.initOwner(btnCerrarSesion.getScene().getWindow());
        btnCerrarSesion.getScene().getWindow().hide();
        stage.show();
    }

    public void onClickBuscar(ActionEvent actionEvent) {

        if(!tfBuscar.getText().isEmpty()) {
            String respuesta = modelFactoryController.buscarLibro(tfBuscar.getText());
            String[] libros = respuesta.split(";");
            contenedor.getChildren().clear();
            cargarLibros(/*libros*/);
        }else{
            mostrarMensaje("Error","Campo vacio","Ingrese un valor valido", Alert.AlertType.ERROR);
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
