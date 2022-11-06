package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.StatusDetailEntity;
import com.openedsource.pizzastore.database.repository.StatusDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class StatusDetailController {
    @Autowired
    StatusDetailRepository statusDetailRepository;

    @GetMapping("/statusdetail")
    public ResponseEntity<List<StatusDetailEntity>> getStatusDetailList(){
        List<StatusDetailEntity> statusDetailListList = statusDetailRepository.findAll();
        return ResponseEntity.ok().body(statusDetailListList);
    }
    @GetMapping("/statusdetail/{id}")
    public ResponseEntity<Object> getStatusDetail(@PathVariable(name = "id") Integer id){
        Optional<StatusDetailEntity> statusDetail = statusDetailRepository.findById(id);
        return ResponseEntity.ok().body(statusDetail.get());
    }

}
