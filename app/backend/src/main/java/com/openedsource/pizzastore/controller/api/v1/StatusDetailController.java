package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.StatusDetail;
import com.openedsource.pizzastore.database.repository.StatusDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class StatusDetailController {
    @Autowired
    StatusDetailRepository statusDetailRepository;

    @GetMapping("/statusdetail")
    public ResponseEntity<List<StatusDetail>> getStatusDetailList(){
        List<StatusDetail> statusDetailListList = statusDetailRepository.findAll();
        return ResponseEntity.ok().body(statusDetailListList);
    }
    @GetMapping("/statusdetail/{id}")
    public ResponseEntity<Object> getStatusDetail(@PathVariable(name = "id") Integer id){
        Optional<StatusDetail> statusDetail = statusDetailRepository.findById(id);
        return ResponseEntity.ok().body(statusDetail.get());
    }
    @PostMapping("/statusdetail/add")
    public ResponseEntity<StatusDetail> addStatusDetail(@Valid @RequestBody StatusDetail statusdetail) {
        StatusDetail newStatusDetail =  statusDetailRepository.save(statusdetail);
        return new ResponseEntity<>(newStatusDetail, HttpStatus.CREATED);
    }

    @PutMapping("/statusdetail/update")
    public ResponseEntity<StatusDetail> updateStatusDetail(@RequestBody StatusDetail statusdetail){
        StatusDetail updateStatusDetail = statusDetailRepository.save(statusdetail);
        return new ResponseEntity<>(updateStatusDetail,HttpStatus.OK);
    }
    @DeleteMapping("/statusdetail/delete/{id}")
    public ResponseEntity<?> deleteStatusDetail(@PathVariable("id")Integer id) {
        statusDetailRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
