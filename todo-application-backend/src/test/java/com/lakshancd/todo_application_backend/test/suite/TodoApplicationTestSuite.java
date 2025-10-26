package com.lakshancd.todo_application_backend.test.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Comprehensive Test Suite for Todo Application Backend
 * 
 * This test suite includes:
 * - Unit Tests for Service Layer
 * - Unit Tests for Helper Classes
 * - Integration Tests with Database
 * - Repository Tests
 * - Controller Tests
 * 
 * Test Coverage Areas:
 * - TaskService implementation
 * - TaskServiceHelper utilities
 * - Database operations
 * - Business logic validation
 * - Error handling
 * - Pagination functionality
 * - Status management
 * 
 * To run all tests: mvn test
 * To run with coverage: mvn test jacoco:report
 * To run specific test categories: mvn test -Dtest="*UnitTest" or mvn test -Dtest="*IntegrationTest"
 */
@Suite
@SuiteDisplayName("Todo Application Backend - Complete Test Suite")
@SelectPackages({
    "com.lakshancd.todo_application_backend.service.task.impl",
    "com.lakshancd.todo_application_backend.service.task.helper",
    "com.lakshancd.todo_application_backend.service.task.integration",
    "com.lakshancd.todo_application_backend.repository",
    "com.lakshancd.todo_application_backend.controller"
})
public class TodoApplicationTestSuite {
}
