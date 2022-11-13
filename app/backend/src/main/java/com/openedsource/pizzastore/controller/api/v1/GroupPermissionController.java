package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.GroupPermissionEntity;
import com.openedsource.pizzastore.database.repository.GroupPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/grouppermission")
@CrossOrigin(origins = "*")
public class GroupPermissionController {
    @Autowired
    private GroupPermissionRepository groupPermissionRepository;

    @GetMapping
    public ResponseEntity<List<GroupPermissionEntity>> getGroupPermissionList(){
        List<GroupPermissionEntity> groupPermissionList = groupPermissionRepository.findAll();
        return ResponseEntity.ok().body(groupPermissionList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getGroupPermission(@PathVariable(name = "id") Integer id){
        Optional<GroupPermissionEntity> groupPermission = groupPermissionRepository.findById(id);
        return ResponseEntity.ok().body(groupPermission.get());
    }
}
