📱 Mobile Test Automation Framework (Appium + Java)

🚀 Overview

This project is a mobile test automation framework built to test Android and iOS applications using:<br>
•	Java<br>
•	Appium<br>
•	TestNG<br>
•	Cucumber (BDD)<br>


The framework is designed with scalability, maintainability, and real-world usage in mind. It supports cross-platform testing, parallel execution, and clean separation of concerns using the Page Object Model.

⸻

🧱 Tech Stack<br>
•	Language: Java<br>
•	Automation: Appium (Android + iOS)<br>
•	Test Framework: TestNG<br>
•	BDD: Cucumber<br>
•	Build Tool: Maven<br>
•	CI/CD: GitHub Actions<br>
•	Logging: Log4j2<br>

⸻

🏗️ Framework Architecture

The framework follows a layered design:<br>
src <br/>
├── main/java/framework<br/>
│   ├── config        → ConfigManager (env + system properties)<br/>
│   ├── driver        → DriverFactory, DriverManager (ThreadLocal)<br/>
│   ├── pages         → BasePage + platform-specific pages<br/>
│   └── utils         → WaitUtils, ScreenshotUtils, LoggerUtils<br/>
│<br/>
├── test/java/tests<br/>
│   ├── runners       → Cucumber + TestNG runner<br/>
│   ├── steps         → Step Definitions + Hooks<br/>
│<br/>
└── resources<br/>
├── features      → Cucumber feature files<br/>
├── config        → config.properties<br/>
<br>
⚙️ Key Features

✅ Cross-Platform Support<br>
•	Supports both Android and iOS<br>
•	Platform-specific page implementations (LoginPageAndroid, LoginPageIOS)

⸻

✅ Page Object Model (POM)<br>
•	Clean separation of UI interactions<br>
•	Reusable page methods<br>
•	Centralized actions in BasePage

⸻

✅ Cucumber + TestNG Integration<br>
•	BDD-style test scenarios using .feature files<br>
•	Test execution managed by TestNG

⸻

✅ Parallel Execution<br>
•	Thread-safe driver management using ThreadLocal<br>
•	Supports running Android scenarios in parallel across multiple devices<br>
•	Assigns each device its own UiAutomator2 `systemPort`

⸻

✅ Centralized Wait Handling<br>
•	WaitUtils handles all explicit waits<br>
•	BasePage integrates waits into common actions (click, type, etc.)

⸻

✅ Config Management<br>
•	Uses config.properties for default values<br>
•	Supports runtime overrides via Maven:<br>
`mvn test -Dplatform=android -DdeviceName=emulator-5554`

⸻

✅ Multi-Device Android Parallel Execution<br>
•	Configure connected Android devices in `src/test/resources/config/config.properties` using `android.devices`<br>
•	Format: `deviceName:systemPort,deviceName:systemPort`<br>
•	Scenarios are distributed round-robin across configured devices<br>
•	Each scenario gets a device-specific Appium session and UiAutomator2 `systemPort`

⸻

✅ Screenshot on Failure<br>
•	Automatically captures screenshots on test failure<br>
•	Attached to Cucumber reports

⸻

✅ Logging<br>
•	Log4j2 integration for structured logging<br>
•	Logs stored in target/logs

⸻

✅ Tag-Based Execution

Run specific test groups using tags:<br>
`mvn test -Dcucumber.filter.tags="@smoke"`

⸻

✅ CI Integration (GitHub Actions)<br>
•	Automated test execution on push<br>
•	Generates and uploads test reports

⸻

🧪 Sample Test Execution

Run all tests<br>
`mvn clean test`

Run smoke tests<br>
`mvn clean test -Dcucumber.filter.tags="@smoke"`

Run on specific platform<br>
`mvn clean test -Dplatform=ios -DdeviceName="iPhone 15"`

Run Android tests in parallel across two devices<br>
`mvn clean test -Dplatform=android`

Example `config.properties` for two Android devices<br>
```properties
platform=android
android.devices=emulator-5554:8201,RZ8R30XNHAV:8202
app.path=src/main/resources/base.apk
appium.url=http://127.0.0.1:4723
implicit.wait=10
explicit.wait=20
```

Parallel execution notes<br>
•	Both devices must be visible in `adb devices` before the run starts<br>
•	Each Android device must have a unique `systemPort`<br>
•	Run a single runner class so the same suite is not executed twice<br>
•	If you add more devices, append them to `android.devices` with unused ports


⸻

📌 Example Feature

`Feature: Login`

`@smoke`<br>
`Scenario: Successful login`<br>
`Given the user is on the login screen`<br>
`When the user logs in with username "testuser" and password "Password123"`



⸻

📷 Project Highlights<br>
•	Designed for real-device and emulator testing<br>
•	Supports scalable parallel execution<br>
•	Clean architecture aligned with industry best practices<br>
•	Easily extendable for:<br>
•	API testing<br>
•	Cloud device execution (BrowserStack, Sauce Labs)<br>
•	Performance testing integration

⸻

🔧 Setup Instructions

Prerequisites<br>
•	Java 17+<br>
•	Maven<br>
•	Node.js + npm<br>
•	Appium 2<br>
•	Android Studio (for Android testing)<br>
•	Xcode (for iOS testing)

⸻

Install Appium

`npm install --location=global appium`<br>
`appium driver install uiautomator2`<br>
`appium driver install xcuitest`


⸻

Start Appium Server

`appium`

🚀 Future Improvements<br>
•	Integration with cloud providers (BrowserStack / Sauce Labs)<br>
•	Enhanced reporting (Allure)<br>
•	Test data management layer<br>
•	Environment-based config (dev / staging / prod)

⸻

👤 Author

Kunle Oduwobi<br>
QA Automation Engineer
