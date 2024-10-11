# uRide - Online Cab Booking Application Backend

uRide is an online cab booking backend system built using Spring Boot, providing essential functionalities for both riders and drivers. It handles authentication, ride management, and payment processing, with features like driver discovery strategies, fare calculation strategies, and distance computation using the OSRM API.

## Features

### Core Technologies:
- **Spring Boot**: For creating a scalable and production-ready application.
- **Spring Security**: For authentication and authorization.
- **Spring Data JPA**: For database operations.
- **PostgreSQL**: As the relational database.

### Functionality Overview:

#### Authentication & Authorization
- Implemented using **Spring Security** to ensure secure login and access control.

#### Distance Calculation
- Calculated the distance between the pickup and dropoff locations using the **OSRM API**.

### Driver Features:
- **Accept Ride**: Accept a ride request from a rider.
- **Cancel Ride**: Cancel an accepted ride.
- **Start Ride**: Start a ride after OTP verification.
- **End Ride**: End the ride and complete the journey.
- **Rate Rider**: Provide a rating for the rider after the ride is completed.

### Rider Features:
- **Request Ride**: Request a ride with specified pickup and dropoff locations.
- **Cancel Ride**: Cancel a ride request before it's started.
- **Rate Driver**: Rate the driver after completing a ride.

### OTP Verification
- An OTP-based verification system to ensure that the ride starts securely.

### Driver Finding Strategies
- **Top Rated Drivers**: Assigns rides to drivers based on their ratings.
- **Nearest Drivers**: Assigns rides to the nearest available drivers.

### Fare Calculation Strategies
- **Default Fare Calculation**: Based on the distance between pickup and dropoff locations.
- **Surge Pricing**: Dynamically adjusts fare based on selected conditions such as peak hours or high demand.

### Payment Options
- **Wallet Payment**: Riders can pay using their in-app wallet.
- **Cash Payment**: Riders can choose to pay in cash at the end of the ride.

### API Documentation & Monitoring
- **Swagger UI**: Provides a user-friendly interface for testing and exploring the API.
- **Spring Boot Actuators**: Offers production-ready monitoring and management capabilities.

## Project Structure

- `advices`: Exception handling and advice controllers.
- `configs`: Configuration files for Spring Security, database connections, etc.
- `controllers`: Handles incoming HTTP requests and defines API endpoints.
- `dtos`: Data Transfer Objects for transferring data between layers.
- `entities`: The core database entities (Driver, Rider, Ride, etc.).
- `exceptions`: Custom exceptions for handling errors.
- `repositories`: Interfaces for accessing the database using Spring Data JPA.
- `services`: Business logic implementation for various features like ride handling, fare calculation, etc.
- `strategies`: Contains algorithms for driver finding and fare calculation strategies.
- `utils`: Utility classes for common functionalities across the application.

