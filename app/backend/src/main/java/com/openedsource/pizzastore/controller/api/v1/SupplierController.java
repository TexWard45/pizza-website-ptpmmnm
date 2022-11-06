package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.SupplierEntity;
import com.openedsource.pizzastore.database.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/supplier")
    public ResponseEntity<List<SupplierEntity>> getSupplierList(){
        List<SupplierEntity> supplierList = supplierRepository.findAll();
        return ResponseEntity.ok().body(supplierList);
    }
    @GetMapping("/supplier/{id}")
    public ResponseEntity<Object> getSupplier(@PathVariable(name = "id") Integer id){
        Optional<SupplierEntity> supplier = supplierRepository.findById(id);
        return ResponseEntity.ok().body(supplier.get());
    }

}
