# Tutorial: spring3-rest-product-cata

This is a **Spring 3 REST API** for managing a *product catalog* without using Spring Boot. 
It demonstrates traditional Spring MVC architecture where you can **create, read, update, and delete products** 
through HTTP endpoints. The system validates business rules like *preventing duplicate SKUs* and handles 
errors gracefully with meaningful HTTP responses. It uses an **in-memory storage** system that can easily 
be replaced with a real database.


**Source Repository:** [https://github.com/pr4nj411/spring3-rest-product-cata](https://github.com/pr4nj411/spring3-rest-product-cata)

```mermaid
flowchart TD
    A0["Product Domain Model
"]
    A1["REST API Controller Layer
"]
    A2["Business Logic Service Layer
"]
    A3["Data Access Repository Layer
"]
    A4["Exception Handling System
"]
    A5["Spring MVC Architecture Pattern
"]
    A1 -- "Delegates to" --> A2
    A1 -- "Returns" --> A0
    A2 -- "Queries" --> A3
    A2 -- "Throws" --> A4
    A3 -- "Stores" --> A0
    A4 -- "Handles errors for" --> A1
    A5 -- "Configures" --> A1
```

## Chapters

1. [Spring MVC Architecture Pattern
](01_spring_mvc_architecture_pattern_.md)
2. [Product Domain Model
](02_product_domain_model_.md)
3. [REST API Controller Layer
](03_rest_api_controller_layer_.md)
4. [Business Logic Service Layer
](04_business_logic_service_layer_.md)
5. [Data Access Repository Layer
](05_data_access_repository_layer_.md)
6. [Exception Handling System
](06_exception_handling_system_.md)
