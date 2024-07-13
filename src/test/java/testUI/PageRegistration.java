//package testUI;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testng.annotations.Test;
//
//import static com.codeborne.selenide.Selectors.byAttribute;
//import static com.codeborne.selenide.Selectors.byClassName;
//import static com.codeborne.selenide.Selenide.*;
//
//
//public class PageRegistration {
//
//    public void checkRegistration(String login, String password, String repeatPassword){
//        $(byClassName("input100")).val(login);
//        $(byAttribute("placeholder","Password")).val(password);
//        $(byAttribute("placeholder","Repeat password")).val(repeatPassword);
//        $(byClassName("login100-form-btn")).click();
//
//    }
//}
