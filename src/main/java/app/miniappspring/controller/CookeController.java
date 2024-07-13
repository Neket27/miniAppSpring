package app.miniappspring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class CookeController {
    //записать куки
    @GetMapping(value = "/set-cookie")
    public ResponseEntity<?> setCookie(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("data", "Come_to_the_dark_side");//создаем объект Cookie,
        //в конструкторе указываем значения для name и value
        cookie.setPath("/");//устанавливаем путь
        cookie.setMaxAge(86400);//здесь устанавливается время жизни куки
        response.addCookie(cookie);//добавляем Cookie в запрос
        response.setContentType("text/plain");//устанавливаем контекст
        return ResponseEntity.ok().body(HttpStatus.OK);//получилось как бы два раза статус ответа установили, выбирайте какой вариант лучше
    }

    //прочитать куки
    @GetMapping(value = "/get-cookie")
    public ResponseEntity<?> readCookie(@CookieValue(value = "data") String data) {
        return ResponseEntity.ok().body(data);
    }

    //прочитать заголовки
    @GetMapping(value = "/get-headers")
    public ResponseEntity<?> getHeaders(@RequestHeader Map<String, String> headers){//представляет заголовки ввиде мапы,
        //где ключ это наименование заголовка, а значение мапы - это значение заголовка
        return ResponseEntity.ok(headers);
    }

    //записать заголовок
    @GetMapping(value = "/set-header")
    public ResponseEntity<?> setHeader(){
        return ResponseEntity.ok().header("name-header","value-header").body(HttpStatus.OK);
    }


    //еще варианты работы с заголовками
    @GetMapping(value = "/set-headers")
    public ResponseEntity<?> setHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();//создаем объект
        //который имплементирует мапу MultiValueMap<String, String>
        //наполняем ее парами ключ-значение
        //можно наполнить своими заголовками через метод add
        httpHeaders.add("customer-header", "value-header1");
        //HttpHeaders так же предлагает большой выбор стандартных заголовков
        //Посмотрите на них набрав в IDEA HttpHeaders.
        httpHeaders.add(HttpHeaders.FROM, "russia");
        //можно изменить существующий заголовок, вызвав для него сет-метод
        httpHeaders.setDate(0);
        //или получить значение конкретного заголовка
        Long date = httpHeaders.getDate();
        System.out.println(date);
        return ResponseEntity
                .ok().headers(httpHeaders)//здесь метод принимающий MultiValueMap<String, String>
                .body(HttpStatus.OK);
    }

}
