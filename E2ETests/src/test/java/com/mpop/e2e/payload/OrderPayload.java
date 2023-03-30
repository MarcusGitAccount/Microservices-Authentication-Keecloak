package com.mpop.e2e.payload;

public class OrderPayload {

    private String itemName;

    private String userEmail;

    public OrderPayload(String itemName, String userEmail) {
        this.itemName = itemName;
        this.userEmail = userEmail;
    }

    public OrderPayload() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
