package com.lakshancd.todo_application_backend.entity.logs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogDataEntity {
    /**
     * The data entity containing the payload or information related to the log.
     */
    private DataEntity data;

    /**
     * The HTTP method used in the request (e.g., GET, POST).
     */
    private String method;

    /**
     * The request URI or endpoint of the API.
     */
    private String requestURI;

    /**
     * The masked request body to ensure sensitive information is not exposed in logs.
     */
    private String maskedRequestBody;

    /**
     * The HTTP response code returned by the API.
     */
    private int responseCode;

    /**
     * The masked response body to ensure sensitive information is not exposed in logs.
     */
    private String maskedResponseBody;

    /**
     * The time taken for processing the request and generating the response, in milliseconds.
     */
    private long timeTaken;

    /**
     * The name of the machine or server where the log data is generated.
     */
    private String machineName;
}
