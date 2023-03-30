# Microservices authentication using Keycloak

Example project demonstrating authentication and authorization between two Springboot microservices using Keycloak. API requests are issued using OpenFeign clients.

Authentication filters are handled by Spring's OAuth 2.0 Resource Server.

Each service, and Keycloak and its database are deployed using docker-compose with a common network connecting them.
Keycloak database's data has also been added to the repository to streamline initial testing and running.

Client-server authentication is not handled since the purpose of this example is server-server authentication.

A third Java application provides E2E tests and tasks to build the docker images.
E2E create the containers and the network, wait for the servers to start up, run the tests, and remove the containers.
E2E tests can be run using the gradle task **e2e**. Prior to this, the services projects should be setup, and their images built