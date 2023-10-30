# email-api
REST APIs to create, retrieve and send emails.

Step 1 - Clone project from Github: git clone https://github.com/9000rpm/email-api <br>
Step 2 - Build and create docker image: .\mvnw spring-boot:build-image <br>
Step 3 - Run docker image: docker run -d -p 8080:8080 --name email docker.io/library/email:0.0.1-SNAPSHOT <br><br>
Step 4 - Make requests:<br>
GET		localhost:8080/v1/emails<br>
GET		localhost:8080/v1/emails/1<br>
POST	localhost:8080/v1/emails (Sample body: src/test/resources/SampleEmailMessageRequest.json)<br>
POST	localhost:8080/v1/emails/3/send<br>
PATCH	localhost:8080/v1/emails/2<br>
