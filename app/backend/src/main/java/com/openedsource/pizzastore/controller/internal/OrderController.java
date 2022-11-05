package com.openedsource.pizzastore.controller.internal;

import com.openedsource.pizzastore.database.entity.Order;
import com.openedsource.pizzastore.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order/add")
    public ResponseEntity<Order> addOrder(@Valid @RequestBody Order order) {
        Order newOrder =  orderRepository.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/order/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Order updateOrder = orderRepository.save(order);
        return new ResponseEntity<>(updateOrder,HttpStatus.OK);
    }

    @PutMapping("/order/update/{id}")
    public ResponseEntity<Order> updateGroup(@RequestBody Order order,@PathVariable("id")Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        order.setId(id);
        Order updateGroup = orderRepository.save(order);
        return new ResponseEntity<>(updateGroup,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id")Integer id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
