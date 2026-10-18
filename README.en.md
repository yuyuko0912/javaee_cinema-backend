# JavaEE Movie Management System

This is a backend project for a movie management system based on Spring Boot, providing movie information management and online ticket purchasing functionality.

## Project Features

- Provides RESTful API interfaces  
- Supports movie information management  
- Implements seat inquiry and ticket purchasing functionality  
- Uses JPA for database operations  
- Includes cross-origin access support  

## Directory Structure

- `src/main/java` - Java source code  
- `src/main/resources` - Configuration files and database scripts  
- `src/test` - Unit tests  

## Main Features

1. **Movie Management**
   - Retrieve a list of all movies  
   - Query detailed information about a specific movie  
   - Check movie seat availability  

2. **Ticketing System**
   - Query movie ticket information  
   - Implement ticket purchasing  
   - Manage seat status  

## API Interfaces

### Movie-related Interfaces `/movies`

- `GET /movies` - Retrieve a list of all movies  
- `GET /movies/{id}` - Retrieve detailed information about a specific movie  
- `GET /movies/{id}/seats` - Query movie seat availability  

### Ticket-related Interfaces `/tickets`

- `GET /tickets/movie/{movieId}` - Query all ticket information for a specific movie  
- `POST /tickets/` - Save ticket information  
- `POST /tickets/buy` - Implement ticket purchasing  

## Database Design

- `movie` table - Stores movie information  
- `ticket` table - Stores ticket information, with a many-to-one relationship to the movie table  

## Technology Stack

- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Spring Boot Test  
- H2 Database (or another database you configure)  

## Development Environment Requirements

- Java 17 or higher  
- Maven 3.8.x  
- IDE (IntelliJ IDEA, Eclipse, or another Java IDE)  
- MySQL or another supported database (based on application.properties configuration)  

## Usage Instructions

1. Clone the project to your local machine  
2. Configure the database connection information (application.properties)  
3. Execute the database scripts (schema.sql and data.sql)  
4. Run Application.java to start the application  
5. Use the API interfaces to manage movies and purchase tickets  

## Contribution Guidelines

Code contributions are welcome! Please ensure the following before submitting a PR:  
- Code adheres to Java coding conventions  
- Appropriate comments and documentation are added  
- Relevant unit tests are included  
- Existing functionality is not broken  

## License

This project is licensed under the MIT License. See the LICENSE file for details.