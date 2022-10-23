package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.WarehouseReceiptDetail;
import com.openedsource.pizzastore.database.repository.WarehouseReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class WarehouseReceiptDetailController {
    @Autowired
    WarehouseReceiptDetailRepository warehouseReceiptDetailRepository;
    @GetMapping("/warehousereceiptdetail")

    public ResponseEntity<List<WarehouseReceiptDetail>> getWarehouseReceiptDetailList(){
        List<WarehouseReceiptDetail> warehouseReceiptList = warehouseReceiptDetailRepository.findAll();
        return ResponseEntity.ok().body(warehouseReceiptList);
    }
    @GetMapping("/warehousereceiptdetail/{id}")
    public ResponseEntity<Object> getWarehouseReceiptDetail(@PathVariable(name = "id") Integer id){
        Optional<WarehouseReceiptDetail> warehouseReceiptDetail = warehouseReceiptDetailRepository.findById(id);
        return ResponseEntity.ok().body(warehouseReceiptDetail.get());
    }
    @PostMapping("/warehousereceiptdetail/add")
    public ResponseEntity<WarehouseReceiptDetail> addWarehouseReceiptDetail(@Valid @RequestBody WarehouseReceiptDetail warehousereceiptdetail) {
        WarehouseReceiptDetail newWarehouseReceiptDetail =  warehouseReceiptDetailRepository.save(warehousereceiptdetail);
        return new ResponseEntity<>(newWarehouseReceiptDetail, HttpStatus.CREATED);
    }

    @PutMapping("/warehousereceiptdetail/update")
    public ResponseEntity<WarehouseReceiptDetail> updateWarehouseReceiptDetail(@RequestBody WarehouseReceiptDetail warehousereceiptdetail){
        WarehouseReceiptDetail updateWarehouseReceiptDetail = warehouseReceiptDetailRepository.save(warehousereceiptdetail);
        return new ResponseEntity<>(updateWarehouseReceiptDetail,HttpStatus.OK);
    }
    @DeleteMapping("/warehousereceiptdetail/delete/{id}")
    public ResponseEntity<?> deleteWarehouseReceiptDetail(@PathVariable("id")Integer id) {
        warehouseReceiptDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
