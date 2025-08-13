package com.cloud.psynea.controller;

import com.cloud.psynea.dto.ProgramDTO;
import com.cloud.psynea.dto.QuestionDTO;
import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.entity.Program;
import com.cloud.psynea.mapper.ProgramMapper;
import com.cloud.psynea.mapper.QuestionMapper;
import com.cloud.psynea.service.ProgramService;
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
    ProgramService programService;

    @Resource
    QuestionMapper questionMapper;

    @Resource
    ProgramMapper programMapper;

    @GetMapping("/question/get/{index}")
    public ResponseDto<Object> getQuestion(@PathVariable("index") Integer index) {

        return testService.combineQuestionAndCount(index);
    }

    @PostMapping("/question/add")
    public ResponseDto<Object> addQuestion(@RequestBody QuestionDTO questionDTO){

        testService.saveQuestion(questionMapper.questionDTOToQuestion(questionDTO));

        return ResponseDto.builder().status(HttpStatus.ACCEPTED).data("Add successfully").build();
    }

    @PostMapping("/program/add")
    public ResponseDto<Object> addProgram(@RequestBody ProgramDTO programDTO){

        Program program = programService.addProgram(programMapper.programDTOToProgram(programDTO));

        return ResponseDto.builder().status(HttpStatus.ACCEPTED).data(programMapper.programToProgramDTO(program)).build();
    }

    @PostMapping("/program/update/program")
    public ResponseDto<Object> updateProgram(@RequestParam("programName")String programName, @RequestParam("userName")String userName){

        programService.updateProgramList(programName, userName);

        return ResponseDto.builder().status(HttpStatus.ACCEPTED).data(null).build();
    }

    @GetMapping("/program/get/{name}")
    public ResponseDto<Object> getProgram(@RequestBody @PathVariable("name") String userName){

        ProgramDTO programDTO = programMapper.programToProgramDTO(programService.getProgram(userName));

        return ResponseDto.builder().status(HttpStatus.ACCEPTED).data(programDTO).build();
    }
}
