# uRide - Online Cab Booking Application Backend

uRide is an online cab booking backend system built using Spring Boot, offering essential functionalities for both riders and drivers. It implements JWT-based authentication and authorization, ride management, and payment processing, with features like driver discovery strategies, fare calculation strategies, and distance computation using the OSRM API.

## Features

### Core Technologies:
- **Spring Boot**: For creating a scalable and production-ready application.
- **Spring Security with JWT**: For authentication and authorization using JSON Web Tokens.
- **Spring Data JPA**: For database operations.
- **PostgreSQL**: As the relational database.

### Functionality Overview:

#### Authentication & Authorization
- Implemented using **JWT (JSON Web Token)** with **Spring Security** to secure API endpoints.
- Users authenticate with a email and password, receiving a JWT token for subsequent requests.
- Authorization checks are performed based on the roles embedded in the JWT token.

#### Distance Calculation
- The distance between pickup and dropoff locations is calculated using the **OSRM API**.

### Driver Features:
- **Accept Ride**: Accept a ride request from a rider.
- **Cancel Ride**: Cancel an accepted ride.
- **Start Ride**: Start a ride after OTP verification.
- **End Ride**: Complete the ride.
- **Rate Rider**: Provide a rating for the rider after the ride.

### Rider Features:
- **Request Ride**: Request a ride by specifying pickup and dropoff locations.
- **Cancel Ride**: Cancel a ride request before it’s accepted.
- **Rate Driver**: Rate the driver after completing a ride.

### OTP Verification
- An OTP-based system ensures that a ride can only start after correct verification.

### Driver Finding Strategies
- **Top Rated Drivers**: Prioritizes assigning rides to drivers based on their ratings.
- **Nearest Drivers**: Assigns rides to the nearest available drivers.

### Fare Calculation Strategies
- **Default Fare Calculation**: Based on the distance between pickup and dropoff points.
- **Surge Pricing**: Dynamic pricing based on conditions such as high demand or peak hours.

### Payment Options
- **Wallet Payment**: Riders can pay using their in-app wallet.
- **Cash Payment**: Riders can choose to pay in cash after the ride.

### API Documentation & Monitoring
- **Swagger UI**: Provides an interface for testing and exploring the API.
- **Spring Boot Actuators**: Offers production-ready monitoring and management capabilities.

## JWT Implementation

The application uses JWT for authentication and authorization:

1. **User Authentication**:
   - Users log in with their credentials and receive a JWT token upon successful authentication.
   
2. **Token Storage**:
   - The client stores the JWT token (e.g., in cookies).
   
3. **Securing Endpoints**:
   - Each request to a secured endpoint must include the JWT token in the `Authorization` header (`Bearer <token>`).
   - The backend verifies the token’s validity and extracts user details for authorization.

4. **Authorization**:
   - Roles and permissions embedded in the JWT token are checked during every request.

## Project Structure

- `advices`: Handles global exception management and advice controllers.
- `configs`: Contains configuration files for Spring Security, JWT settings, and other app-related configurations.
- `controllers`: Handles incoming HTTP requests and defines API endpoints.
- `dtos`: Data Transfer Objects for transferring data between layers.
- `entities`: The core database entities, such as Driver, Rider, and Ride.
- `exceptions`: Custom exceptions for handling errors.
- `repositories`: Interfaces for database access using Spring Data JPA.
- `services`: Contains business logic implementations, such as ride handling, fare calculation, etc.
- `strategies`: Driver and fare calculation strategies (top-rated, nearest driver, default fare, surge pricing).
- `utils`: Utility classes for common functionalities across the application.
