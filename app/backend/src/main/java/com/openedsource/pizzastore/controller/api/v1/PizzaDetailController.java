package com.openedsource.pizzastore.controller.api.v1;


import com.openedsource.pizzastore.database.entity.PizzaDetailEntity;
import com.openedsource.pizzastore.database.repository.PizzaDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class PizzaDetailController {
    @Autowired
    private PizzaDetailRepository pizzaDetailRepository;

    @GetMapping("/pizzadetail")
    public ResponseEntity<List<PizzaDetailEntity>> getPizzaDetailList(){
        List<PizzaDetailEntity> pizzaDetailList = pizzaDetailRepository.findAll();
        return ResponseEntity.ok().body(pizzaDetailList);
    }
    @GetMapping("/pizzadetail/{id}")
    public ResponseEntity<Object> getPizzaDetail(@PathVariable(name = "id") Integer id){
        Optional<PizzaDetailEntity> pizzaDetail = pizzaDetailRepository.findById(id);
        return ResponseEntity.ok().body(pizzaDetail.get());
    }

}
