package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.ToppingDetail;
import com.openedsource.pizzastore.database.repository.ToppingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
