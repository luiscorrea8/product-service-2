package com.splitcart.product.api;

import com.splitcart.product.entity.CustomerEntity;
import com.splitcart.product.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /**
     * GET /api/customers : Obtiene todos los clientes.
     *
     * @return Un Flux que emite todos los clientes.
     */
    @GetMapping
    public Flux<CustomerEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * GET /api/customers/{id} : Obtiene un cliente por su ID.
     *
     * @param id El ID del cliente a buscar.
     * @return Un Mono que emite el cliente encontrado o un estado 404 si no existe.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerEntity>> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok) // Si se encuentra, envuelve en un ResponseEntity 200 OK
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Si el Mono está vacío, devuelve 404 Not Found
    }

    /**
     * POST /api/customers : Crea un nuevo cliente.
     *
     * @param customer El cliente a crear.
     * @return Un Mono que emite el cliente creado con un estado 201 Created.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * DELETE /api/customers/{id} : Elimina un cliente por su ID.
     *
     * @param id El ID del cliente a eliminar.
     * @return Un Mono<Void> que se completa cuando la eliminación ha terminado.
     */
    @DeleteMapping("/{id}")
    public Mono<Boolean> deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }
}