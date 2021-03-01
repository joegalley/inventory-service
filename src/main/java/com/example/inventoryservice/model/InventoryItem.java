package com.example.inventoryservice.model;

public class InventoryItem {

    private String vehicleId;
    private Integer inStockCount;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getInStockCount() {
        return inStockCount;
    }

    public void setInStockCount(Integer inStockCount) {
        this.inStockCount = inStockCount;
    }
}
