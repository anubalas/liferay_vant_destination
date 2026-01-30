#include <gtest/gtest.h>
#include "InventoryItem.h"

// Test Fixture for InventoryItem
class InventoryItemTest : public ::testing::Test {
protected:
    InventoryItem* item;

    void SetUp() override {
        // This will be called before each test
    }

    void TearDown() override {
        // This will be called after each test
        delete item;
    }
};

// Test case for constructor with valid values
TEST_F(InventoryItemTest, Constructor_ValidValues) {
    item = new InventoryItem("Widget", 1, 10, 19.99);
    EXPECT_EQ(item->getItemName(), "Widget");
    EXPECT_EQ(item->getItemID(), 1);
    EXPECT_EQ(item->getQuantity(), 10);
    EXPECT_DOUBLE_EQ(item->getPrice(), 19.99);
}

// Test case for constructor with negative quantity
TEST_F(InventoryItemTest, Constructor_NegativeQuantity) {
    item = new InventoryItem("Gadget", 2, -5, 15.00);
    EXPECT_EQ(item->getQuantity(), 0);
}

// Test case for constructor with negative cost
TEST_F(InventoryItemTest, Constructor_NegativeCost) {
    item = new InventoryItem("Thingamajig", 3, 5, -10.00);
    EXPECT_DOUBLE_EQ(item->getPrice(), 0.0);
}

// Test case for constructor with both negative quantity and cost
TEST_F(InventoryItemTest, Constructor_NegativeQuantityAndCost) {
    item = new InventoryItem("Doohickey", 4, -1, -1.00);
    EXPECT_EQ(item->getQuantity(), 0);
    EXPECT_DOUBLE_EQ(item->getPrice(), 0.0);
}

// Test case for getItemName method
TEST_F(InventoryItemTest, GetItemName_ReturnsCorrectName) {
    item = new InventoryItem("TestItem", 5, 10, 20.00);
    EXPECT_EQ(item->getItemName(), "TestItem");
}

// Test case for getItemName with empty name
TEST_F(InventoryItemTest, GetItemName_EmptyName) {
    item = new InventoryItem("", 6, 10, 20.00);
    EXPECT_EQ(item->getItemName(), "");
}

// Test case for getItemName with whitespace name
TEST_F(InventoryItemTest, GetItemName_WhitespaceName) {
    item = new InventoryItem("   ", 7, 10, 20.00);
    EXPECT_EQ(item->getItemName(), "   ");
}

// Test case for getPrice method
TEST_F(InventoryItemTest, GetPrice_ReturnsCorrectPrice) {
    item = new InventoryItem("TestItem", 10, 5, 29.99);
    EXPECT_DOUBLE_EQ(item->getPrice(), 29.99); // Verify price is correct
}

// Test case for getPrice with negative price
TEST_F(InventoryItemTest, GetPrice_NegativePrice) {
    item = new InventoryItem("NegativePriceItem", 11, 5, -5.00);
    EXPECT_DOUBLE_EQ(item->getPrice(), 0.0); // Verify price is set to 0.0
}

// Test case for getPrice with zero price
TEST_F(InventoryItemTest, GetPrice_ZeroPrice) {
    item = new InventoryItem("ZeroPriceItem", 12, 5, 0.00);
    EXPECT_DOUBLE_EQ(item->getPrice(), 0.0); // Verify price is zero
}

// Test case for setItemName method
TEST_F(InventoryItemTest, SetItemName_ValidName) {
    item = new InventoryItem("InitialName", 1, 10, 19.99);
    item->setItemName("NewName");
    EXPECT_EQ(item->getItemName(), "NewName");
}

TEST_F(InventoryItemTest, SetItemName_EmptyName) {
    item = new InventoryItem("InitialName", 1, 10, 19.99);
    item->setItemName("");
    EXPECT_EQ(item->getItemName(), ""); // Verify that the name is set to empty
}

TEST_F(InventoryItemTest, SetItemName_WhitespaceName) {
    item = new InventoryItem("InitialName", 1, 10, 19.99);
    item->setItemName("   ");
    EXPECT_EQ(item->getItemName(), "   "); // Verify that the name is set to whitespace
}