package laboratorio3.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.text.StyledEditorKit;

public class CambiarPasswordController {

    ModelFactoryController modelFactoryController;

    @FXML
    private PasswordField tfNuevaPassword;
    @FXML
    private PasswordField tfPasswordRepetida;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();

        btnConfirmar.setOnMouseEntered((event) -> {
            btnConfirmar.setScaleY(1.1);
            btnConfirmar.setScaleX(1.1);
        });
        btnConfirmar.setOnMouseExited((event) -> {
            btnConfirmar.setScaleY(1.0);
            btnConfirmar.setScaleX(1.0);
        });

        btnCancelar.setOnMouseEntered((event) -> {
            btnCancelar.setScaleY(1.1);
            btnCancelar.setScaleX(1.1);
        });
        btnCancelar.setOnMouseExited((event) -> {
            btnCancelar.setScaleY(1.0);
            btnCancelar.setScaleX(1.0);
        });
    }

    public void onClickConfirmar() {

        if(tfNuevaPassword.getText().isEmpty() || tfPasswordRepetida.getText().isEmpty()){
            return;
        }

        if(!tfNuevaPassword.getText().equals(tfPasswordRepetida.getText())) {
            mostrarMensaje("Error", "Contraseña incorrecta", "Las contraseñas no coinciden", Alert.AlertType.ERROR);
            tfNuevaPassword.setText("");
            tfPasswordRepetida.setText("");
            return;
        }
        boolean respuesta = modelFactoryController.cambiarPassword(tfNuevaPassword.getText());
        if(respuesta){
            btnConfirmar.getScene().getWindow().hide();
            mostrarMensaje("Exito","Contraseña cambiada","La contraseña se ha cambiado correctamente", Alert.AlertType.INFORMATION);
        }else{
            mostrarMensaje("Error", "Contraseña incorrecta", "La contraseña no se ha cambiado correctamente", Alert.AlertType.ERROR);
            tfNuevaPassword.setText("");
            tfPasswordRepetida.setText("");
        }
    }

    public void onClickCancelar() {
        btnConfirmar.getScene().getWindow().hide();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {

        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
