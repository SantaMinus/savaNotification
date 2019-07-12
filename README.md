# Push Notification POC

Simple POC to test PWA push notifications

#### Requirements

 - Java 8
 - Gradle 4.4+
 
#### Building the project
 
`cd {project_folder}`

`gradle clean build`
 
#### Running the project

`java -jar build/libs/savaNotification-0.0.1-SNAPSHOT.jar`

#### HTTP notes

Since the server requires an **HTTPS** connection, the client needs to **import the certificate** into the browser.

**Download** the certificate: https://drive.google.com/open?id=15eqgdrI2eSNOXJqc7qqG993JFl07Gc5l

How to **import** a certificate into Chrome: https://support.globalsign.com/customer/portal/articles/1211541-install-client-digital-certificate---windows-using-chrome
###### Password: Capibar9