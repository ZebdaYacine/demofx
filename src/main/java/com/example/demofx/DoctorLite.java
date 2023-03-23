/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctorlite;

import doctorlite.BackEnd.DataBaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;


/**
 * @author Zed Yacine
 */

public class DoctorLite extends Application {


    public static Stage loginStage = new Stage();
    public static Stage SecondStage = new Stage();
    public static Stage thirdStage = new Stage();

    public static final Alert alertUpdate = Template(Alert.AlertType.CONFIRMATION,
            "التعديل", "تأكيد التعديل", "تأكد من المعلومات قبل التعديل");
    public static final Alert alertDelete = Template(Alert.AlertType.CONFIRMATION,
            "الحذف", "تأكيد الحذف", "تأكد قبل الحذف");

    public static Alert Template(Alert.AlertType type, String titel, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(titel);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/doctorlite/FrontEnd/layout/Login.fxml"));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.setOnCloseRequest(event -> {
            event.consume();
            if (SecondStage.isShowing()) {
                SecondStage.close();
            }
            if (thirdStage.isShowing()) {
                thirdStage.close();
            }
            loginStage.close();

        });
        loginStage.setTitle("إدارة المدرسة الخاصة");
        loginStage.show();
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

       /* new Thread(() -> {
            DataBaseConnection.Connect();
        }).start();
        launch(args);*/

        /*DSLContext context = DSL.using(DataBaseConnection.Connect(), SQLDialect.MYSQL);

        Result<Record> result = context.select().from("user").where("id = ?", 1).fetch();

        for (Record record : result) {
            int id = record.getValue("id", Integer.class);
            String name = record.getValue("name", String.class);
            System.err.println(id+" "+name);
        }*/

    }
    


}
