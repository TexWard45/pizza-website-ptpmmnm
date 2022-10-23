package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Status;
import com.openedsource.pizzastore.database.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("/status")
    public ResponseEntity<List<Status>> getStatusList(){
        List<Status> statusList = statusRepository.findAll();
        return ResponseEntity.ok().body(statusList);
    }
    @GetMapping("/status/{id}")
    public ResponseEntity<Object> getStatus(@PathVariable(name = "id") Integer id){
        Optional<Status> status = statusRepository.findById(id);
        return ResponseEntity.ok().body(status.get());
    }
    @PostMapping("/status/add")
    public ResponseEntity<Status> addStatus(@Valid @RequestBody Status status) {
        Status newStatus =  statusRepository.save(status);
        return new ResponseEntity<>(newStatus, HttpStatus.CREATED);
    }

    @PutMapping("/status/update")
    public ResponseEntity<Status> updateStatus(@RequestBody Status status){
        Status updateStatus = statusRepository.save(status);
        return new ResponseEntity<>(updateStatus, HttpStatus.OK);
    }
    @DeleteMapping("/status/delete/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable("id")Integer id) {
        statusRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
