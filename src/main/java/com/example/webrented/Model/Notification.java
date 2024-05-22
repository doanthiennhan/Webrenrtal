package com.example.webrented.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@EntityScan
@Document(collection = "Message")
public class Notification {

}
