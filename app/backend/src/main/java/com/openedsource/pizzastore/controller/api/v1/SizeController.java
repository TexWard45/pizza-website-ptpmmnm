package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.SizeEntity;
import com.openedsource.pizzastore.database.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/size")
@CrossOrigin(origins = "*")
public class SizeController {

    @Autowired
    private SizeRepository sizeRepository;

    @GetMapping
    public ResponseEntity<List<SizeEntity>> getSizeList(){
        List<SizeEntity> sizeList = sizeRepository.findAll();
        return ResponseEntity.ok().body(sizeList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSize(@PathVariable(name = "id") Integer id){
        Optional<SizeEntity> size = sizeRepository.findById(id);
        return ResponseEntity.ok().body(size.get());
    }

}
