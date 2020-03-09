# SellersJsonProcessing Library
#### Introduction:

Simple Java based library to process sellers.json. The leates verison is 1.4

#### Environment Requirement:

At least JDK11, Maven 3.6.0+

#### Usage：
Add Maven dependency to pom.xml:
```xml
<dependency>
   <groupId>com.github.megathrone</groupId>
   <artifactId>SellersJsonProcessing</artifactId>
   <version>1.5</version>
</dependency
```
Add Gradle Groovy DSL to build.gradle:
```groovy
implementation 'com.github.megathrone:SellersJsonProcessing:1.5'
```
API instructions:

```java
// Get instance of SellersProcessor
SellersProcessor processor = new SellersProcessor();

// load sellers.json data from given url
String rawData = processor.loadJsonStringFromUrl(yourUrl);

// Using pre-defined Sellers object
Sellers sellers = processor.loadJsonObjectFromUrl(yourUrl);

// List domains from JSON object
List<String> domains = processor.listDomainsFromObject(sellers);

// List domains from sellers.json file
Reader reader = Files.newBufferedReader(Paths.get(pathToYourSellersJsonFile));
List<String> domains = processor.listDomainsFromFile(reader);

// List common sellers from two sellers.json files
Reader reader1 = Files.newBufferedReader(Paths.get(pathToYourSellersJsonFile));
Reader reader2 = Files.newBufferedReader(Paths.get(pathToYourSellersJsonFile));
List<Seller> commonSellers = processor.lostCommonSellers(reader1, reader2);
```

#### Design documentations:

* JDK 11 instead of JDK 8
  * JDK 11 is the long term support version which means it is stable and better.
  * At the beginning I was thinking using Netty to compose Http requests that loads json url. But in order to make library as small size as possible I figured out that using JDK 11 HttpClient might be better. JDK 11 native HttpClient has better performance than before.

* Dealing with json file
  * Using Gson library because it is light weighted and it has better perfomance than Jackson.
  * The library could return both raw sellers.json string and pre-defined Java object.
* Pre defined Sellers object
  * The sellers object was designed based on the latest version of [IAB Tech Lab specification](https://iabtechlab.com/wp-content/uploads/2019/07/Sellers.json_Final.pdf).
  * Sellers object composes Identifier class and Seller class. All of them are implemented with toString, hashcode and equals methods.
* Processor structure
  * The processor has two components that are json parser and json loader. All of them implemented one interface that defined methods.
  * Parser uses Gson library to serialize and deserialize json file.
  * Loader uses JDK11 native HttpClient to access to json url
* Consumer Application:
  * I decided to use SpringBoot(Spring/SpringMVC) because both library and client application are running on JVM platform, it is less difficult to setup and use.
  * The user interface used Thymeleaf, a good template engine that supprted by Spring commuity, really easy for communication between frontend and backend.