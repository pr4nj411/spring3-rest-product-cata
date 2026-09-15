# Tutorial: spring3-rest-product-cata

This is a **Spring 3 REST API** for managing a *product catalog* without using Spring Boot. 
It demonstrates traditional Spring MVC architecture where you can **create, read, update, and delete products** 
through HTTP endpoints. The system validates business rules like *preventing duplicate SKUs* and handles 
errors gracefully with meaningful HTTP responses. It uses an **in-memory storage** system that can easily 
be replaced with a real database.


**Source Repository:** [https://github.com/pr4nj411/spring3-rest-product-cata](https://github.com/pr4nj411/spring3-rest-product-cata)

```mermaid
flowchart TD
    A0["Spring MVC Architecture Pattern
"]
    A1["Product Domain Model
"]
    A2["REST API Controller Layer
"]
    A3["Business Logic Service Layer
"]
    A4["Data Access Repository Layer
"]
    A5["Exception Handling System
"]
    A6["Dependency Injection Container
"]
    A7["HTTP Request/Response Mapping
"]
    A8["Business Rule Validation
"]
    A0 -- "Configures" --> A6
    A2 -- "Delegates to" --> A3
    A2 -- "Returns" --> A1
    A3 -- "Queries" --> A4
    A3 -- "Enforces" --> A8
    A3 -- "Throws" --> A5
    A4 -- "Stores" --> A1
    A5 -- "Handles errors for" --> A2
    A7 -- "Routes requests to" --> A2
    A6 -- "Wires" --> A3
```

## Chapters

1. [Spring MVC Architecture Pattern
](01_spring_mvc_architecture_pattern_.md)
2. [Product Domain Model
](02_product_domain_model_.md)
3. [REST API Controller Layer
](03_rest_api_controller_layer_.md)
4. [HTTP Request/Response Mapping
](04_http_request_response_mapping_.md)
5. [Business Logic Service Layer
](05_business_logic_service_layer_.md)
6. [Business Rule Validation
](06_business_rule_validation_.md)
7. [Data Access Repository Layer
](07_data_access_repository_layer_.md)
8. [Exception Handling System
](08_exception_handling_system_.md)
9. [Dependency Injection Container
](09_dependency_injection_container_.md)
