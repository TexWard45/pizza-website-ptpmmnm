package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.ToppingDetailEntity;
import com.openedsource.pizzastore.database.repository.ToppingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class ToppingDetailController {
    @Autowired
    private ToppingDetailRepository toppingDetailRepository;
    @GetMapping("/toppingdetail")

    public ResponseEntity<List<ToppingDetailEntity>> getToppingDetailList(){
        List<ToppingDetailEntity> toppingDetailList = toppingDetailRepository.findAll();
        return ResponseEntity.ok().body(toppingDetailList);
    }
    @GetMapping("/toppingdetail/{id}")
    public ResponseEntity<Object> getToppingDetail(@PathVariable(name = "id") Integer id){
        Optional<ToppingDetailEntity> toppingDetail = toppingDetailRepository.findById(id);
        return ResponseEntity.ok().body(toppingDetail.get());
    }
}
