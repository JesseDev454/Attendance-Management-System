# TAMS Test Suite

This directory contains automated unit tests for the Time & Attendance Management System (TAMS).

## Test Structure

```
test/
└── java/
    └── com/
        └── nileuniversity/
            └── tams/
                └── service/
                    ├── AuthenticationServiceTest.java    (10 tests)
                    ├── AttendanceServiceTest.java        (10 tests)
                    ├── ReportServiceTest.java            (8 tests)
                    └── UserManagementTest.java           (8 tests)
```

## Running Tests

### Run all tests:
```bash
./gradlew test
```

### Run specific test class:
```bash
./gradlew test --tests AuthenticationServiceTest
./gradlew test --tests AttendanceServiceTest
./gradlew test --tests ReportServiceTest
./gradlew test --tests UserManagementTest
```

### Run with detailed output:
```bash
./gradlew test --info
```

## Test Coverage

| Test Suite | Tests | Coverage |
|------------|-------|----------|
| AuthenticationServiceTest | 10 | Login, logout, role access, credential validation |
| AttendanceServiceTest | 10 | Check-in, check-out, records, status tracking |
| ReportServiceTest | 8 | Report generation, statistics, formats |
| UserManagementTest | 8 | User creation, admin operations |
| **TOTAL** | **36** | **All service layer methods** |

## Test Reports

After running tests, view the HTML report at:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

## Test Guidelines

1. **Isolation**: Each test is independent and doesn't affect others
2. **Setup**: `@Before` methods ensure clean state
3. **Naming**: Tests follow `testMethodName_Scenario_ExpectedResult` pattern
4. **Assertions**: Clear assertion messages for debugging
5. **Documentation**: Each test has JavaDoc explaining its purpose

## Notes

- Tests focus on service layer logic (no UI tests)
- All tests use JUnit 4 framework
- Services use singleton pattern - properly reset in setUp()
- Test data is self-contained and doesn't require external resources

## Last Test Execution

- **Date**: December 31, 2025
- **Result**: ✅ All 36 tests PASSED
- **Duration**: ~50 seconds
- **Pass Rate**: 100%
