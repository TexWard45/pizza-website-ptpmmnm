package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.StatusEntity;
import com.openedsource.pizzastore.database.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/status")
    public ResponseEntity<List<StatusEntity>> getStatusList(){
        List<StatusEntity> statusList = statusRepository.findAll();
        return ResponseEntity.ok().body(statusList);
    }
    @GetMapping("/status/{id}")
    public ResponseEntity<Object> getStatus(@PathVariable(name = "id") Integer id){
        Optional<StatusEntity> status = statusRepository.findById(id);
        return ResponseEntity.ok().body(status.get());
    }

}
