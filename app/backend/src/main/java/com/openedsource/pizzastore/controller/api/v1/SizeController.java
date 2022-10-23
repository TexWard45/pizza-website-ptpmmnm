package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Size;
import com.openedsource.pizzastore.database.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class SizeController {

    @Autowired
    SizeRepository sizeRepository;

    @GetMapping("/size")
    public ResponseEntity<List<Size>> getSizeList(){
        List<Size> sizeList = sizeRepository.findAll();
        return ResponseEntity.ok().body(sizeList);
    }
    @GetMapping("/size/{id}")
    public ResponseEntity<Object> getSize(@PathVariable(name = "id") Integer id){
        Optional<Size> size = sizeRepository.findById(id);
        return ResponseEntity.ok().body(size.get());
    }
    @PostMapping("/size/add")
    public ResponseEntity<Size> addSize(@Valid @RequestBody Size size) {
        Size newSize =  sizeRepository.save(size);
        return new ResponseEntity<>(newSize, HttpStatus.CREATED);
    }

    @PutMapping("/size/update")
    public ResponseEntity<Size> updateSize(@RequestBody Size size){
        Size updateSize = sizeRepository.save(size);
        return new ResponseEntity<>(updateSize,HttpStatus.OK);
    }
    @DeleteMapping("/size/delete/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable("id")Integer id) {
        sizeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
