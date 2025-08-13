package com.cloud.psynea.mapper;

import com.cloud.psynea.dto.QuestionDTO;
import com.cloud.psynea.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "type",source = "type")
    Question questionDTOToQuestion(QuestionDTO questionDTO);

    QuestionDTO questionToQuestionDTO(Question question);
}
