package controller.item;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.List;
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

        ItemServiceImplementation itm = new ItemServiceImplementation();
        List<ItemModal> all = itm.getAll();

        ArrayList<itemTableModal> itemList = new ArrayList<>();
        all.forEach(item ->{
            itemList.add(
                    new itemTableModal(
                            item.getCode(),
                            item.getDescription(),
                            item.getPackSize(),
                            item.getUnitPrice(),
                            item.getQty()
                    )
            );

        } );

        tblItems.setItems(FXCollections.observableArrayList(all));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
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
