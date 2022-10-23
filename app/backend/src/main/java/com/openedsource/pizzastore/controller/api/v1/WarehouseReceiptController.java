package com.openedsource.pizzastore.controller.api.v1;


import com.openedsource.pizzastore.database.entity.WarehouseReceipt;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class WarehouseReceiptController {
    @Autowired
    WarehouseReceiptRepository warehouseReceiptRepository;
    @GetMapping("/warehousereceipt")

    public ResponseEntity<List<WarehouseReceipt>> getWarehouseReceiptList(){
        List<WarehouseReceipt> warehouseReceiptList = warehouseReceiptRepository.findAll();
        return ResponseEntity.ok().body(warehouseReceiptList);
    }
    @GetMapping("/warehousereceipt/{id}")
    public ResponseEntity<Object> getWarehouseReceipt(@PathVariable(name = "id") Integer id){
        Optional<WarehouseReceipt> warehouseReceipt = warehouseReceiptRepository.findById(id);
        return ResponseEntity.ok().body(warehouseReceipt.get());
    }
    @PostMapping("/warehousereceipt/add")
    public ResponseEntity<WarehouseReceipt> addWarehouseReceipt(@Valid @RequestBody WarehouseReceipt warehousereceipt) {
        WarehouseReceipt newWarehouseReceipt =  warehouseReceiptRepository.save(warehousereceipt);
        return new ResponseEntity<>(newWarehouseReceipt, HttpStatus.CREATED);
    }

    @PutMapping("/warehousereceipt/update")
    public ResponseEntity<WarehouseReceipt> updateWarehouseReceipt(@RequestBody WarehouseReceipt warehousereceipt){
        WarehouseReceipt updateWarehouseReceipt = warehouseReceiptRepository.save(warehousereceipt);
        return new ResponseEntity<>(updateWarehouseReceipt,HttpStatus.OK);
    }
    @DeleteMapping("/warehousereceipt/delete/{id}")
    public ResponseEntity<?> deleteWarehouseReceipt(@PathVariable("id")Integer id) {
        warehouseReceiptRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

