#ifndef INVENTORYITEM_H
#define INVENTORYITEM_H

#include <string>
#include <iostream>

class InventoryItem {
private:
    std::string itemName;
    int itemID;
    int quantity;
    double price;

public:
    // Constructor
    InventoryItem(std::string name, int id, int qty, double cost)
        : itemName(name), itemID(id), quantity(qty >= 0 ? qty : 0), price(cost >= 0 ? cost : 0.0) {}

    // Getters
    std::string getItemName() const {
        return itemName;
    }

    int getItemID() const {
        return itemID;
    }

    int getQuantity() const {
        return quantity;
    }

    double getPrice() const {
        return price;
    }

    // Setters
    void setItemName(std::string name) {
        itemName = name;
    }

    void setItemID(int id) {
        itemID = id;
    }

    void setQuantity(int qty) {
        if (qty >= 0) {
            quantity = qty;
        }
    }

    void setPrice(double cost) {
        if (cost >= 0) {
            price = cost;
        }
    }

    // Display method
    void displayItemDetails() const {
        std::cout << "Item Name: " << getItemName() << "
";
        std::cout << "Item ID: " << getItemID() << "
";
        std::cout << "Quantity: " << getQuantity() << "
";
        std::cout << "Price: $" << getPrice() << "
";
    }
};

#endif // INVENTORYITEM_H
