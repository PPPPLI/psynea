package com.cloud.psynea.utils;

import com.cloud.psynea.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class PrintResponseUtil {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void returnResponse(HttpServletResponse resp, ResponseDto<?> data) {


        try(Writer writer = resp.getWriter()) {

            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");

            String dataToJson = OBJECT_MAPPER.writeValueAsString(data);
            writer.append(dataToJson);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
