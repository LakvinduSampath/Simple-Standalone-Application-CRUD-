package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modal.tableModal.Customer;
import modal.tableModal.TableModal;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {


    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colCity;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colProvince;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomers;


    @FXML
    private JFXComboBox cmbTitle;

    @FXML
    private DatePicker dateDob;


    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        String id=txtId.getText();
        String name=txtName.getText();
        String title=cmbTitle.getValue().toString();
        LocalDate dob = dateDob.getValue();
        String address=txtAddress.getText();
        Double salary=Double.parseDouble(txtSalary.getText());
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String postalCode=txtPostalCode.getText();

        Customer customer = new Customer(id, title, name, dob, address, salary, city, province, postalCode);

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getTitle());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setObject(4, customer.getDob());
            preparedStatement.setDouble(5, customer.getSalary());
            preparedStatement.setString(6, customer.getAddrss());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getProvince());
            preparedStatement.setString(9, customer.getPostalCode());

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer added").show();
                txtId.setText("");
                cmbTitle.setValue(null);
                txtName.setText("");
                txtAddress.setText("");
                txtCity.setText("");
                txtProvince.setText("");
                txtPostalCode.setText("");
                dateDob.setValue(null);
                dateDob.setValue(null);
                txtSalary.setText("");
                loadTable();

            }else{
                new Alert(Alert.AlertType.ERROR,"customer not added").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    private void loadTable(){

        ArrayList<TableModal> tableList = new ArrayList<TableModal>();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakadeforfxml", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while(resultSet.next()){
                tableList.add(new TableModal(

                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(6),
                        resultSet.getDouble(5),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                ));
            }


            tblCustomers.setItems(FXCollections.observableArrayList(tableList));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbTitle.setItems(FXCollections.observableArrayList(Arrays.asList("Mr","Mrs","Ms"))); //in here crate array list with values MR,MRS,MS
        loadTable();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE id= ? ");
            preparedStatement.setString(1,txtId.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getString(6),
                    resultSet.getDouble(5),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

            );

            System.out.println(customer);
            setDataOfTheCustomer(customer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setDataOfTheCustomer(Customer customer){
        txtId.setText(customer.getId());
        cmbTitle.setValue(customer.getTitle());
        txtName.setText(customer.getName());
        dateDob.setValue(customer.getDob());
        txtAddress.setText(customer.getAddrss());
        txtSalary.setText(customer.getSalary().toString());
        txtCity.setText(customer.getCity());
        txtProvince.setText(customer.getProvince());
        txtPostalCode.setText(customer.getPostalCode());

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id= ?");
            preparedStatement.setString(1,txtId.getText());
            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Can't delete customer").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        btnSearchOnAction(actionEvent);
        txtId.setEditable(false);

        String id=txtId.getText();
        String name=txtName.getText();
        String title=cmbTitle.getValue().toString();
        LocalDate dob = dateDob.getValue();
        String address=txtAddress.getText();
        Double salary=Double.parseDouble(txtSalary.getText());
        String city=txtCity.getText();
        String province=txtProvince.getText();
        String postalCode=txtPostalCode.getText();

        Customer customer = new Customer(id, title, name, dob, address, salary, city, province, postalCode);

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getTitle());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setObject(4, customer.getDob());
            preparedStatement.setDouble(5, customer.getSalary());
            preparedStatement.setString(6, customer.getAddrss());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getProvince());
            preparedStatement.setString(9, customer.getPostalCode());

            if (preparedStatement.executeUpdate()>0){
                new Alert(Alert.AlertType.INFORMATION,"Updated").show();
                txtId.setText("");
                cmbTitle.setValue(null);
                txtName.setText("");
                txtAddress.setText("");
                txtCity.setText("");
                txtProvince.setText("");
                txtPostalCode.setText("");
                dateDob.setValue(null);
                dateDob.setValue(null);
                txtSalary.setText("");
                loadTable();

            }else{
                new Alert(Alert.AlertType.ERROR,"not updated").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
