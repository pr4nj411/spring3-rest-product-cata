# Spring 3 REST Product Catalog — Non-Spring-Boot Example

This is a deliberately traditional Spring MVC REST application using **Spring Framework 3.2.18.RELEASE** and **no Spring Boot**.

## Architecture

```text
HTTP Client
   |
   v
Servlet Container (Tomcat)
   |
   v
DispatcherServlet
   |
   +--> ProductController
   |       |
   |       v
   |   ProductService
   |       |
   |       v
   |   ProductRepository
   |       |
   |       v
   |   InMemoryProductRepository
   |
   +--> GlobalExceptionHandler
   |
   v
Jackson JSON conversion
```

The application intentionally uses XML configuration and `web.xml` to demonstrate how Spring MVC works without Spring Boot.

## Example use case

The service manages a product catalog.

Supported operations:

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/products` | List products |
| GET | `/api/products/{id}` | Get one product |
| GET | `/api/products?category=ACCESSORY` | Filter by category |
| GET | `/api/products?active=true` | Filter by active state |
| POST | `/api/products` | Create product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

## Requirements

- JDK 7+ for the historical Spring 3.2/JDK target used here
- Maven 3.x
- Servlet 3.0 compatible container, such as Tomcat 7/8
- No Spring Boot

## Build

```bash
mvn clean test package
```

The generated WAR is:

```text
target/spring3-rest-product-catalog.war
```

Deploy the WAR to a Servlet 3.0 compatible Tomcat instance.

## Example requests

List:

```bash
curl http://localhost:8080/spring3-rest-product-catalog/api/products
```

Get by ID:

```bash
curl http://localhost:8080/spring3-rest-product-catalog/api/products/1
```

Create:

```bash
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"sku":"MOU-400","name":"Wireless Mouse","description":"Bluetooth mouse","price":49.99,"category":"ACCESSORY","active":true}' \
  http://localhost:8080/spring3-rest-product-catalog/api/products
```

Update:

```bash
curl -X PUT \
  -H "Content-Type: application/json" \
  -d '{"sku":"MOU-400","name":"Wireless Mouse Pro","description":"Bluetooth mouse","price":59.99,"category":"ACCESSORY","active":true}' \
  http://localhost:8080/spring3-rest-product-catalog/api/products/4
```

Delete:

```bash
curl -X DELETE http://localhost:8080/spring3-rest-product-catalog/api/products/4
```

## Error examples

Unknown product returns HTTP 404:

```json
{
  "code": "PRODUCT_NOT_FOUND",
  "message": "Product with id 999 was not found.",
  "details": []
}
```

Duplicate SKU returns HTTP 409:

```json
{
  "code": "DUPLICATE_SKU",
  "message": "Product SKU already exists: LAP-100",
  "details": []
}
```

Invalid input returns HTTP 400.

## Why this is not Spring Boot

There is intentionally:

- no `spring-boot-starter-*`
- no `@SpringBootApplication`
- no embedded server
- no application.properties-driven Boot configuration
- no Boot auto-configuration
- no Boot actuator

Instead, startup is controlled by:

1. `web.xml`
2. `ContextLoaderListener`
3. `DispatcherServlet`
4. `applicationContext.xml`
5. `rest-servlet.xml`

## Extension points

The repository is deliberately isolated behind `ProductRepository`. A production implementation can replace `InMemoryProductRepository` with:

- JDBC/JdbcTemplate
- Hibernate
- JPA
- MongoDB
- another remote service

The REST controller should remain largely unchanged.

## Important historical note

Spring Framework 3.2 is an old, end-of-life generation. This project is useful for understanding or maintaining legacy Spring MVC systems. For new applications, a supported modern Spring Framework/Spring Boot release should normally be preferred.
