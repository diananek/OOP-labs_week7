# JFreeChart JAR build 


#### Maven
Run this command to build jar: 

```
mvn clean compile assembly:single
```

Run this to run jar:
__Windows__
```
java -jar target/lab7-1.0-SNAPSHOT-jar-with-dependencies.jar
```
__Linux__
```
java -jar ./target/lab7-1.0-SNAPSHOT-jar-with-dependencies.jar
```

#### Gradle
Run this command to build jar: 

```
.\gradlew clean build
```

Run this to run jar:
__Windows__
```
java -jar build/libs/example-1.0-SNAPSHOT.jar
```
__Linux__
```
java -jar ./build/libs/example-1.0-SNAPSHOT.jar
```
