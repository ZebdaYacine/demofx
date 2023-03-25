package com.example.demofx.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class DialogsController {
    public  MFXGenericDialog dialogContent;
    public MFXStageDialog dialog;

    @FXML
    public GridPane grid;

    public DialogsController(Stage stage) {

        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    .setContentText("Model.ipsum")
                    .makeScrollable(true)
                    .get();
            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle("Dialogs Preview")
                    .setOwnerNode(grid)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.addActions(
                    Map.entry(new MFXButton("Confirm"), event -> {
                    }),
                    Map.entry(new MFXButton("Cancel"), event -> dialog.close())
            );

            dialogContent.setMaxSize(400, 200);
        });
    }

     public void openInfo() {
        MFXFontIcon infoIcon = new MFXFontIcon("mfx-info-circle", 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText("This is a generic info dialog");
        convertDialogTo("mfx-info-dialog");
        dialog.showDialog();
    }

    public void openWarning() {
        MFXFontIcon warnIcon = new MFXFontIcon("mfx-do-not-enter-circle", 18);
        dialogContent.setHeaderIcon(warnIcon);
        dialogContent.setHeaderText("This is a warning info dialog");
        convertDialogTo("mfx-warn-dialog");
        dialog.showDialog();
    }

    public void openError() {
        MFXFontIcon errorIcon = new MFXFontIcon("mfx-exclamation-circle-filled", 18);
        dialogContent.setHeaderIcon(errorIcon);
        dialogContent.setHeaderText("This is a error info dialog");
        convertDialogTo("mfx-error-dialog");
        dialog.showDialog();
    }

    public void openGeneric() {
        dialogContent.setHeaderIcon(null);
        dialogContent.setHeaderText("This is a generic dialog");
        convertDialogTo(null);
        dialog.showDialog();
    }

    public void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }
}