package controller.item;

import modal.tableModal.ItemModal;

import java.util.List;

public interface ItemService {
    boolean addItem(ItemModal item);
    boolean deleteItem(String id);
    ItemModal searchItem(String id);
    boolean updateItem(ItemModal item);
    List<ItemModal> getAll();
}
