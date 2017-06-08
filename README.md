# geocode
 
 This is a Spring Boot Web rest service that takes in address and uses google maps geocde api to getlat/long for the address.
 Camel http4 is used to make call to google maps api and xmljson module is used to convert the xml response to json.
 To call the service use following url
```
 curl http://localhost:8080/geocode/545+Mission+ST+San+Francisco+CA
```
 
 To run test cases use following  
 ```mvn test```
 
 To build and run the code use following 
  
  ```mvn install spring-boot:run```
 
 
 
