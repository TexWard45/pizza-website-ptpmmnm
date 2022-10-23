package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Supplier;
import com.openedsource.pizzastore.database.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> getSupplierList(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return ResponseEntity.ok().body(supplierList);
    }
    @GetMapping("/supplier/{id}")
    public ResponseEntity<Object> getSupplier(@PathVariable(name = "id") Integer id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return ResponseEntity.ok().body(supplier.get());
    }
    @PostMapping("/supplier/add")
    public ResponseEntity<Supplier> addSupplier(@Valid @RequestBody Supplier supplier) {
        Supplier newSupplier =  supplierRepository.save(supplier);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    @PutMapping("/supplier/update")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier){
        Supplier updateSupplier = supplierRepository.save(supplier);
        return new ResponseEntity<>(updateSupplier,HttpStatus.OK);
    }
    @DeleteMapping("/supplier/delete/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable("id")Integer id) {
        supplierRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
