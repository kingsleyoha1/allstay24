# Authentication Service with CQRS Pattern with Clean Architecture

This application uses the CQRS pattern to implement an authentication service.

## Getting Started

### Prerequisites

Ensure you have Docker and Docker Compose installed on your machine.

### Running the Application

1. **Build the application using Docker Compose:**
   ```sh
   docker-compose build
   docker-compose up


The application will be running on http://localhost:8090.

Usage
You can create users via Postman or any other API client.

Endpoints
Register User: POST /api/auth/register
Login User: POST /api/auth/login
Get Authenticated User: GET /api/user/me
Get All Users: GET /api/users

POST /api/auth/register
Content-Type: application/json

{
"fullName" : "kingsleyoha",
"email" : "kingsley200@gmail.com",
"password" : "kingsley1"
}

POST /api/auth/login
Content-Type: application/json

{
"email": "john.doe@example.com",
"password": "password123"
}

Get Authenticated User
GET /api/user/me
Authorization: Bearer <your_jwt_token>

Get All Users
GET /api/users
Authorization: Bearer <your_jwt_token>