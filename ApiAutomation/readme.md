# PetStore Api Automation

This project contains automated test scenarios for the PetStore API. The project is developed using REST Assured and JUnit5.

## Project Structure
- **src/test/java**: Test code.
- **pom.xml**: Maven configuration file.

## Setup
### Requirements
- Java 8 or higher
- Maven 3.6.0 or higher

## Project Content

- testAddPet: Adds a new pet to the store. This test verifies that a pet can be successfully added with the correct attributes.

- testAddInvalidPet: Attempts to add an invalid pet with incorrect JSON. This test ensures that the system correctly handles invalid input by returning a 400 Bad Request status.

- testGetPetById: Retrieves a pet by its ID. This test checks that a previously added pet can be fetched using its unique ID, and the attributes match as expected.

- testGetPetFail: Attempts to retrieve a non-existent pet. This test ensures that the system returns a 404 Not Found status when a pet with a given ID does not exist.

- testUpdatePet: Updates the status of an existing pet. This test verifies that the status of a pet can be successfully updated.

- testUpdatePetFail: Attempts to update a pet with invalid data. This test ensures that the system returns a 400 Bad Request status when trying to update with incorrect JSON.

- testDeletePet: Deletes a pet by its ID. This test checks that a pet can be successfully deleted from the store.

- testDeletePetFail: Attempts to delete a non-existent pet. This test ensures that the system returns a 404 Not Found status when trying to delete a pet that does not exist.