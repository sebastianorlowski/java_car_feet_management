# Car Fleet Management

Application to manage cars in company created with Spring Boot.
Here you can store your employees and cars in your company, connect cars with owners and
add receipt with fuel cost, then app will make raport about cars.
This application is dedicated to people which have several and more  cars in company.
For example. Uber, Food delivers, International transport etc.


### Technologies

* Spring Boot 2.4.5
* Vaadin 14.5.4
* mySQL 8.0
* Maven
* Hibernate

### Setup

To setup you need:
* mySQL database
* installed Maven
* IDE for example Intellij

~~~
1. git clone https://github.com/sebastianorlowski/java_car_fleet_management 

2. Go to application.properties and change datasource url.
    spring.datasource.url=jdbc:mysql://localhost:3306/carfuelcalculator?serverTimezone=Europe/Warsaw&useLegacyDatetimeCode=true
    spring.datasource.username={username}
    spring.datasource.password={password}
    
3. Start app and go to localhost:8080

4. Login: test
   Password: test123
   (You can change it in config/security/SecurityConfig)
~~~


### Features

~~~
* Car - /car - by this endpoint you can add new car with basic information about him
* Car list - / - main endpoint with list of cars in database
* Car edit - /car-edit - here you can find car by registration and edit him (for example registration)
* Car information - /car/info - if you put registration car then you will get information about car and owner (if exist)
* Fuel - /fuel - Firstly you add car registration and then you can add information about refueling(data, price, car kilometer status etc.)
* Owner - /owner/add - here you can add employee with basic informations in your company which will be owner one of cars 
* Owner list - /owner-list - you will get your employees list
* Owner edit - /owner-edit - firstly you need to find owner, you can find him by phone number/pesel/email then can edit and add car to owner
* Report about car - /report - here you can generate raport about car, every refueling and info in list, below you will see average about fuel etc.

~~~
  
