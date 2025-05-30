package com.cloud.psynea.service;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.entity.Question;

public interface TestService {

    Question getQuestionByIndex(Integer index);

    Long getQuestionCount();

    ResponseDto<Object> combineQuestionAndCount(Integer index);

    void saveQuestion(Question question);
}
