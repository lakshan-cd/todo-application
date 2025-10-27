package com.lakshancd.todo_application_backend.common.constants;

public class ResponseConstants {

    /* Feature Names */
    public static final String TASK = "Task";
    public static final String STATUS = "Status";

    /**
     * Common
     **/
    //DATABASE_ERROR
    public static final String[] DATABASE_ERROR = {" Database error occurred unexpectedly(table PK FK violation.)", "3333"};

    /*
     * Error Handling Enhancement
     */
    public static class CommonMessages {
        public static final String[] CREATED = {"%s has been successfully created.", "9001"};
        public static final String[] FETCHED = {"%s has been successfully retrieved.", "9002"};
        public static final String[] UPDATED = {"%s has been successfully updated.", "9003"};
        public static final String[] DELETED = {"%s has been successfully deleted.", "9008"};
        public static final String[] PATCHED = {"%s has been successfully patched.", "9004"};
        public static final String[] NOT_UPDATED = {"%s is not updated.", "9093"};
        public static final String[] NOT_DELETED = {"%s is not deleted.", "8056"};
        public static final String[] NOT_FOUND = {"%s is not found.", "9095"};
        public static final String REQUIRED = "%s %s is required.";
    }

    public static class ErrorMessages {
        public static final String INVALID_LENGTH_TEMPLATE = "%s %s should have %d or more characters and cannot exceed %d characters.";
        public static final String INVALID_MIN_LENGTH_TEMPLATE = "%s %s should have %d or more characters.";
        public static final String INVALID_MAX_LENGTH_TEMPLATE = "%s %s cannot exceed %d characters.";
        public static final String FIELD_VALUE_INVALID = "%s field %s is invalid.";
        public static final String ALREADY_COMPLETED = "%s is already completed.";

    }


    /**
     * Error message methods
     */

    public static String getRequiredMessage(String featureName, String fieldName){
        return String.format(CommonMessages.REQUIRED, featureName, fieldName);
    }
    public static String getCreatedMessage(String featureName){
        return String.format(CommonMessages.CREATED[0], featureName);
    }
    public static String getDeleteMessage(String featureName){
        return String.format(CommonMessages.DELETED[0], featureName);
    }
    public static String getFetchedMessage(String featureName){
        return String.format(CommonMessages.FETCHED[0], featureName);
    }
    public static String getUpdatedMessage(String featureName){
        return String.format(CommonMessages.UPDATED[0], featureName);
    }
    public static String getNotUpdatedMessage(String featureName){
        return String.format(CommonMessages.NOT_UPDATED[0], featureName);
    }
    public static String getNotDeletedMessage(String featureName){
        return String.format(CommonMessages.NOT_DELETED[0], featureName);
    }
    public static String getNotFoundMessage(String featureName){
        return String.format(CommonMessages.NOT_FOUND[0], featureName);
    }
    public static String getMinMaxValidation(String featureName, String fieldName ,int min, int max){
        return String.format(ErrorMessages.INVALID_LENGTH_TEMPLATE, featureName, fieldName, min, max);
    }
    public static String getMinValidation(String featureName, String fieldName ,int min){
        return String.format(ErrorMessages.INVALID_MIN_LENGTH_TEMPLATE, featureName, fieldName, min);
    }
    public static String getMaxValidation(String featureName, String fieldName , int max){
        return String.format(ErrorMessages.INVALID_MAX_LENGTH_TEMPLATE, featureName, fieldName, max);
    }
    public static String getInvalidFieldMessage(String featureName, String fieldName) {
        return String.format(ErrorMessages.FIELD_VALUE_INVALID, featureName, fieldName);
    }
    public static String getAlreadyCompleted(String featureName){
        return String.format(ErrorMessages.ALREADY_COMPLETED, featureName);
    }

}