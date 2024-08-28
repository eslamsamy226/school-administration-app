# School Administration App

This is a Spring Boot REST API for managing a school administration system. The system handles student registrations, course management, and student enrollment in courses. The application uses `Spring Security` for authentication and `JWT` tokens for secure communication.

## Features

- **Student Management**: CRUD operations for students.
- **Course Management**: CRUD operations for courses.
- **Authentication**: Secure login and registration with JWT tokens.

## Prerequisites

- Java 17+
- Maven 3.6+
- Postgresql or any preferred relational database

## Getting Started


### Configuration

1. **Database Configuration:**

   Update your `application.properties` or `application.yml` file with your database configurations.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
  spring.datasource.username=username
  spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   ```

2. **Run the Application:**

   Use Maven to build and run the project:

   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Authentication

- **Register a User**

  ```http
  POST /api/auth/register
  ```

  **Request Body:**

  ```json
  {
    "firstname": "John",
    "lastname": "Doe",
    "email": "johndoe@example.com",
    "password": "password"
  }
  ```

  **Response:**

  ```json
  {
    "token": "your-jwt-token"
  }
  ```
  This will also create a Student with the coresponding data
- **Login**

  ```http
  POST /api/auth/login
  ```

  **Request Body:**

  ```json
  {
    "email": "johndoe@example.com",
    "password": "password"
  }
  ```

  **Response:**

  ```json
  {
    "token": "your-jwt-token"
  }
  ```

### Student Management

- **Get All Students**

  ```http
  GET /api/students
  ```

  **Response:**

  ```json
  [
    {
      "id": 1,
      "name": "Jane Doe",
      "email": "jane@example.com"
    }
  ]
  ```

- **Create a Student**

  ```http
  POST /api/students
  ```

  **Request Body:**

  ```json
  {
    "name": "Jane Doe",
    "email": "jane@example.com"
  }
  ```

  **Response:**

  ```json
  {
    "id": 1,
    "name": "Jane Doe",
    "email": "jane@example.com"
  }
  ```

- **Enroll a Student in a Course**

  ```http
  POST /api/students/{studentId}/enroll/{courseId}
  ```

  **Response:**

  ```json
  {
    "id": 1,
    "name": "Jane Doe",
    "email": "jane@example.com",
    "courses": [
      {
        "id": 1,
        "courseName": "Math 101"
      }
    ]
  }
  ```

### Course Management

- **Get All Courses**

  ```http
  GET /api/courses
  ```

  **Response:**

  ```json
  [
    {
      "id": 1,
      "courseName": "Math 101",
      "description": "Basic Mathematics"
    }
  ]
  ```

- **Create a Course**

  ```http
  POST /api/courses
  ```

  **Request Body:**

  ```json
  {
    "courseName": "Physics 101",
    "description": "Introduction to Physics"
  }
  ```

  **Response:**

  ```json
  {
    "id": 2,
    "courseName": "Physics 101",
    "description": "Introduction to Physics"
  }
  ```

## Error Handling

Validation errors are handled gracefully. If a validation fails, the API will return a `400 Bad Request` with details about the validation error.

**Example Response:**

```json
{
  "email": "Email should be valid e.g. (user@email.com)",
  "name": "Name should be between 3 and 150 characters"
}
```
