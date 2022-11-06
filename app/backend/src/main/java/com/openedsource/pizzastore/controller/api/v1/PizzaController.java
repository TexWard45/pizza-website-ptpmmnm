package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.PizzaEntity;
import com.openedsource.pizzastore.database.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/pizza")
    public ResponseEntity<List<PizzaEntity>> getPizzaList(){
        List<PizzaEntity> pizzaList = pizzaRepository.findAll();
        return ResponseEntity.ok().body(pizzaList);
    }
    @GetMapping("/pizza/{id}")
    public ResponseEntity<Object> getPizza(@PathVariable(name = "id") Integer id){
        Optional<PizzaEntity> pizza = pizzaRepository.findById(id);
        return ResponseEntity.ok().body(pizza.get());
    }

}
