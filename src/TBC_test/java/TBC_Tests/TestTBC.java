package TBC_Tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTBC {

    @Test()
    @Order(1)
    void test_image_upload() {
        //ბრაუზერის გაშვება
        open("http://the-internet.herokuapp.com/");
        //1.       დააჭირე File Upload-ს , ატვირთე ფაილი და შეამოწმე რომ ფაილი აიტვირთა
        $(byText("File Upload")).click();
        $(byId("file-upload")).sendKeys("C:\\Users\\Tsotne\\Desktop\\test\\churchxela.jpg");
        $(byId("file-submit")).click();
        assertTrue($(byId("uploaded-files")).getText().contains("churchxela.jpg"));
    }

    @Test
    @Order(2)
    void test_mouse_hover() {
        open("http://the-internet.herokuapp.com/");
        //2. დააჭირე Shifting Content-ს
        //·  მიიტანე მაუსი Home ღილაკთან და შეამოწმე რომ დიზაინი შეიცავალა
        $(byText("Shifting Content")).click();
        $(byXpath("//*[contains(text(), 'Menu Element')]")).click();
        String color1 = $(byText("Home")).getCssValue("color");
        $(byText("Home")).hover();
        String color2 = $(byText("Home")).getCssValue("color");
        assertNotSame(color1, color2);
    }

    @Test
    @Order(3)
    void test_change_image_size() {
        back();
        // · დააჭირე Image-ს და შემდეგ დააჭირე მეორე click here-ს და შეამოწმე რომ ფოტოს ზომა შეიცვალა.შემდეგ დააჭირე მეოთხე click here-ს და შეამოწმე, რომ ფოტო შეიცვალა
        $(byXpath("//*[contains(text(), 'image')]")).click();
        String size = $(byXpath("//*[contains(@src, '/img/avatar.jpg')]")).getCssValue("left");
        $$(byText("click here")).get(2).click();
        String size2 = $(byXpath("//*[contains(@src, '/img/avatar.jpg')]")).getCssValue("left");
        assertNotSame(size, size2);
        String image1 = $(byXpath("//img[contains(@class, 'shift')]")).getAttribute("src");
        $$(byText("click here")).last().click();
        String image2 = $(byXpath("//img[contains(@class, 'shift')]")).getAttribute("src");
        assertNotSame(image1, image2);

        // URL-ით შემოწმება
        $$(byText("click here")).get(2).click();
        String image1_0 = WebDriverRunner.url();
        $$(byText("click here")).last().click();
        String image2_0 = WebDriverRunner.url();
        assertNotSame(image1_0, image2_0);

    }

}

