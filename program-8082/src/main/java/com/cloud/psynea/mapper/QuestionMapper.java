package com.cloud.psynea.mapper;

import com.cloud.psynea.dto.QuestionDTO;
import com.cloud.psynea.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question questionDTOToQuestion(QuestionDTO questionDTO);

    QuestionDTO questionToQuestionDTO(Question question);
}
