# Product Service

Este es el README para el servicio de productos (product-service).

## Cómo ejecutar

Puedes ejecutar la aplicación usando el wrapper de Maven:

```bash
./mvnw spring-boot:run
```

El servicio estará disponible en `http://localhost:8080`.

## Endpoints de la API

A continuación se muestran ejemplos de cómo probar los endpoints de la API usando `curl`.

### 1. Listar productos con filtros y paginación

*   **Sin filtros:**
    ```bash
    curl -X GET "http://localhost:8080/api/products"
    ```
*   **Con filtros (categoría, precio máximo, paginación):**
    ```bash
    curl -X GET "http://localhost:8080/api/products?category=electronics&maxPrice=500&page=0&size=10"
    ```

### 2. Obtener un producto por su ID/SKU
    ```bash
    curl -X GET "http://localhost:8080/api/products/PROD-123"
    ```

### 3. Consultar disponibilidad para múltiples SKUs
    ```bash
    curl -X GET "http://localhost:8080/api/products/availability?ids=SKU-001,SKU-002,SKU-003"
    ```