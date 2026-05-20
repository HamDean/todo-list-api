# Todo List API

A Spring Boot REST API for managing todo lists.

## Prerequisites

Before starting the project, make sure you have installed:

- **Java 25**
- **MySQL**
- **Git**

You can use the included Maven Wrapper, so installing Maven separately is **not required**.

---

## Getting Started

### 1. Clone the repository

```bash
git clone <your-repository-url>
cd todo-list-api
```

---

### 2. Create the environment file

Copy the example environment file and create your local `.env` file.

#### On macOS/Linux

```bash
cp .env.example .env
```

#### On Windows (PowerShell)

```powershell
Copy-Item .env.example .env
```

---

### 3. Configure environment variables

Open `.env` and add your local database and JWT configuration:

```env
DB_URL=jdbc:mysql://localhost:3306/todo_list_api
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_secure_jwt_secret
```

Make sure the MySQL database exists before running the project:

```sql
CREATE DATABASE todo_list_api;
```

---

## Running the Project

### On macOS/Linux

```bash
./mvnw spring-boot:run
```

### On Windows

```bash
mvnw.cmd spring-boot:run
```

The API will start at:

```text
http://localhost:8080
```

---

## Running Database Migrations

This project uses **Flyway** for database migrations.

### On macOS/Linux

```bash
./mvnw flyway:migrate
```

### On Windows

```bash
mvnw.cmd flyway:migrate
```

---

## Building the Project

To build the application:

### On macOS/Linux

```bash
./mvnw clean package
```

### On Windows

```bash
mvnw.cmd clean package
```

After building, the generated `.jar` file will be available in the `target/` directory.

---

## Environment Variables

| Variable      | Description                         |
|---------------|-------------------------------------|
| `DB_URL`      | MySQL JDBC connection URL          |
| `DB_USERNAME` | MySQL database username            |
| `DB_PASSWORD` | MySQL database password            |
| `JWT_SECRET`  | Secret key used to sign JWT tokens |

---

## Git Notes

Do **not** commit the `.env` file because it contains private credentials and secrets.

You **should** commit:

- `.env.example`
- `README.md`

You can ensure `.env` is ignored by adding this to your `.gitignore`:

```gitignore
.env
```