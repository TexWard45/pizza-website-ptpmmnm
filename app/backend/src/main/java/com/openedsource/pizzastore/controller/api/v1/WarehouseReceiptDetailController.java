package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.WarehouseReceiptDetailEntity;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/warehousereceiptdetail")
@CrossOrigin(origins = "*")
public class WarehouseReceiptDetailController {
    @Autowired
    private WarehouseReceiptDetailRepository warehouseReceiptDetailRepository;
    @GetMapping
    public ResponseEntity<List<WarehouseReceiptDetailEntity>> getWarehouseReceiptDetailList(){
        List<WarehouseReceiptDetailEntity> warehouseReceiptList = warehouseReceiptDetailRepository.findAll();
        return ResponseEntity.ok().body(warehouseReceiptList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWarehouseReceiptDetail(@PathVariable(name = "id") Integer id){
        Optional<WarehouseReceiptDetailEntity> warehouseReceiptDetail = warehouseReceiptDetailRepository.findById(id);
        return ResponseEntity.ok().body(warehouseReceiptDetail.get());
    }

}
