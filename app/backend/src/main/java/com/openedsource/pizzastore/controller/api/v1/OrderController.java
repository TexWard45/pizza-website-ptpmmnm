package com.openedsource.pizzastore.controller.api.v1;

import com.openedsource.pizzastore.database.entity.Order;
import com.openedsource.pizzastore.database.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrderList(){
        List<Order> orderList = orderRepository.findAll();
        return ResponseEntity.ok().body(orderList);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable(name = "id") Integer id){
        Optional<Order> order = orderRepository.findById(id);
        return ResponseEntity.ok().body(order.get());
    }
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
    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id")Integer id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
