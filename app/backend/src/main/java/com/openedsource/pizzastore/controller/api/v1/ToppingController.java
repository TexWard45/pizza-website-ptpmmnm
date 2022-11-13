package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.ToppingEntity;
import com.openedsource.pizzastore.database.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/topping")
@CrossOrigin(origins = "*")
public class ToppingController {
    @Autowired
    private ToppingRepository toppingRepository;

    @GetMapping
    public ResponseEntity<List<ToppingEntity>> getToppingList(){
        List<ToppingEntity> toppingList = toppingRepository.findAll();
        return ResponseEntity.ok().body(toppingList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTopping(@PathVariable(name = "id") Integer id){
        Optional<ToppingEntity> topping = toppingRepository.findById(id);
        return ResponseEntity.ok().body(topping.get());
    }

}
