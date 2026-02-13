package controller.Customer;

import db.DBConnection;
import javafx.scene.control.Alert;
import modal.tableModal.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImplementation implements CustomerService{
    @Override
    public boolean addCustomer(Customer customer) {
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

                return true;

            }else{
                return false;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public Customer searchCustomer(String id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
