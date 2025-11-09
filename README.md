
# order-inventory-microservices-assignment

Two Spring Boot microservices (Inventory Service and Order Service) using H2 database and Spring Data JPA.
Factory Pattern is used in Inventory Service. Lombok and Swagger (springdoc-openapi) included.

## Import into Eclipse (Maven)
1. Unzip the project.
2. In Eclipse: File → Import → Existing Maven Projects → Select the extracted folder `order-inventory-microservices-assignment`.
3. Import both `inventory-service` and `order-service` as separate Maven projects.
4. Run as Spring Boot App:
   - Inventory Service: run `com.example.inventory.InventoryServiceApplication` (port 8081)
   - Order Service: run `com.example.order.OrderServiceApplication` (port 8082)

## Endpoints
- Inventory Service (port 8081)
  - `GET /inventory/{productId}` -> list batches sorted by expiry date
  - `POST /inventory/update` -> updates inventory after an order (payload: { "productId": 1, "quantity": 2 })
  - Swagger UI: http://localhost:8081/swagger-ui.html or /swagger-ui/index.html

- Order Service (port 8082)
  - `POST /order` -> places an order. Example payload:
    ```json
    { "productId": 1, "quantity": 2 }
    ```
  - This will call inventory service to check/update stock.

## Tests
- Run `mvn test` in each module or run tests from Eclipse/JUnit runner.

