# WebDriverIO Mobile Automation Project

## Description

A mobile automation framework designed to validate key functionalities of the WebDriverIO app. This framework evaluates app behavior under various conditions and ensures consistent user experience by mimicking real-world scenarios.

## Disclaimers

1. **Explicit Waits**
    - The framework uses explicit waits to ensure elements are visible or interactable before performing any actions.

2. **Data-Driven Testing**
    - Implements **Data Providers** to execute the Signup tests with unique, randomly generated credentials on each run.
    - The framework uses the **Faker library** to generate emails and passwords dynamically for realistic testing scenarios.

3. **Execution Flow**
    - The test sequence ensures comprehensive validation of critical functionalities:
        - **Login Test**: Tests email and password combinations and validates that the success message in the popup matches the expected result.
        - **Signup Test**: Uses random credentials (via Faker) for each test iteration and verifies the success label in the popup message.
        - **Swipe Test**: Verifies proper visibility of the cards and ensures the hidden message appears at the end of the screen.

4. **JDK Version**
    - Built and tested using *OpenJDK 23*.

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AnthonyyHL/MobileAutomationProject.git

2. Install the required dependencies using Maven or any other build tool.
3. Run the tests.