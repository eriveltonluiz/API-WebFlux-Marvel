package com.project.api.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.project.api.constants.HeroesConstant;

@Configuration
@EnableDynamoDBRepositories
public class HeroesData {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(HeroesConstant.ENDPOINT_DYNAMO,
						HeroesConstant.REGION_DYNAMO))
				.build();

		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("Heroes_Api_Table");

		Item hero = new Item().withPrimaryKey("id", "1").withPrimaryKey("name", "Mulher Maravilha")
				.withPrimaryKey("universe", "dc comics").withPrimaryKey("moovies", 3);
		
		Item hero1 = new Item().withPrimaryKey("id", "3").withPrimaryKey("name", "Homem-Aranha")
				.withPrimaryKey("universe", "marvel").withPrimaryKey("moovies", 4);

		PutItemOutcome outcome = table.putItem(hero);
		PutItemOutcome outcome1 = table.putItem(hero1);
	}
}
