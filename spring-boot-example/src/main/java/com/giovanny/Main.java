package com.giovanny;

import com.giovanny.Exception.ResourceNotFoundException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer updatedEmployee = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exist with id: " + id));
        updatedEmployee.setName(request.name);
        updatedEmployee.setEmail(request.email);
        updatedEmployee.setAge(request.age);
        customerRepository.save(updatedEmployee);
        return "Updated successfully!";
    }
}
