package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Base;
import com.openedsource.pizzastore.database.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class BaseController {

    @Autowired
    private BaseRepository baseRepository;

    @GetMapping("/base")
    public ResponseEntity<List<Base>> getBaseList(){
        List<Base> baseList = baseRepository.findAll();
        return ResponseEntity.ok().body(baseList);
    }
    @GetMapping("/base/{id}")
    public ResponseEntity<Object> getBase(@PathVariable(name = "id") Integer id){
        Optional<Base> base = baseRepository.findById(id);
        return ResponseEntity.ok().body(base.get());
    }
    @PostMapping("/base/add")
    public ResponseEntity<Base> addBase(@Valid @RequestBody Base base) {
        Base newBase =  baseRepository.save(base);
        return new ResponseEntity<>(newBase, HttpStatus.CREATED);
    }

    @PutMapping("/base/update")
    public ResponseEntity<Base> updateBase(@RequestBody Base base){
        Base updateBase = baseRepository.save(base);
        return new ResponseEntity<>(updateBase,HttpStatus.OK);
    }
    @PutMapping("/base/update/{id}")
    public ResponseEntity<Base> updateBase(@RequestBody Base base,@PathVariable("id")Integer id){
        Optional<Base> baseOptional = baseRepository.findById(id);
        if(baseOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        base.setId(id);
        Base updateBase = baseRepository.save(base);
        return new ResponseEntity<>(updateBase,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/base/delete/{id}")
    public ResponseEntity<?> deleteBase(@PathVariable("id")Integer id) {
        baseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
