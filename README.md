# User Contacts

# Setup Instruction
You can run the project using docker-compose.yml via Docker.

### Requirements
* Installed Docker on your machine

### Running from docker-compose
1. `git clone https://github.com/genadigeno/contact-book.git contact-book`
2. `cd contact-book`
3. `mvn package`
4. `docker compose up`

### Base URL
- http://localhost:9898

Contact CRUD Operations

| Title             | URL                                            | Required RequestBody | Authorization Required |
|-------------------|------------------------------------------------|----------------------|------------------------|
| get all books     | GET /api/contacts?page=<int>&size=<int>        | No                   | Yes                    |
| get contact by id | GET /api/contacts/{contactId}                  | No                   | Yes                    |
| create contact    | PUT /api/contacts                              | Yes                  | Yes                    | 
| update contact    | POST /api/contacts                             | Yes                  | Yes                    |
| delete contact    | DELETE /api/contacts                           | No                   | Yes                    | 
| search contacts   | GET /api/contacts/search?page=<int>&size=<int> | Optional             | Yes                    |

Authorization Operations

| Title           | URL                     | Required RequestBody | Authorization Required |
|-----------------|-------------------------|----------------------|------------------------|
| register a user | POST /api/auth/register | Yes                  | NO                     |
| lorin a user    | POST /api/auth/login    | Yes                  | NO                     |

Used Technologies:
* Java 17
* Spring Boot 3.x
* PostgreSQL
* Lombok
* FlyWay
* MapStruct
* Docker
* Git
