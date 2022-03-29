# 8x8 Test

## How to build

- Pull down the code
- <b>Run <em>docker-compose up</em> in your terminal and that's it</b> (it will take a little while the first time)

## How to use

- The Fibonacci service provides <b>API documentation</b>:
    - http://localhost:8080/swagger-ui.html
- Use your favorite API platform (e.g. Postman) to send queries to the service

## Used:

- Java and SpringBoot for building the application backend
- H2 database for the storage layer
- OpenAPI for API documentation
- Docker for containerization

## Notes:

- For the single number request, I've opted for using a limit provided by the user for the segment, and providing a number randomly from that segment