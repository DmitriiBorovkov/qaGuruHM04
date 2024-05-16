import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class JUnit5CodeExampleAvailable {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void searchJUnit5CodeExample() {
        //  - Откройте страницу Selenide в Github
        open("/selenide/selenide");

        //  - Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        //  - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-body .markdown-body").$$("li").shouldHave(itemWithText("Soft assertions"));

        // - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body .markdown-body").$(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                @Test
                void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
                $("#first").should(visible).click();
                $("#second").should(visible).click();
                }
                }"""));
    }
}
