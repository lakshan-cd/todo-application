package com.lakshancd.todo_application_backend.dto.task;

import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import com.lakshancd.todo_application_backend.common.validators.notnullwithmessage.NotNullWithMessage;
import com.lakshancd.todo_application_backend.common.validators.sizeminmax.SizeMinMax;
import lombok.Data;

@Data
public class CreateUpdateTaskDTO {
    @NotNullWithMessage(featureName = ResponseConstants.TASK, fieldName = "Title")
    @SizeMinMax(featureName = ResponseConstants.TASK, fieldName = "Title", min = 3, max = 100)
    private String title;

    @SizeMinMax(featureName = ResponseConstants.TASK, fieldName = "Description", max = 255)
    private String description;
}
