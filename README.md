# User Service Microservice

This microservice is responsible for **authorizing users** contacting the e-commerce platform. It handles user authentication, registration, and role-based access control.

---

## Features
- 🔹 User authentication using JWT.
- 🔹 Role-based access control (`USER`, `ADMIN`).
- 🔹 Secure user login and registration.
- 🔹 Token validation for API access.

---

## Tech Stack
- **Spring Boot** – Backend framework
- **Spring Security & JWT** – Authentication & Authorization
- **MySQL** – Database for user storage

---

## API Endpoints
| Endpoint | Method | Description |
|---------|--------|------------|
| `/api/users/register` | POST | Register a new user |
| `/api/users/login` | POST | Authenticate user & return JWT |
| `/api/users/validate` | GET | Validate user token |
