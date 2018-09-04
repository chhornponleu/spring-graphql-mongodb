package com.ponleu.mongographql.graphql;


import com.google.common.graph.Graph;
import com.ponleu.mongographql.graphql.fetchers.AllArticlesDataFetcher;
import com.ponleu.mongographql.graphql.fetchers.AllUsersDataFetcher;
import com.ponleu.mongographql.graphql.fetchers.UserDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class GraphQLUtility {

    @Value("classpath:models.graphqls")
    private Resource schemaResource;

    @Autowired
    private AllUsersDataFetcher allUsersDataFetcher;
    @Autowired
    private UserDataFetcher userDataFetcher;
    @Autowired
    private AllArticlesDataFetcher allArticlesDataFetcher;

    @PostConstruct
    public GraphQL createGraphQLObject() throws IOException {
        File schema = schemaResource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        .dataFetcher("users", allUsersDataFetcher)
                        .dataFetcher("user", userDataFetcher)
                )
                .type("User", builder -> builder
                        .dataFetcher("articles", allArticlesDataFetcher)
                        .dataFetcher("friends", allUsersDataFetcher)
                )
                .build();
    }

    private GraphQL graphQL;

}
