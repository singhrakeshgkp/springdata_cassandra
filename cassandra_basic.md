# Table of contents
- Installation
   - [Install in docker container](#install-in-docker-container)
   - [Install in macos](#install-in-mac-os)
- [Keyspace](#keyspace)
- [Operations](#operations)
   - [DDL](#ddl)
   - [DML](#dml)
- [Commands](#commands)
## Install in docker container
- ```docker pull cassandra:3.11```
- ```docker run -p 9042:9042 --rm --name cassandra -d cassandra:3.11```
- ```docker run --rm -d --name cassandra --hostname cassandra cassandra```
- ```docker exec -it cassandra bash -c "cqlsh -u cassandra -p cassandra"```

## Install in mac os
- Run following commands to install and start cassandra
   -  ```brew install cassandra```
   -  ```cassandra -f``` to start cassandra
   -  ```cqlsh``` type in new terminal
## Keyspace
- Keyspaces contain tables, materialized views and user-defined types, functions and aggregates fore more visit datastack official website
- commands
   -  this command ```describe keyspaces``` is used to list down the available key spaces
   -  Create Keyspace
     ```
      CREATE KEYSPACE demo_keys
     WITH REPLICATION = { 
     'class' : 'NetworkTopologyStrategy',
     'datacenter1' : 1 <replication factor>
     };
     ```
## Operations
### DDL
- Create Table
  ```
   CREATE TABLE demo_keys.employee_by_country (
    country text,
    email text,
    first_name text,
    last_name text,
    age smallint,
    PRIMARY KEY ((country), email)
   );
   Note :- create table <name space>.<table name>, here country is partition key
  ```
### DML
- Insert statement
  ```
      INSERT INTO demo_keys.employee_by_country (country,email,first_name,last_name,age)
      VALUES('US', 'rakesh@email.com', 'rakesh','singh',22);
  ```


  
