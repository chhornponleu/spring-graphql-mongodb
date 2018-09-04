package com.ponleu.mongographql.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "users")
public class User {
    private ObjectId id;
    private String name;
    private Integer age;

    private Date createdAt;
    private String nationality;
    private List<String> friendsIds;
    private List<String> articlesIds;
}
