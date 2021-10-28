package io.ollivander.ollivanderbackend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.model.dto.ResponseObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class HttpPrintErrorHelper {

    private HttpPrintErrorHelper() {
    }

    public static void printError(HttpServletResponse httpResponse, ErrorInfo error, int code) throws IOException {
        printError(httpResponse, error, code, null);
    }

    public static void printError(HttpServletResponse httpResponse, ErrorInfo error, int code, Object data) throws IOException {

        ResponseObject<Object> responseObj = new ResponseObject<Object>();
        responseObj.setError(error);
        if (data != null) {
            responseObj.setResponseData(data);
        }

        httpResponse.setStatus(code);
        httpResponse.addHeader("Content-Type", "application/json");
        ServletOutputStream sos = httpResponse.getOutputStream();
        sos.print(new ObjectMapper().writeValueAsString(responseObj));
        sos.close();
    }
}
