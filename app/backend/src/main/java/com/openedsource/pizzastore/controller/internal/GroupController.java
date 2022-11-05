package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.database.entity.Group;
import com.openedsource.pizzastore.database.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;


    @PostMapping("/group/add")
    public ResponseEntity<Group> addGroup(@Valid @RequestBody Group group) {
        Group newGroup =  groupRepository.save(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @PutMapping("/group/update")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group){
        Group updateGroup = groupRepository.save(group);
        return new ResponseEntity<>(updateGroup,HttpStatus.OK);
    }

    @PutMapping("/group/update/{id}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group,@PathVariable("id")Integer id){
        Optional<Group> groupOptional = groupRepository.findById(id);
        if(groupOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        group.setId(id);
        Group updateGroup = groupRepository.save(group);
        return new ResponseEntity<>(updateGroup,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/group/delete/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id")Integer id) {
        groupRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
