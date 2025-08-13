package com.cloud.psynea.service.impl;

import com.cloud.psynea.entity.Program;
import com.cloud.psynea.messaging.producer.KafkaMQ;
import com.cloud.psynea.repository.ProgramRepository;
import com.cloud.psynea.service.ProgramService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Resource
    private ProgramRepository programRepository;

    @Resource
    private KafkaMQ kafkaMQ;

    @Override
    public Program addProgram(Program program) {

        program.setModificationDate(LocalDateTime.now());
        program.setCreationDate(LocalDateTime.now());

        Program res =  programRepository.save(program);

        kafkaMQ.updateInitialStatus(program.getUserName());

        return res;
    }

    @Override
    public void updateProgramList(String programName, String userName) {

        Program program = programRepository.findProgramByUserName(userName);

        program.setModificationDate(LocalDateTime.now());

        List<String> list = program.getSubscribedProgram();

        if(list == null){

            list = new ArrayList<>();
            list.add(programName);
        }else{

            list.add(programName);
        }

        program.setSubscribedProgram(list);

        programRepository.save(program);

    }

    @Override
    public Program getProgram(String userName) {

        Program program = programRepository.findProgramByUserName(userName);

        return programRepository.findProgramByUserName(userName);
    }
}
