package com.cassandratest.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration{

	//private final String localDataCenter;
	private final String host;
	private final String keySpaceName;
	//private final String port;
	private final String schemaAction;
	
	CassandraConfig(@Value("${spring.cassandra.keyspace-name}") String keySpaceName,
							 @Value("${spring.cassandra.contact-points}") String contactPoints,
							 @Value("$spring.cassandra.schema-action") String schemaAction){
		             
		this.host = contactPoints;
		//this.port = port;
		this.keySpaceName = keySpaceName;
		this.schemaAction = schemaAction;
	}
	
	@Override
	protected String getKeyspaceName() {

		return keySpaceName;
	}
	
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}


	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keySpaceName)
													.with(KeyspaceOption.DURABLE_WRITES,true)
													.ifNotExists();
		return Arrays.asList(specification);
	}
	
	@Override
	public String[] getEntityBasePackages() {
		return new String[] {"com.cassandratest.entity"};
	}


	@Override
	public String getContactPoints(){
		return host;
	}

}
