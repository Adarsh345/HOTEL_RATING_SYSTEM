package com.lcwd.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "HOTELS")
public class Hotel {
    private String id;
    private String name;
    private String location;
    private String about;
}
