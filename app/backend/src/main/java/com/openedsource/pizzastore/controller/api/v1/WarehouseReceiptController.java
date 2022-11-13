package com.openedsource.pizzastore.controller.api.v1;


import com.openedsource.pizzastore.database.entity.WarehouseReceiptEntity;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/warehousereceipt")
@CrossOrigin(origins = "*")
public class WarehouseReceiptController {
    @Autowired
    private WarehouseReceiptRepository warehouseReceiptRepository;
    @GetMapping
    public ResponseEntity<List<WarehouseReceiptEntity>> getWarehouseReceiptList(){
        List<WarehouseReceiptEntity> warehouseReceiptList = warehouseReceiptRepository.findAll();
        return ResponseEntity.ok().body(warehouseReceiptList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWarehouseReceipt(@PathVariable(name = "id") Integer id){
        Optional<WarehouseReceiptEntity> warehouseReceipt = warehouseReceiptRepository.findById(id);
        return ResponseEntity.ok().body(warehouseReceipt.get());
    }

}

