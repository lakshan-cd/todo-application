package com.lakshancd.todo_application_backend.common.constants;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        public static final String[] FROZE = {"%s has been successfully frozen.", "9006"};
        public static final String[] UPDATED = {"%s has been successfully updated.", "9003"};
        public static final String[] DELETED = {"%s has been successfully deleted.", "9008"};
        public static final String[] CONVERTED = {"%s has been successfully converted.", "9011"};


        public static final String[] PATCHED = {"%s has been successfully patched.", "9004"};
        public static final String[] NOT_CREATED = {"%s is not created.", "9091"};
        public static final String[] NOT_FETCHED = {"%s is not fetched.", "9092"};
        public static final String[] NOT_UPDATED = {"%s is not updated.", "9093"};
        public static final String[] NOT_DELETED = {"%s is not deleted.", "8056"};

        public static final String[] NOT_PATCHED = {"%s is not patched.", "9094"};
        public static final String[] NOT_FOUND = {"%s is not found.", "9095"};
        public static final String[] INVALID_DATE = {"%s date invalid.", "9099"};

        public static final String[] AUDITED = {"%s history has been successfully retrieved.", "9096"};

        public static final String NOT_VALID = "%s %s is not valid";
        public static final String REQUIRED = "%s %s is required.";
        public static final String[] FILE_UPLOADED = {"%s file has been successfully uploaded.", "9005"};
        public static final String[] FILE_NOT_UPLOADED = {"%s file is not uploaded.", "9097"};
        public static final String[] PLEASE_CREATE_FIELD = {"Please Create %s %s, to calculate %s.", "9098"} ;
        public static final String[] CANCELLED = {"%s has been successfully cancelled.", "9098"};
        public static final String[] NOT_APPROVED = {"%s has not been approved.", "9099"};
        public static final String[] ALREADY_APPROVED = {"%s has been already approved.", "9100"};
        public static final String[] CREATE_BEFORE = {"Please allocate %s, before assigning %s"};
        public static final String SETTLED = "%s has been settled successfully.";
        public static final String ALREADY_SETTLED = "%s already settled.";
        public static final String INVALID = "%s is invalid for the %s.";
        public static final String VOIDED = "Voided %s cannot be edited.";
        public static final String CANNOT_TRANSFER_TO_SAME_ACCOUNT = "Amount cannot be transferred between same account" ;
        public static final String NOT_ALLOCATED = "%s is not allocated for the %s";
        public static final String NO_DATA_TO_PROCESS = "No data to be processed";
        public static final String[] APPROVED = {"%s has been successfully approved.", "9101"};
        public static final String[] REJECTED = {"%s has been successfully rejected.", "9102"};
        public static final String BOUNCED = "%s has been successfully bounced.";
        public static final String CLEARED = "%s has been successfully cleared.";
    }

    public static class ErrorMessages {
        public static final String[] FIELD_EXISTS = {"%s %s already exists.", "9035"};
        public static final String[] FIELD_ARCHIVE = {"%s %s archived.", "9036"};
        public static final String[] FIELD_INACTIVE = {"%s %s inactive.", "9037"};
        public static final String[] INVALID_STATUS = {"%s %s status invalid.", "9037"};
        public static final String[] ALREADY_TERMINATED = {"%s already terminated.", "9038"};
        public static final String[] ALREADY_RESIGNED = {"%s already resigned.", "9039"};



        public static final String INVALID_LENGTH_TEMPLATE = "%s %s should have %d or more characters and cannot exceed %d characters.";
        public static final String INVALID_MIN_LENGTH_TEMPLATE = "%s %s should have %d or more characters.";
        public static final String INVALID_MAX_LENGTH_TEMPLATE = "%s %s cannot exceed %d characters.";
        public static final String SPECIAL_CHARACTERS_TEMPLATE = "%s %s should not be contain special characters.";
        public static final String ONLY_ONE_SPACE_ALLOWED = "%s %s can only contain one middle space.";
        public static final String CANNOT_UPDATE_ARCHIVED = "Cannot update archived %s.";
        public static final String CANNOT_UPDATE_INACTIVE = "Cannot update inactive %s.";
        public static final String CANNOT_DUPLICATED = "%s cannot be duplicated for %s.";
        public static final String CANNOT_OVERLAPPED = "%s cannot be overlapped for %s.";
        public static final String CANNOT_BE_GREATER_THAN = "%s cannot be greater than %s.";
        public static final String CANNOT_BE_EQUAL_TO = "%s cannot be equal to %s";
        public static final String MUST_BEFORE = "%s %s must be before the %s.";
        public static final String CANNOT_BE_ARCHIVED = "Archived %s cannot be assigned or mapped to %s.";
        public static final String CANNOT_BE_INACTIVE = "Inactive %s cannot be assigned or mapped to %s.";
        public static final String PRESENT_OR_FUTURE = "%s must be in the present or future";
        public static final String AT_LEAST_ONE_REQUIRED = "At least one %s is required.";
        public static final String CANNOT_SET_AFTER = "Cannot set %s after the %s.";
        public static final String CANNOT_DUPLICATED_FOR_SAME_DATE = "%s %s cannot be duplicate in same date.";
        public static final String ALLOW_ONLY_ALPHABET = "%s %s should not contain special characters or numbers.";
        public static final String ALLOW_ONLY_NUMERIC = "%s %s should be numeric.";
        public static final String INVALID_MIN_VALUE_TEMPLATE = "%s %s cannot be less than %s";
        public static final String INVALID_MAX_VALUE_TEMPLATE = "%s %s cannot exceed %s.";
        public static final String CANNOT_BE_ZERO_OR_MINUS = "%s %s cannot be 0 or a minus value.";
        public static final String VAT_NO_IS_NOT_VALID = "%s %s should have two letters in the front followed digits.";
        public static final String BUSINESS_ID_IS_NOT_VALID = "%s %s cannot have special characters other than '/' and '.'";
        public static final String CANNOT_UPDATE_ACTIVE_INACTIVE_WHEN_ARCHIVED = "Cannot update active/inactive status for archived %s";
        public static final String[] FIELD_NOT_EXISTS = {"%s %s not exists", "9036"};
        public static final String CAN_HAVE_ONE_MIDDLE_SPACE_AND_ALLOW_ALPHABET_ONLY = "%s %s must contain only one middle space and should have alphabetic character only.";
        public static final String INVALID_CONTENT_TYPE = "Invalid %s %s. %s";
        public static final String REPORT_GENERATION_FAILED = "%s report generation is failed.";
        public static final String AMOUNT_SHOULD_EQUAL_OR_LOWER = "%s amount should be equal or lower than requested amount.";
        public static final String LOAN_REQUEST_STATUS_SHOULD_BE_PENDING_FOR_REVIEW = "%s request can only be reviewed when their status is 'Pending.'";
        public static final String SHOULD_FOLLOW_CYCLE = "%s should follow the cycle.";
        public static final String CANNOT_PAY_LOAN_IF_LOAN_IS_NOT_APPROVED = "Cannot proceed with the payment. The loan with ID: %s has not been approved.";
        public static final String CANNOT_BE_COMPLETED_INACTIVE_ERROR = "%s cannot be marked as completed and inactive at the same time.";
        public static final String INVALID_DATE_FORMAT = "Invalid date format for %s. Expected format: YYYY-MM-DD.";
        public static final String INVALID_DATE_RANGE = "Invalid date range for start date should be before the end date.";
        public static final String DATES_NOT_IN_SPECIFIED_YEAR = "The provided dates must be in the year %s.";
        public static final String DATA_NOT_FOUND_FOR_GIVEN_DATE_RANGE = "%s data not found for the given date range.";
        public static final String NOTHING_TO_FREEZE = "No %s to freeze.";
        public static final String NO_ACTIVE_RECORD_FOUND = "No active %s found";
        public static final String ONLY_ALPHA_NUMERIC_WITH_DASH_ALLOWED = "%s: %s can contain only alphanumeric characters with '-'.";
        public static final String CANNOT_UPDATE_FROZEN = "Cannot update frozen %s.";
        public static final String CANNOT_CREATE_ATTENDANCE_FROZEN_DATE = "Cannot create attendance record for this effective date as attendance entries are frozen for the selected effective date";
        public static final String ALREADY_ASSIGNED = "%s is already assigned for this %s %s.";
        public static final String ALLOW_ONLY_POSITIVE_NUMBERS = "%s %s should contain only positive numbers.";
        public static final String EMPLOYEE_ALREADY_ASSIGNED_AS_DEPARTMENT_HEAD = "This employee is already assigned as a department head.";
        public static final String EMPLOYEE_NOT_IN_SAME_DEPARTMENT = "You cannot assign this employee as department head because they are not in the same department.";
        public static final String IS_INVALID_OR_EMPTY = "%s %s is invalid or empty.";
        public static final String CANNOT_UPDATE = "Cannot update %s.";
        public static final String CANNOT_CREATE_ALREADY_EXISTS = "Cannot create %s with the same %s already exists.";
        public static final String CANNOT_UPDATE_ALREADY_EXISTS = "Cannot update %s with the same %s already exists.";
        public static final String CANNOT_CREATE_ATTENDANCE_FOR_APPROVED_LEAVE_DATE = "You cannot add attendance for this employee, because they already have approved leave on date %s.";
        public static final String CANNOT_BE_NEGATIVE = "%s cannot be negative.";
        public static final String SELECT_AT_LEAST_ONE = "Please select at least one %s in %s.";
        public static final String ALLOW_ONLY_POSITIVE_NUMBERS_AND_ZERO_LIST = "All elements in %s should be positive numbers.";
        public static final String NO_ACTIVE_ENTITY_FOUND_FOR_GIVEN_ID = "No active %s found for the given id : %s.";
        public static final String ALLOW_ONLY_POSITIVE_NUMBERS_AND_ZERO = "%s %s should contain only positive number or zero.";
        public static final String ONLY_ALPHA_NUMERIC_WITHOUT_DASH_ALLOWED = "%s %s can contain only alphanumeric characters allowed.";
        public static final String ARCHIVED_RECORDS_FOUND = "Cannot update archived %s record";
        public static final String FIELD_VALUE_INVALID = "%s field %s is invalid.";

        public static final String ALREADY_CANCELED = "%s is already cancelled";
        public static final String ALREADY_COMPLETED = "%s is already completed.";

    }
    /**
     * Feature names
     */




    public static final String EVENT = "Event";

    /**
     * Error message methods
     */

    public static String getRequiredMessage(String featureName, String fieldName){
        return String.format(CommonMessages.REQUIRED, featureName, fieldName);
    }
    public static String getCreatedMessage(String featureName){
        return String.format(CommonMessages.CREATED[0], featureName);
    }
    public static String getFrozeMessage(String featureName){
        return String.format(CommonMessages.FROZE[0], featureName);
    }
    public static String getDeleteMessage(String featureName){
        return String.format(CommonMessages.DELETED[0], featureName);
    }
    public static String getConvertedMessage(String featureName){
        return String.format(CommonMessages.CONVERTED[0], featureName);
    }
    public static String getFetchedMessage(String featureName){
        return String.format(CommonMessages.FETCHED[0], featureName);
    }
    public static String getUpdatedMessage(String featureName){
        return String.format(CommonMessages.UPDATED[0], featureName);
    }
    public static String getPatchedMessage(String featureName){
        return String.format(CommonMessages.PATCHED[0], featureName);
    }
    public static String getNotCreatedMessage(String featureName){
        return String.format(CommonMessages.NOT_CREATED[0], featureName);
    }
    public static String getNotFetchedMessage(String featureName){
        return String.format(CommonMessages.NOT_FETCHED[0], featureName);
    }
    public static String getNotUpdatedMessage(String featureName){
        return String.format(CommonMessages.NOT_UPDATED[0], featureName);
    }
    public static String getNotDeletedMessage(String featureName){
        return String.format(CommonMessages.NOT_DELETED[0], featureName);
    }
    public static String getNotPatchedMessage(String featureName){
        return String.format(CommonMessages.NOT_PATCHED[0], featureName);
    }
    public static String getNotFoundMessage(String featureName){
        return String.format(CommonMessages.NOT_FOUND[0], featureName);
    }
    public static String getInvalidDateMessage(String featureName){
        return String.format(CommonMessages.INVALID_DATE[0], featureName);
    }
    public static String getFieldExists(String featureName, String fieldName){
        return String.format(ErrorMessages.FIELD_EXISTS[0], featureName, fieldName);
    }
    public static String getFieldInactive(String featureName, String fieldName) {
        return String.format(ErrorMessages.FIELD_INACTIVE[0], featureName, fieldName);
    }

    public static String getFieldArchive(String featureName, String fieldName){
        return String.format(ErrorMessages.FIELD_ARCHIVE[0], featureName, fieldName);
    }
    public static String getInvalidStatus(String featureName, String fieldName){
        return String.format(ErrorMessages.INVALID_STATUS[0], featureName, fieldName);
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
    public static String getSpecialCharactersValidation(String featureName, String fieldName) {
        return String.format(ErrorMessages.SPECIAL_CHARACTERS_TEMPLATE, featureName, fieldName);
    }
    public static String getOneSpaceAllowedMessage(String featureName, String fieldName){
        return String.format(ErrorMessages.ONLY_ONE_SPACE_ALLOWED, featureName, fieldName);
    }
    public static String getNotValidMessage(String featureName, String fieldName){
        return String.format(CommonMessages.NOT_VALID, featureName, fieldName);
    }
    public static String getCannotUpdateArchived(String featureName){
        return String.format(ErrorMessages.CANNOT_UPDATE_ARCHIVED, featureName);
    }
    public static String getCannotUpdateInActive(String featureName){
        return String.format(ErrorMessages.CANNOT_UPDATE_INACTIVE, featureName);
    }
    public static String getCannotDuplicateMessage(String fieldName01, String fieldName02){
        return String.format(ErrorMessages.CANNOT_DUPLICATED, fieldName01, fieldName02);
    }
    public static String getCannotBeGreaterThan(String minFieldValue, String maxFieldValue){
        return String.format(ErrorMessages.CANNOT_BE_GREATER_THAN, minFieldValue, maxFieldValue);
    }
    public static String getCannotBeEqualTo(String minFieldValue, String maxFieldValue){
        return String.format(ErrorMessages.CANNOT_BE_EQUAL_TO, minFieldValue, maxFieldValue);
    }
    public static String getMustBeforeMessage(String featureName, String startFieldValue, String endFieldValue){
        return String.format(ErrorMessages.MUST_BEFORE, featureName, startFieldValue, endFieldValue);
    }
    public static String getCannotBeArchived(String featureName, String fieldName){
        return String.format(ErrorMessages.CANNOT_BE_ARCHIVED, fieldName, featureName);
    }
    public static String getCannotBeInActive(String featureName, String fieldName){
        return String.format(ErrorMessages.CANNOT_BE_INACTIVE, fieldName, featureName);
    }
    public static String getNotExists(String featureName, String fieldName){
        return String.format(ErrorMessages.FIELD_NOT_EXISTS[0], featureName, fieldName);
    }
    public static String getPresentOrFutureMessage(String fieldName){
        return String.format(ErrorMessages.PRESENT_OR_FUTURE, fieldName);
    }
    public static String getAtLeastOneRequiredMessage(String fieldName) {
        return String.format(ErrorMessages.AT_LEAST_ONE_REQUIRED, fieldName);
    }
    public static String getCannotSetFromDateAfterToDate(String from, String to){
        return String.format(ErrorMessages.CANNOT_SET_AFTER, from, to);
    }
    public static String getCannotDuplicateSameDate(String featureName, String fieldName){
        return String.format(ErrorMessages.CANNOT_DUPLICATED_FOR_SAME_DATE, featureName, fieldName);
    }
    public static String getAllowAlphabetOnly(String featureName, String fieldName){
        return String.format(ErrorMessages.ALLOW_ONLY_ALPHABET, featureName, fieldName);
    }
    public static String getNumericMessage(String featureName, String fieldName) {
        return String.format(ErrorMessages.ALLOW_ONLY_NUMERIC, featureName, fieldName);
    }
    public static String getDecimalMinValidation(String featureName, String fieldName ,BigDecimal min){
        return String.format(ErrorMessages.INVALID_MIN_VALUE_TEMPLATE, featureName, fieldName, min);
    }
    public static String getDecimalMaxValidation(String featureName, String fieldName , BigDecimal max){
        return String.format(ErrorMessages.INVALID_MAX_VALUE_TEMPLATE, featureName, fieldName, max);
    }
    public static String getMinValueMessage(String featureName, String fieldName) {
        return String.format(ErrorMessages.CANNOT_BE_ZERO_OR_MINUS, featureName, fieldName);
    }
    public static String getVatIdErrorMessage(String featureName, String fieldName) {
        return String.format(ErrorMessages.VAT_NO_IS_NOT_VALID, featureName, fieldName);
    }
    public static String getBusinessIdErrorMessage(String featureName, String fieldName) {
        return String.format(ErrorMessages.BUSINESS_ID_IS_NOT_VALID, featureName, fieldName);
    }
    public static String getCannotUpdateActiveInactiveStatusWhenArchived(String featureName){
        return String.format(ErrorMessages.CANNOT_UPDATE_ACTIVE_INACTIVE_WHEN_ARCHIVED, featureName);
    }
    public static String getCannotHaveMiddleSpaceAndAllowAlphabetOnly(String featureName, String fieldName){
        return String.format(ErrorMessages.CAN_HAVE_ONE_MIDDLE_SPACE_AND_ALLOW_ALPHABET_ONLY, featureName, fieldName);
    }
    public static String getInvalidContentTypeMessage(String featureName, String fieldName, String contentType) {
        return String.format(ErrorMessages.INVALID_CONTENT_TYPE, featureName, fieldName, contentType);
    }
    public static String getReportNotCreated(String featureName) {
        return String.format(ErrorMessages.REPORT_GENERATION_FAILED, featureName);
    }
    public static String getAuditGetAllMessage(String featureName) {
        return String.format(CommonMessages.AUDITED[0], featureName);
    }
    public static String getFileUploadedMessage(String featureName){
        return String.format(CommonMessages.FILE_UPLOADED[0], featureName);
    }
    public static String getNotFileUploadedMessage(String featureName){
        return String.format(CommonMessages.FILE_NOT_UPLOADED[0], featureName);
    }
    public static String getPleaseCreateFieldMessage(String featureName, String fieldName, String actionName){
        return String.format(CommonMessages.PLEASE_CREATE_FIELD[0], fieldName, featureName, actionName);
    }

    public static String getAmountEqualOrLower(String featureName){
        return String.format(ErrorMessages.AMOUNT_SHOULD_EQUAL_OR_LOWER, featureName);
    }
    public static String getCanOnlyReviewWhenPending(String featureName){
        return String.format(ErrorMessages.LOAN_REQUEST_STATUS_SHOULD_BE_PENDING_FOR_REVIEW, featureName);
    }
    public static String getCancelled(String featureName){
        return String.format(CommonMessages.CANCELLED[0], featureName);
    }
    public static String getCannotPay(String loanId){
        return String.format(ErrorMessages.CANNOT_PAY_LOAN_IF_LOAN_IS_NOT_APPROVED, loanId);
    }
    public static String getSettle(String featureName){
        return String.format(CommonMessages.SETTLED, featureName);
    }
    public static String getAlreadyTerminatedMesssage(String featureName){
        return String.format(ErrorMessages.ALREADY_TERMINATED[0], featureName);
    }
    public static String getAlreadyResignedMesssage(String featureName){
        return String.format(ErrorMessages.ALREADY_RESIGNED[0], featureName);
    }

    public static String getNotApprovedMessage(String featureName){
        return String.format(CommonMessages.NOT_APPROVED[0], featureName);
    }
    public static String getCannotOverLapMessage(String fieldName01, String fieldName02){
        return String.format(ErrorMessages.CANNOT_OVERLAPPED, fieldName01, fieldName02);
    }
    public static String getShouldFollowCycle(String fieldName01) {
        return String.format(ErrorMessages.SHOULD_FOLLOW_CYCLE, fieldName01);
    }
    public static String getAlreadyApprovedMessage(String featureName){
        return String.format(CommonMessages.ALREADY_APPROVED[0], featureName);
    }
    public static String getCreateBeforeMessage(String featureName01, String featureName02){
        return String.format(CommonMessages.CREATE_BEFORE[0], featureName01, featureName02);
    }
    public static String getAlreadySettled(String featureName){
        return String.format(CommonMessages.ALREADY_SETTLED, featureName);
    }
    public static String getInvalid(String featureName01, String featureName02){
        return String.format(CommonMessages.INVALID, featureName01, featureName02);
    }
    public static String getVoidedMessage(String featureName) {
        return String.format(CommonMessages.VOIDED, featureName);
    }
    public static String getCannotTransferToSameAccount() {
        return String.format(CommonMessages.CANNOT_TRANSFER_TO_SAME_ACCOUNT);
    }
    public static String getNotAllocatedMessage(String featureName01, String featureName02) {
        return String.format(CommonMessages.NOT_ALLOCATED,featureName01,featureName02);
    }
    public static String getNotDataToProcessMessage() {
        return String.format(CommonMessages.NO_DATA_TO_PROCESS);
    }
    public static String getCannotBeCompletedInActivated(String featureName) {
        return String.format(ErrorMessages.CANNOT_BE_COMPLETED_INACTIVE_ERROR, featureName);
    }
    public static String getInvalidDateFormat(String fieldName) {
        return String.format(ErrorMessages.INVALID_DATE_FORMAT, fieldName);
    }
    public static String getEndDateBeforeStartDate() {
        return String.format(ErrorMessages.INVALID_DATE_RANGE);
    }
    public static String getDatesNotInSpecifiedYear(String year) {
        return String.format(ErrorMessages.DATES_NOT_IN_SPECIFIED_YEAR, year);
    }
    public static String getDataNotFoundForGivenDateRange(String featureName) {
        return String.format(ErrorMessages.DATA_NOT_FOUND_FOR_GIVEN_DATE_RANGE, featureName);
    }
    public static String getNothingToFreeze(String featureName) {
        return String.format(ErrorMessages.NOTHING_TO_FREEZE, featureName);
    }
    public static String getNotActiveRecordsFound(String featureName) {
        return String.format(ErrorMessages.NO_ACTIVE_RECORD_FOUND, featureName);
    }
    public static String getCannotUpdateFrozen(String featureName) {
        return String.format(ErrorMessages.CANNOT_UPDATE_FROZEN, featureName);
    }
    public static String getCannotCreateAttendanceFrozenDate() {
        return String.format(ErrorMessages.CANNOT_CREATE_ATTENDANCE_FROZEN_DATE);
    }
    public static String getAlreadyAssignedMessage(String featureName, String fieldName, String depName) {
        return String.format(ErrorMessages.ALREADY_ASSIGNED, featureName, fieldName, depName);
    }
    public static String getAllowPositiveNumbersOnly(String featureName, String fieldName){
        return String.format(ErrorMessages.ALLOW_ONLY_POSITIVE_NUMBERS, featureName, fieldName);
    }
    public static String getEmployeeAlreadyAssignedMessage() {
        return String.format(ErrorMessages.EMPLOYEE_ALREADY_ASSIGNED_AS_DEPARTMENT_HEAD);
    }
    public static String getEmployeeNotInSameDepartmentMessage() {
        return String.format(ErrorMessages.EMPLOYEE_NOT_IN_SAME_DEPARTMENT);
    }
    public static String getInvalidMessage(String featureName, String field) {
        return String.format(ErrorMessages.IS_INVALID_OR_EMPTY, featureName, field);
    }
    public static String getCannotUpdate(String featureName){
        return String.format(ErrorMessages.CANNOT_UPDATE, featureName);
    }
    public static String getCannotCreateAlreadyExists(String featureName, String fieldName) {
        return String.format(ErrorMessages.CANNOT_CREATE_ALREADY_EXISTS, featureName, fieldName);
    }
    public static String getCannotUpdateAlreadyExists(String featureName, String fieldName) {
        return String.format(ErrorMessages.CANNOT_CREATE_ALREADY_EXISTS, featureName, fieldName);
    }
    public static String getCannotCreateAttendanceForApprovedLeave(LocalDate date) {
        return String.format(ErrorMessages.CANNOT_CREATE_ATTENDANCE_FOR_APPROVED_LEAVE_DATE, date);
    }
    public static String getCannotBeNegative(String fieldName) {
        return String.format(ErrorMessages.CANNOT_BE_NEGATIVE, fieldName);
    }
    public static String getSelectAtLeastOne(String featureName, String fieldName) {
        return String.format(ErrorMessages.SELECT_AT_LEAST_ONE, fieldName, featureName);
    }
    public static String getAllElementsMustBePositive(String fieldName) {
        return String.format(ErrorMessages.ALLOW_ONLY_POSITIVE_NUMBERS_AND_ZERO_LIST, fieldName);
    }
    public static String getNoActiveFeatureFoundMessage(String featureName, String id) {
        return String.format(ErrorMessages.NO_ACTIVE_ENTITY_FOUND_FOR_GIVEN_ID, featureName, id);
    }
    public static String getAllowPositiveNumbersOrZero(String featureName, String fieldName){
        return String.format(ErrorMessages.ALLOW_ONLY_POSITIVE_NUMBERS_AND_ZERO, featureName, fieldName);
    }
    public static String getApprovedMessage(String featureName) {
        return String.format(CommonMessages.APPROVED[0], featureName);
    }
    public static String getBouncedMessage(String featureName) {
        return String.format(CommonMessages.BOUNCED, featureName);
    }
    public static String getClearedMessage(String featureName) {
        return String.format(CommonMessages.CLEARED, featureName);
    }
    public static String getCancelledMessage(String featureName) {
        return String.format(CommonMessages.CANCELLED[0], featureName);
    }
    public static String getAlphaNumericWithoutDashMessage(String featureName, String fieldName){
        return String.format(ErrorMessages.ONLY_ALPHA_NUMERIC_WITHOUT_DASH_ALLOWED, featureName, fieldName);
    }
    public static String getRejectedMessage(String featureName) {
        return String.format(CommonMessages.REJECTED[0], featureName);
    }
    public static String getArchivedRecordsFound(String featureName) {
        return String.format(ErrorMessages.ARCHIVED_RECORDS_FOUND, featureName);
    }

    public static String getAlreadyCancelledMessage(String featureName) {
        return String.format(ErrorMessages.ALREADY_CANCELED, featureName);
    }

    public static String getInvalidFieldMessage(String featureName, String fieldName) {
        return String.format(ErrorMessages.FIELD_VALUE_INVALID, featureName, fieldName);

    }
    public static String getAlreadyCompleted(String featureName){
        return String.format(ErrorMessages.ALREADY_COMPLETED, featureName);
    }

}