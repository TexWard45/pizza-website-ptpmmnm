package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.OrderEntity;
import com.openedsource.pizzastore.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    public ResponseEntity<List<OrderEntity>> getOrderList(){
        List<OrderEntity> orderList = orderRepository.findAll();
        return ResponseEntity.ok().body(orderList);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable(name = "id") Integer id){
        Optional<OrderEntity> order = orderRepository.findById(id);
        return ResponseEntity.ok().body(order.get());
    }
}
