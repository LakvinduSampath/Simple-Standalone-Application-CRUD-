package controller.Customer;

import modal.tableModal.Customer;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(String id);
    Customer searchCustomer(String id);
    List<Customer> getAll();
}
