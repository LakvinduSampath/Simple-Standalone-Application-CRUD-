package controller.item;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import modal.tableModal.ItemModal;
import modal.tableModal.itemTableModal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImplementation implements ItemService{
    @Override
    public boolean addItem(ItemModal item) {
        return false;
    }

    @Override
    public boolean deleteItem(String id) {
        return false;
    }

    @Override
    public ItemModal searchItem(String id) {
        return null;
    }

    @Override
    public boolean updateItem(ItemModal item) {
        return false;
    }

    @Override
    public List<ItemModal> getAll() {
        ArrayList<ItemModal> itemList = new ArrayList<>();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while(resultSet.next()){

                itemList.add(
                        new ItemModal(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getDouble(5)
                        )
                );

            }

            return itemList;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
