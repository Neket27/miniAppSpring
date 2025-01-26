//package testUI;
//
//import com.codeborne.selenide.Configuration;
//import org.junit.jupiter.api.Assertions;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import static com.codeborne.selenide.Selenide.*;
//@SpringBootTest
//public class MainClassTest {
//
//    @BeforeSuite
//    public void beforeSuite() {
//        Configuration.baseUrl ="http://localhost:5173";
//        Configuration.browser ="firefox";
//    }
//
//    @Test
//    public void testPageRegistration(){
//        PageRegistration pageRegistration = open("/register", PageRegistration.class);
//        pageRegistration.checkRegistration("user1","password1","password1");
//    }
//
//    @Test
//    public void testPageLogin(){
//        PageLogin pageLogin = open("/login", PageLogin.class);
//        pageLogin.checkSignIn("user1", "password1");
//
//    }
//
//    @Test
//    public void testBlockAuth(){
//        PageLogin pageLogin = open("/login", PageLogin.class);
//        pageLogin.checkAuth("АВТОРИЗУЙТЕСЬ1");
//    }
//
//
//}
