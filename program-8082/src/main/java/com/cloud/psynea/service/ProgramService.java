package com.cloud.psynea.service;

import com.cloud.psynea.entity.Program;

public interface ProgramService {

    Program addProgram(Program program);

    void updateProgramList(String programName, String userName);

    Program getProgram(String userName);
}
