# Table of contents
- [Common info](#common-info)
- Installation
   - [Install in docker container](#install-in-docker-container)
   - [Install in macos](#install-in-mac-os)
- [Keyspace](#keyspace)
- [Operations](#operations)
   - [DDL](#ddl)
   - [DML](#dml)
- [Partitions](#partitions)
   - [Single Row Partitions](#single-row-partitions)
   - [Multi Row Partitions](#single-row-partitions)
- [Commands](#commands)

## Common Info
- Cassandra is master less(peer-to-peer architecture)
  ### References
  - https://www.youtube.com/watch?v=WFycRMLvMKc
  - 
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
   Note :- Here country is partition key.
  ```
  ```
  create table name(
   column1 type [static]
   column2 type [static]
  ...
  )
  primary key ((column,...), column,...)
  with clustering order by ( colustering-column[ASC|DESC], ...
  ));
  Note--> here first part (column,...) is partition key and column,... is clustering columns
  --> we use clustering column for two perpose. First to ensure enigquiness if partition key is not enough. Second To stablish sorting order.
  ```
### DML
- Insert statement
  ```
      INSERT INTO demo_keys.employee_by_country (country,email,first_name,last_name,age)
      VALUES('US', 'rakesh@email.com', 'rakesh','singh',22);
  ```
- Select statement
    ```
     select         selectors
     from           table name
     where          primary key condition(partition key or partition key + clustering key)
                    AND index conditions
     group by       primary key columns
     order by       cluster key column(ASC|DESC)
     limit          N
     allow filtering ;
    Note using ALLOW FILTERING is very bad pratice, it forces cassandra to go and perform full cluster scan to find the data.
    using allow filtering we can use data column instead of primary key column(partition column) in where clause.      
    ```
## Partitions
### Single Row Partitions
```
 create table employee(
   id UUID,
   name Text,
   primary key(id)
  )
```
- In above table(employee) id column is the partition key which is universally unique, that means we can have as much partition as employee. if u have millions of employee u will have millions of partitions.
### Multi Row Partitions
```
 create table employee(
   id UUID,
   name Text,
   city Text,
   joiningYear Text,
   salary Text,
   primary key((city,joiningYear),salary)
  )
```
- in this table we have defined composite partition key with column city and joiningYear. Employee with same city but different joining year will be allocated different partition. 

  
