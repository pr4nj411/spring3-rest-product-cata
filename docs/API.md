# Product API Contract

Base path:

`/api/products`

## Product representation

```json
{
  "id": 1,
  "sku": "LAP-100",
  "name": "Developer Laptop",
  "description": "16GB RAM, 512GB SSD",
  "price": 1299.99,
  "category": "COMPUTER",
  "active": true
}
```

## POST /api/products

Request:

```json
{
  "sku": "MOU-400",
  "name": "Wireless Mouse",
  "description": "Bluetooth mouse",
  "price": 49.99,
  "category": "ACCESSORY",
  "active": true
}
```

Response: `201 Created`

## PUT /api/products/{id}

Response: `200 OK`

## DELETE /api/products/{id}

Response: `204 No Content`

## Error model

```json
{
  "code": "PRODUCT_NOT_FOUND",
  "message": "Product with id 999 was not found.",
  "details": []
}
```

HTTP status mapping:

- 400 — invalid request
- 404 — product not found
- 409 — duplicate SKU
- 500 — unexpected server failure
