package fr.vat.amapg.amapg.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.svenkubiak.embeddedmongodb.EmbeddedMongoDB;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.bson.UuidRepresentation.STANDARD;

@TestConfiguration
public class MongoTestConfiguration {

    private static final Integer port = 27019;
    private static final String host = "localhost";
    private static final String database = "test";

    @Bean(destroyMethod = "stop")
    public EmbeddedMongoDB embeddedMongoDB() {
        return EmbeddedMongoDB.create()
                .withHost(host)
                .withPort(port)
                .withVersion(Version.Main.V8_0)
                .start();
    }

    @Bean
    @DependsOn("embeddedMongoDB")
    MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://" + host + ":" + port);

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .uuidRepresentation(STANDARD)
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    @DependsOn("mongoClient")
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, database);
    }

}
