package com.cloud.psynea.service.impl;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.entity.Question;
import com.cloud.psynea.repository.TestRepository;
import com.cloud.psynea.service.TestService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Resource
    private TestRepository testRepository;

    @Override
    public Question getQuestionByIndex(Integer index) {

        return testRepository.findQuestionByIndex(index);

    }

    @Override
    public Long getQuestionCount() {

        return testRepository.count();
    }

    @Override
    public ResponseDto<Object> combineQuestionAndCount(Integer index) {

        Question question = getQuestionByIndex(index);
        Long count = getQuestionCount();

        return ResponseDto.builder().status(HttpStatus.ACCEPTED).data(List.of(question,count)).build();
    }

    @Override
    public void saveQuestion(Question question) {

        testRepository.save(question);
    }
}
