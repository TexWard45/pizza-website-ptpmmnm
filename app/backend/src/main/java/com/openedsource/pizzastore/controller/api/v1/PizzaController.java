package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Pizza;
import com.openedsource.pizzastore.database.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/pizza")
    public ResponseEntity<List<Pizza>> getPizzaList(){
        List<Pizza> pizzaList = pizzaRepository.findAll();
        return ResponseEntity.ok().body(pizzaList);
    }
    @GetMapping("/pizza/{id}")
    public ResponseEntity<Object> getPizza(@PathVariable(name = "id") Integer id){
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        return ResponseEntity.ok().body(pizza.get());
    }
    @PostMapping("/pizza/add")
    public ResponseEntity<Pizza> addPizza(@Valid @RequestBody Pizza pizza) {
        Pizza newPizza =  pizzaRepository.save(pizza);
        return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
    }

    @PutMapping("/pizza/update")
    public ResponseEntity<Pizza> updatePizza(@RequestBody Pizza pizza){
        Pizza updatePizza = pizzaRepository.save(pizza);
        return new ResponseEntity<>(updatePizza,HttpStatus.OK);
    }
    @DeleteMapping("/pizza/delete/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable("id")Integer id) {
        pizzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
