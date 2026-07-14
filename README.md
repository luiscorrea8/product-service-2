# Microservice ms-customers

Este es el README para el microservicio de clientes y productos.

## Cómo ejecutar

Puedes ejecutar la aplicación usando el wrapper de Maven:

```bash
./mvnw spring-boot:run
```

El servicio estará disponible en `http://localhost:8080`.

## Endpoints de la API

A continuación se muestran ejemplos de cómo probar los endpoints de la API usando `curl`.

### Endpoints de Productos

#### 1. Listar productos con filtros y paginación

*   **Listar todos:**
    ```bash
    curl -X GET "http://localhost:8080/api/products"
    ```
*   **Con filtros:**
    ```bash
    curl -X GET "http://localhost:8080/api/products?category=electronics&maxPrice=500&page=0&size=10"
    ```

#### 2. Obtener un producto por su ID/SKU
    ```bash
    curl -X GET "http://localhost:8080/api/products/PROD-123"
    ```

#### 3. Consultar disponibilidad para múltiples SKUs
    ```bash
    curl -X GET "http://localhost:8080/api/products/availability?ids=SKU-001,SKU-002,SKU-003"
    ```

### Endpoints de Clientes

#### 1. Listar todos los clientes
```bash
curl -X GET "http://localhost:8080/api/customers"
```

#### 2. Obtener un cliente por su ID
```bash
curl -X GET "http://localhost:8080/api/customers/some-customer-id"
```

#### 3. Crear un nuevo cliente
```bash
curl -X POST "http://localhost:8080/api/customers" -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "john.doe@example.com"}'
```

#### 4. Eliminar un cliente por su ID
```bash
curl -X DELETE "http://localhost:8080/api/customers/some-customer-id"
```

### cURL para probar los endpoints

**Crear un cliente (PERSONAL)**
```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/customers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "customerType": "PERSONAL",
  "documentType": "DNI",
  "documentNumber": "12345678",
  "name": "John Doe",
  "companyName": "",
  "email": "john.doe@example.com",
  "status": "ACTIVE"
}'
```

**Crear un cliente (ENTERPRISE)**
```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/customers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "customerType": "ENTERPRISE",
  "documentType": "RUC",
  "documentNumber": "20123456789",
  "name": "Pedro Martinez",
  "companyName": "Tech Solutions S.A.",
  "email": "contact@techsolutions.com",
  "status": "ACTIVE"
}'
```

**Crear un cliente (VIP)**
```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/customers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "customerType": "VIP",
  "documentType": "DNI",
  "documentNumber": "87654321",
  "name": "Jane Smith",
  "companyName": "NADA",
  "email": "jane.smith@example.com",
  "status": "ACTIVE"
}'
```

**Crear un cliente (PYME)**
```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/customers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "customerType": "PYME",
  "documentType": "RUC",
  "documentNumber": "20987654321",
  "name": "Ana Garcia",
  "companyName": "Grupo Innovador EIRL",
  "email": "ventas@grupoinnovador.com",
  "status": "ACTIVE"
}'
```

**Obtener todos los clientes**
```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/customers' \
  -H 'accept: application/json'
```

**Obtener un cliente por ID**
> Reemplaza `{customerId}` con un ID existente.
```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/customers/{customerId}' \
  -H 'accept: application/json'
```

**Actualizar un cliente**
> Reemplaza `{customerId}` con el ID del cliente que quieres actualizar.
```bash
curl -X 'PUT' \
  'http://localhost:8080/api/v1/customers' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": "{customerId}",
  "customerType": "PERSONAL",
  "documentType": "DNI",
  "documentNumber": "87654321",
  "name": "John Doe Updated",
  "companyName": "",
  "email": "john.doe.new@example.com",
  "status": "INACTIVE"
}'
```

**Eliminar un cliente por ID**
> Reemplaza `{customerId}` con un ID existente.
```bash
curl -X 'DELETE' \
  'http://localhost:8080/api/v1/customers/{customerId}' \
  -H 'accept: */*'
```