package com.cloud.psynea.controller;

import com.cloud.psynea.dto.QuestionDTO;
import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.mapper.QuestionMapper;
import com.cloud.psynea.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;

    @Resource
    QuestionMapper questionMapper;

    @GetMapping("/question/get/{index}")
    public ResponseDto<Object> getQuestion(@PathVariable("index") Integer index) {

        return testService.combineQuestionAndCount(index);
    }

    @PostMapping("/question/add")
    public ResponseDto<Object> addQuestion(@RequestBody QuestionDTO questionDTO){

        testService.saveQuestion(questionMapper.questionDTOToQuestion(questionDTO));

        return ResponseDto.builder().status(HttpStatus.ACCEPTED).data("Add successfully").build();
    }
}
