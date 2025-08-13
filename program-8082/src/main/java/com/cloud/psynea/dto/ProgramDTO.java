package com.cloud.psynea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramDTO {

    private String category;
    private String userName;
    private Map<String,Map<String,String>> modules;
    private List<String> subscribedProgram;
    private LocalDateTime modificationDate;
    private Map<String,String> initialQues;
    private Map<LocalDateTime,String> feedback;
    private Map<LocalDateTime,List<String>> history;
    private Map<LocalDateTime,String> appointment;
}
