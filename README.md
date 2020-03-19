=== Koverj

Java agent that allow to collect locators from Selenium based UI tests.

Use with gradle and Selenide:

```build.gradle
repositories {
    mavenCentral()

    maven {
        url = 'https://maven.pkg.github.com/SergeyPirogov/koverj-java-agent'
        credentials {
                username = "GH_USERNAME"
                password = "TOKEN"
        }
    }
}

dependencies {
    compile 'koverj-java-agent:koverj-junit5:1.0'
    compile 'koverj-java-agent:koverj-selenide:1.0.1'
}
```

Add Selenide listener:

IMPORTANT: In case of concurent test execution you have to register listener in **beforeEach** block:

```
@BeforeAll
static void setUp() {
    SelenideLogger.addListener("locators", new KoverjSelenide());
}
```

Supported integrations:

- Selenide
- Selenium

Supported test runners:

- JUnit 5
- TestNG

In order to collect data you have to run koverj server:

```
docker run -p 8086:8086 spirogov/koverj:0.0.9
```

Run test as you did before

In order to see results in your browser, request chrome plugin from @SergeyPirogov

Feel free to provide feedback, submit bugs and feature request using Github issues.

Happy testing!
