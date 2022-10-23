package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Topping;
import com.openedsource.pizzastore.database.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ToppingController {
    @Autowired
    ToppingRepository toppingRepository;

    @GetMapping("/topping")
    public ResponseEntity<List<Topping>> getToppingList(){
        List<Topping> toppingList = toppingRepository.findAll();
        return ResponseEntity.ok().body(toppingList);
    }
    @GetMapping("/topping/{id}")
    public ResponseEntity<Object> getTopping(@PathVariable(name = "id") Integer id){
        Optional<Topping> topping = toppingRepository.findById(id);
        return ResponseEntity.ok().body(topping.get());
    }
    @PostMapping("/topping/add")
    public ResponseEntity<Topping> addTopping(@Valid @RequestBody Topping topping) {
        Topping newTopping =  toppingRepository.save(topping);
        return new ResponseEntity<>(newTopping, HttpStatus.CREATED);
    }

    @PutMapping("/topping/update")
    public ResponseEntity<Topping> updateTopping(@RequestBody Topping topping){
        Topping updateTopping = toppingRepository.save(topping);
        return new ResponseEntity<>(updateTopping,HttpStatus.OK);
    }
    @DeleteMapping("/topping/delete/{id}")
    public ResponseEntity<?> deleteTopping(@PathVariable("id")Integer id) {
        toppingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
