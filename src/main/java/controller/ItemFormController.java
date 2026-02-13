package controller;

import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.xdevapi.FindStatementImpl;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modal.tableModal.ItemModal;
import modal.tableModal.itemTableModal;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colPackSize;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private TableView tblItems;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
       loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    private void loadTable(){

        ArrayList<itemTableModal> itemList = new ArrayList<>();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while(resultSet.next()){

                itemList.add(
                        new itemTableModal(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getDouble(5)
                        )
                );

            }

            tblItems.setItems(FXCollections.observableArrayList(itemList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            assert newValue !=null;
            setTextValues((itemTableModal) newValue);

        } );
    }

    private void setTextValues(itemTableModal itm){
    txtCode.setText(itm.getCode());
    txtDescription.setText(itm.getDescription());
    txtPackSize.setText(itm.getPackSize());
    txtQty.setText(itm.getQty().toString());
    txtUnitPrice.setText(itm.getUnitPrice().toString());
    }
}
