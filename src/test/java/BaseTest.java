import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

abstract public class BaseTest {

    @BeforeEach
    public void setup() {
        Configuration.headless = true;
    }

    @AfterEach
    public void teardown() {
        closeWebDriver();
    }

}
