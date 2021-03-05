package com.example.inventoryservice;

import com.example.inventoryservice.model.InventoryItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {

    private final Logger logger = LogManager.getLogger(InventoryController.class);

    private static final String LOOKUP_STR = "Looking up inventory info";
    private static final String RETURNING_STR = "Returning inventory info to caller";

    @Autowired
    public InventoryController() {

    }

    @GetMapping(value = "/inventory")
    public ResponseEntity<Object> getInventory() {
        logger.info(LOOKUP_STR);

        List<InventoryItem> inventoryItems = getInventoryInfo();

        logger.info(RETURNING_STR);

        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    @GetMapping(value = "/inventory/{vehicle_id}")
    public ResponseEntity<Object> getInventory(@PathVariable String vehicle_id) {
        logger.info(LOOKUP_STR + " for vehicle ID " + vehicle_id);

        List<InventoryItem> inventoryItems = getInventoryInfo();

        for (InventoryItem inventoryItem : inventoryItems) {
            if (inventoryItem.getVehicleId().trim().equalsIgnoreCase(vehicle_id.trim())) {
                logger.info(RETURNING_STR + " for vehicle ID " + vehicle_id);

                return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
            }
        }

        logger.info("No inventory record found for vehicle ID " + vehicle_id);

        return new ResponseEntity<>(inventoryItems, HttpStatus.NOT_FOUND);
    }

    private List<InventoryItem> getInventoryInfo() {
        List<InventoryItem> inventoryItems = new ArrayList<>();

        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setVehicleId("vehicle-1");
        inventoryItem.setInStockCount(17);

        InventoryItem inventoryItem2 = new InventoryItem();
        inventoryItem2.setVehicleId("vehicle-2");
        inventoryItem2.setInStockCount(39);

        InventoryItem inventoryItem3 = new InventoryItem();
        inventoryItem3.setVehicleId("vehicle-3");
        inventoryItem3.setInStockCount(0);

        InventoryItem inventoryItem4 = new InventoryItem();
        inventoryItem4.setVehicleId("vehicle-4");
        inventoryItem4.setInStockCount(8);

        inventoryItems.add(inventoryItem);
        inventoryItems.add(inventoryItem2);
        inventoryItems.add(inventoryItem3);
        inventoryItems.add(inventoryItem4);

        return inventoryItems;
    }

}
