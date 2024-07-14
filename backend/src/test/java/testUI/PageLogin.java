//package testUI;
//
//import com.codeborne.selenide.SelenideElement;
//import com.codeborne.selenide.conditions.Visible;
//
////import static com.codeborne.selenide.Condition.disabled;
//import java.text.MessageFormat;
//
//import static com.codeborne.selenide.Condition.*;
//import static com.codeborne.selenide.Selectors.*;
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.sleep;
//import static org.testng.Assert.*;
//
//
//public class PageLogin {
//
//       public void checkSignIn(String login, String password) {
//        $(byClassName("input100")).val(login);
//        $(byAttribute("placeholder","Password")).val(password);
//        $(byClassName("login100-form-btn")).click();
//        SelenideElement usernameElement = $(byClassName("person-username")).shouldBe(exist);
//    }
//
//    public void checkAuth(String string){
////           String str = MessageFormat.format("//body//h1[text()=\"{0}\"]",string);
//         SelenideElement authBlock =  $(byClassName("auth")).shouldBe(exist);
//    }
//
//}
//
