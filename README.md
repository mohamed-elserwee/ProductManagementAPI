This project is a simple Java SE application that provides a lightweight RESTful API to manage products.
It uses Java's built-in HttpServer to expose the following endpoints:

GET /products – Retrieve a list of all available products

GET /products/{id} – Fetch details of a specific product by its ID

The application follows a layered architecture:

model – Domain entity (Product)

dao – Simulated database access layer

service – Business logic layer

api – REST endpoint handler
