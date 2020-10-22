# Roadmap Application

Roadmap Demo application exposes Spring Rest Services using Spring Boot. 
It determines whether two cities are connected or not based on the input file provided.

#Requirement
Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.
List of roads is available in a input file. The file contains a list of city
pairs (one pair per line, comma separated), which indicates that there’s a
road between those cities.

It will be deployed as a Spring Boot App and expose one endpoint:
http://localhost:8080/connected?origin=city1&destination=city2
Above Rest end point  respond with 
1. ‘yes’ if city1 is connected to city2,
2. ’no’  if city1 is not connected to city2.
3. Any unexpected input should result in a ’no’ response.

#Assumption
1   In input file Each row will have TWO cities name separated by COMMA(,)
2.  In input file if any line has more than one comma will be skipped. Particular rocord/row will be is ignored


# Tested environment

Java 1.8
apache-maven-3.6.3
Spring Boot ::  (v2.3.4.RELEASE)
Eclipse
Windows

# Deploy  and laynch Application

To install and launch the app, open a terminal and do the following:

1. Change directory to Project Directoty where you want to insatall.
2. clone the project from github using following command
   git clone https://github.com/AArokyaswamy/RoadmapDemo.git
3. Change directory to project dir  i.e., \RoadmapDemo
4. Launch the application using below maven command  (or RUN as Spring boot application inside IDE Eclipse).
     ..\RoadmapDemo>mvn spring-boot:run
5. Exposed following Rest API end point  (Refer to below Note Section also)
   http://localhost:8080/connected?origin=Philadelphia&destination=Newark 
6. Run JUnit test cases using below command. Test class implementation 'RoadmapApplicationTests'
   .\RoadmapDemo>mvn clean install
#Note : 
   1. Value of Query parameters for 'origin' and 'destination' in above Rest API End point can be changed to validate against input file 'city.txt'
   2. In application.properties (In project folder under src/main/reorigins/ directory) has property 'inputfile.path='
      To read dyanamic file from any path , change this property and keep the file under specified drectoty
	  ***********IMPORTANT ********** 
	  Example :          inputfile.path=c:/input/city.txt  
      If this property is EMPTY ,  it uses 'city.txt' under project folder as DEFAULT file 

# Test cases (Few examples)   

Default city.txt content is:

Boston, New York

Philadelphia, Newark

Newark, Boston

Trenton, Albany

http://localhost:8080/connected?origin=Boston&destination=New York     

Should response 'yes'

http://localhost:8080/connected?origin=Boston&destination=Philadelphia  

Should response 'yes'

http://localhost:8080/connected?origin=Philadelphia&destination=Albany  

Should response  'no'


#Note : Junit test cases mentioned above step 6


#Design

Here every city is identified with unique node. 
As the input file is read Adjacent cities/nodes are identified and mainted in Hashmap . 
It will be validated against source and desination user passed.

# Implemenation details
   RoadmapApplication (Main Spring boot Application class)
   RoadmapController (Rest Controller class)
   RoadmapServiceImpl (Solution implemenation class)
   GlobalExceptionHandler (For Exception handling)

#Improvements

More test cases needs to added and Readme.md can be added with more details.



