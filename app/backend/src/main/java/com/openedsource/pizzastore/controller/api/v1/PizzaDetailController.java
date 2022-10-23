package com.openedsource.pizzastore.controller.api.v1;


import com.openedsource.pizzastore.database.entity.PizzaDetail;
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
public class PizzaDetailController {
    @Autowired
    private PizzaDetailRepository pizzaDetailRepository;

    @GetMapping("/pizzadetail")
    public ResponseEntity<List<PizzaDetail>> getPizzaDetailList(){
        List<PizzaDetail> pizzaDetailList = pizzaDetailRepository.findAll();
        return ResponseEntity.ok().body(pizzaDetailList);
    }
    @GetMapping("/pizzadetail/{id}")
    public ResponseEntity<Object> getPizzaDetail(@PathVariable(name = "id") Integer id){
        Optional<PizzaDetail> pizzaDetail = pizzaDetailRepository.findById(id);
        return ResponseEntity.ok().body(pizzaDetail.get());
    }
    @PostMapping("/pizzadetail/add")
    public ResponseEntity<PizzaDetail> addPizzaDetail(@Valid @RequestBody PizzaDetail pizzadetail) {
        PizzaDetail newPizzaDetail =  pizzaDetailRepository.save(pizzadetail);
        return new ResponseEntity<>(newPizzaDetail, HttpStatus.CREATED);
    }

    @PutMapping("/pizzadetail/update")
    public ResponseEntity<PizzaDetail> updatePizzaDetail(@RequestBody PizzaDetail pizzadetail){
        PizzaDetail updatePizzaDetail = pizzaDetailRepository.save(pizzadetail);
        return new ResponseEntity<>(updatePizzaDetail,HttpStatus.OK);
    }
    @DeleteMapping("/pizzadetail/delete/{id}")
    public ResponseEntity<?> deletePizzaDetail(@PathVariable("id")Integer id) {
        pizzaDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
