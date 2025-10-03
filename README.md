# Sintad Project Documentation

This project is a **full-stack application** with a **Spring Boot back end** and an **Angular front end**.
It includes a Dockerized database, a set of example queries, and API endpoints that can be tested with **Postman**.

---

## 📂 Project Structure

* **backend/** → Spring Boot project (run with IntelliJ IDEA or Eclipse).
* **frontend/** → Angular project (run with `ng serve`).
* **database/** → Contains:

  * `docker-compose.yml` → starts the database container.
  * `db.sql` → SQL script with schema creation and example data insertion.

---

## 🚀 Getting Started

### 1. Start the Database

Navigate to the `database/` folder and run:

```bash
docker-compose up -d
```

This will start the database service.

### 2. Run the Back End

Open the Spring Boot project in **IntelliJ IDEA** or **Eclipse**, and run the application.
It will be available at:

```
http://localhost:8080
```

### 3. Run the Front End

Navigate to the Angular project folder and start the development server:

```bash
ng serve
```

It will be available at:

```
http://localhost:4200
```

---

## 🔑 Authentication

Before testing secured endpoints, login with the provided credentials to receive a **Bearer token**.
This token must be included in the `Authorization` header for subsequent requests.

---

## 📬 API Endpoints (Postman)

### Authentication

**POST** Login → `http://localhost:8080/api/v1/clientes/login`

```json
{
  "username": "alice",
  "password": "passAlice"
}
```

Use the returned **Bearer token** for all other requests.

---

### Clientes Endpoints

* **GET** All Clients →
  `http://localhost:8080/api/v1/clientes?limit=3&offset=0`

* **GET** Client by ID →
  `http://localhost:8080/api/v1/clientes/2`

* **GET** Row Count →
  `http://localhost:8080/api/v1/clientes/count`

* **POST** Create Client →
  `http://localhost:8080/api/v1/clientes`

  ```json
  {
    "apellido": "Vasconcelos",
    "correo": "pedro.vasconcelos@email.com",
    "telefono": "987654321",
    "nombre": "Pedro"
  }
  ```

* **PUT** Update Client →
  `http://localhost:8080/api/v1/clientes/2`

  ```json
  {
    "apellido": "Terron",
    "correo": "javier.terrones@email.com",
    "telefono": "987654429",
    "nombre": "Javier"
  }
  ```

* **PATCH** Update Estado →
  `http://localhost:8080/api/v1/clientes/2/false`

* **DELETE** Client by ID →
  `http://localhost:8080/api/v1/clientes/1`

---

## ✅ Summary

* **Database**: run with Docker Compose.
* **Back end**: Spring Boot (IntelliJ/Eclipse).
* **Front end**: Angular (`ng serve`).
* **Testing**: Postman with Bearer authentication.

