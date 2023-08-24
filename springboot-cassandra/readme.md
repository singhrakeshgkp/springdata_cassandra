# Project Setup
 - Create new spring boot project and add ``` spring data cassandra, spring web, lombok ``` dependency
 - Add docker file 
 - Add the required properties in application.prop file
 - Build the project. command to build the project form cmd ``` mvn clean install -DskipTests=true ```
 - Create docker image. ``` docker build -t <jar file name> .<currrent directory> ```
 - After that check if image is available ``` docker image ls ```
 - Pull latest cassandra image ``` docker pull cassandra:latest  ```
 - create docker network for spring boot application to communicate with cassandra database. 
 - Here is the command to create docker network ``` docker network create springboot-cassandra  ```
 - Run the image in the given network using below command
   ``` docker run --rm -d --name cassandra<existing container name> --hostname cassandra --network <network name> cassandra<container name/ image name> ```
 - Follow below steps to check if cassandra is running
   - copy cassandra container id. use ``` docker ps ``` command to check container id
   - command to connect to running container ``` docker exec -it 7e428ad186b4 bash ```  ```exit ``` command can be use to come out form cqlsh pag.
 - Use below command to run the application in same network where cassandra is running
   ```
   docker run --network=springboot-cassandra --name springboot-cassandra -p 7171:7171 -d springboot-cassandra.jar
   ```
 
