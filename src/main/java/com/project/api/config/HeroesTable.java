package com.project.api.config;

import java.util.Arrays;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.project.api.constants.HeroesConstant;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {

	public static void main(String[] args) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(HeroesConstant.ENDPOINT_DYNAMO, HeroesConstant.REGION_DYNAMO)).build();

		DynamoDB dynamoDB = new DynamoDB(client);

		String tableName = "Heroes_Api_Table";

		try {
			Table table = dynamoDB.createTable(tableName, Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
					Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)), new ProvisionedThroughput(5L, 5L));
			
			table.waitForActive();
			System.out.println("Sucesso " + table.getDescription().getTableStatus());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
