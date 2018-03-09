= ForexClientIT.java (Ex3.1)

There should be an Integration Test for deployed applications.
The project needs to be re-structured for proper Maven/Gradle use,
with src/main/java for the app and src/test/java for Unit and
Integration tests. Maven will run unit tests with "mvn test"
(which it does automatically as part of normal "mvn package");
it will only run Integration tests post-deploy with "mvn verify".

See some of the projects in the crs936 repository at
https://github.com/IanDarwin/CourseFiles936
for working projects.
