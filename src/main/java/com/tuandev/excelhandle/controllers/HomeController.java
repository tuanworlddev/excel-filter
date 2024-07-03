package com.tuandev.excelhandle.controllers;

import com.tuandev.excelhandle.models.Object1;
import com.tuandev.excelhandle.models.Object2;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private File excel1;
    private File excel2;
    private List<Object1> object1List;
    private List<Object2> object2List;
    private FileChooser excelFileChooser;
    private File lastDirectory;

    @FXML
    private TextField input1TF;

    @FXML
    private TextField input2TF;

    @FXML
    private Button handlerBtn;

    @FXML
    private Button export1Btn;

    @FXML
    private Button export2Btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String accessKey = AuthController.getInstance().readKey();
            if (accessKey != null && AuthController.getInstance().verifyAccessKey(accessKey)) {
                AuthController.getInstance().isLoggedIn = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        excelFileChooser = new FileChooser();
        excelFileChooser.setTitle("Excel File");
        excelFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
    }

    @FXML
    protected void onBrowser1(ActionEvent actionEvent) {
        if (!AuthController.getInstance().isLoggedIn) {
            this.showLoginPop();
        } else {
            if (lastDirectory != null) {
                excelFileChooser.setInitialDirectory(lastDirectory);
            }
            excel1 = excelFileChooser.showOpenDialog(null);
            if (excel1 != null) {
                input1TF.setText(excel1.getAbsolutePath());
                lastDirectory = excel1.getParentFile();
            }
        }
    }

    @FXML
    protected void onBrowser2(ActionEvent actionEvent) {
        if (!AuthController.getInstance().isLoggedIn) {
            this.showLoginPop();
        } else {
            if (lastDirectory != null) {
                excelFileChooser.setInitialDirectory(lastDirectory);
            }
            excel2 = excelFileChooser.showOpenDialog(null);
            if (excel2 != null) {
                input2TF.setText(excel2.getAbsolutePath());
                lastDirectory = excel2.getParentFile();
            }
        }
    }

    @FXML
    protected void onExport1(ActionEvent actionEvent) {
        if (lastDirectory != null) {
            excelFileChooser.setInitialDirectory(lastDirectory);
        }
        if (excel1 != null && excel2 != null) {
            File excelFile = this.excelFileChooser.showSaveDialog(null);
            if (excelFile != null) {
                lastDirectory = excelFile.getParentFile();
                try {
                    object1List = Convert.convertObject1(excel1);
                    object2List = Convert.convertObject2(excel2);
                    boolean success = Handler.export1Handle(excelFile, object1List, object2List);
                    if (success) {
                        showOpenExcelDialog(excelFile);
                    }
                } catch (IOException e) {
                    showErrorDialog(e.getMessage());
                }
            }
        }
    }

    @FXML
    protected void onExport2(ActionEvent actionEvent) {
        if (lastDirectory != null) {
            excelFileChooser.setInitialDirectory(lastDirectory);
        }
        if (excel2 != null) {
            File excelFile = this.excelFileChooser.showSaveDialog(null);
            if (excelFile != null) {
                lastDirectory = excelFile.getParentFile();
                try {
                    object2List = Convert.convertObject2(excel2);
                    boolean success = Handler.export2Handle(excelFile, object2List);
                    if (success) {
                        showOpenExcelDialog(excelFile);
                    }
                } catch (IOException e) {
                    showErrorDialog(e.getMessage());
                }
            }
        }
    }

    private void showOpenExcelDialog(File excelFile) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Open Excel File");
        alert.setHeaderText((String)null);
        alert.setContentText("Excel file has been saved. Do you want to open it now?");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(new ButtonType[]{buttonTypeYes, buttonTypeNo});
        alert.showAndWait().ifPresent((buttonType) -> {
            if (buttonType == buttonTypeYes) {
                try {
                    Desktop.getDesktop().open(excelFile);
                } catch (IOException var5) {
                    showErrorDialog(var5.getMessage());
                }
            }

        });
    }

    private void showErrorDialog(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText((String)null);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    private void showLoginPop() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        vBox.setSpacing(10.0);
        vBox.setPrefWidth(300.0);
        Label keyLabel = new Label("Access Key:");
        vBox.getChildren().add(keyLabel);
        PasswordField keyField = new PasswordField();
        keyField.setPromptText("••••••••••");
        vBox.getChildren().add(keyField);
        CheckBox rememberCheckBox = new CheckBox("Remember me");
        vBox.getChildren().add(rememberCheckBox);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getDialogPane().setContent(vBox);
        alert.setTitle("Login");
        Objects.requireNonNull(keyField);
        Platform.runLater(keyField::requestFocus);
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent((buttonType) -> {
            if (buttonType == ButtonType.OK) {
                String key = keyField.getText();
                boolean remember = rememberCheckBox.isSelected();
                if (key.equals("HongRancho")) {
                    AuthController.getInstance().isLoggedIn = true;
                    if (remember) {
                        try {
                            AuthController.getInstance().writeKey(key);
                        } catch (IOException var7) {
                            this.showErrorDialog(var7.getMessage());
                        }
                    }

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText((String)null);
                    successAlert.setContentText("You have successfully logged in!");
                    successAlert.showAndWait();
                } else {
                    this.showErrorDialog("Access Key Invalid");
                }
            }

        });
    }

    public void onLogout(ActionEvent actionEvent) {
        try {
            AuthController.getInstance().isLoggedIn = false;
            AuthController.getInstance().writeKey("");
            this.showLoginPop();
        } catch (IOException var3) {
            this.showErrorDialog(var3.getMessage());
        }

    }

    public void onLogin(ActionEvent actionEvent) {
        this.showLoginPop();
    }
}