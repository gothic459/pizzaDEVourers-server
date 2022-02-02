# pizzaDEVourers - server
> Backend for pizzaDEVourers project.
> Live demo [coming soon]

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Endpoints](#endpoints)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements)
* [Contact](#contact)

## General Information
- pizzaDEVourers is a client-server style application. 
- Frontend can be found [_here_](https://github.com/gothic459/online-restaurant)
- It was build in order to encounter real-life programming challanges and solve them - just for fun and hobby.


## Technologies Used
- Java - version 11.0
- Spring Boot - version 2.6.3
- MongoDB
- REST API
- JWT (JSON Web Token)


## Endpoints

#### GET /menu
Returns array of menu items, including meals and drinks.  

#### POST /login
Used to login into server.  

Example request:
```json
{
    "username": "dariuszczajka",
    "password": "thatISanAWFULp@$$w0rd"
}
```
     
#### POST /register
User to register a first-time user into a database of clients.  

Example request:
```json
{
    "username": "dariuszczajka",
    "password": "thatISanAWFULp@$$w0rd",
    "first_name": "Dariusz",
    "last_name": "Czajka",
    "address": "ul. Nowa 1, 33-100 Tarnow",
    "telephone": "555222111"
}
```

#### POST /sendOrder
Used to send an order to process in restaurant. Returns an UUID of order. 

Example request:
```json
{
    "userJWT": "eyJraWQiOiI2MWY2YjdlMTYzOGQ4NzMwMjRiZDRmNjciLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTY0Mzc0MzIyMH0.sFfafw6q95oxOrhDVQf6g7NUQdhngEX9Wu4x56FM3gw",
    "productsList": [
        {
            "productId": "61f910db551c0dc22d4acdfe",
            "amount": 1
        },
        {
            "productId": "61f910db551c0dc22d4ace0a",
            "amount": 2
        }
    ]
}
```

## Setup

Required dependencies:
- spring-boot-starter-web
- spring-boot-starter-data-mongodb
- spring-boot-starter-web-services
- spring-boot-starter-test 
- spring-security-core
- java-jwt
- itextpdf
 
Due to security reasons, application.properties isn't provided within the repository. So, in order to run the project, you must create it yourself. 

#### I don't have it just yet
Don't worry - this [_guide_](https://www.mongodb.com/basics/create-database) got you covered. 

#### I got my own mongoDB database
Great! All you need to do is create a new file within the "resources" catalog. Copy this 

```
spring.data.mongodb.database={{YOUR_DATABASE}}
spring.data.mongodb.uri={{YOUR_URI}}
```
into your file (change the credentials - OBVIOUSLY), and save it.


🎉🎉 Congratulations! It should work as expected. 🎉🎉 


## Project Status
Project is: _in progress_ 


## Room for Improvement

To do:
- admin console,
- admin stats for sales, using data collected in the database

Ideas:
- own fork of frontend

## Contact
Created by Dariusz Czajka [LinkedIn](https://www.linkedin.com/in/dariuszczajka/) [E-mail](mailto:dczajka@tuta.io) - feel free to contact me!
