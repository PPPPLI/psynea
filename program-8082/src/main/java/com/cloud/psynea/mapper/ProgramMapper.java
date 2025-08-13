package com.cloud.psynea.mapper;

import com.cloud.psynea.dto.ProgramDTO;
import com.cloud.psynea.entity.Program;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    Program programDTOToProgram(ProgramDTO programDTO);

    ProgramDTO programToProgramDTO(Program program);
}
