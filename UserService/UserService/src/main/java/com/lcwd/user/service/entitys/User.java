package com.lcwd.user.service.entitys;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "User")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String about;

 // this annotation is used to ingore this field to save in to the database
    @Transient
    private List<Rating> ratings ;

}
