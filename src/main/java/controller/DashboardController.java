package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardController {
    public AnchorPane dashRoot;

    public void btnCustomerFormOnAction(ActionEvent actionEvent) {
        try {
        URL resource = this.getClass().getResource("/view/customer_form.fxml");

        assert resource!=null;

            System.out.println(resource);

             Parent parent= FXMLLoader.load(resource);
             dashRoot.getChildren().clear();
             dashRoot.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnItemFormOnAction(ActionEvent actionEvent) {

        try {
        URL resource = this.getClass().getResource("/view/Item_form.fxml");

        assert  resource!=null;


            Parent parent = FXMLLoader.load(resource);
            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
