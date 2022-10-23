package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.ToppingDetail;
import com.openedsource.pizzastore.database.repository.ToppingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ToppingDetailController {
    @Autowired
    ToppingDetailRepository toppingDetailRepository;
    @GetMapping("/toppingdetail")

    public ResponseEntity<List<ToppingDetail>> getToppingDetailList(){
        List<ToppingDetail> toppingDetailList = toppingDetailRepository.findAll();
        return ResponseEntity.ok().body(toppingDetailList);
    }
    @GetMapping("/toppingdetail/{id}")
    public ResponseEntity<Object> getToppingDetail(@PathVariable(name = "id") Integer id){
        Optional<ToppingDetail> toppingDetail = toppingDetailRepository.findById(id);
        return ResponseEntity.ok().body(toppingDetail.get());
    }
    @PostMapping("/toppingdetail/add")
    public ResponseEntity<ToppingDetail> addToppingDetail(@Valid @RequestBody ToppingDetail toppingdetail) {
        ToppingDetail newToppingDetail =  toppingDetailRepository.save(toppingdetail);
        return new ResponseEntity<>(newToppingDetail, HttpStatus.CREATED);
    }

    @PutMapping("/toppingdetail/update")
    public ResponseEntity<ToppingDetail> updateToppingDetail(@RequestBody ToppingDetail toppingdetail){
        ToppingDetail updateToppingDetail = toppingDetailRepository.save(toppingdetail);
        return new ResponseEntity<>(updateToppingDetail,HttpStatus.OK);
    }
    @DeleteMapping("/toppingdetail/delete/{id}")
    public ResponseEntity<?> deleteToppingDetail(@PathVariable("id")Integer id) {
        toppingDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
