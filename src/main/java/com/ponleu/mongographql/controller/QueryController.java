package com.ponleu.mongographql.controller;

import com.ponleu.mongographql.graphql.GraphQLUtility;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QueryController {

    private GraphQL graphQL;

    @Autowired
    public QueryController(GraphQLUtility graphQLUtility) throws IOException {
        this.graphQL = graphQLUtility.createGraphQLObject();
    }

    @PostMapping("/query")
    public ResponseEntity<?> query(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        System.out.println("Errors: " + result.getErrors());
        if(result.getErrors() == null || !result.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getErrors().toString());
        }
        return ResponseEntity.ok(result.getData());
    }
}
