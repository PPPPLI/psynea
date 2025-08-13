package com.cloud.psynea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(value = "program")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {

    private ObjectId id;
    private String category;
    @Indexed(unique = true)
    private String userName;
    private List<String> subscribedProgram;
    private Map<String,Map<String,String>> modules;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private Map<String,String> initialQues;
    private Map<LocalDateTime,String> feedback;
    private Map<LocalDateTime,List<String>> history;
    private Map<LocalDateTime,String> appointment;
}
