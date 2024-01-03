package app.miniappspring.controller;

import app.miniappspring.entity.Message;
import app.miniappspring.entity.User;
import app.miniappspring.repository.MessageRepo;
import app.miniappspring.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/massage")
public class GreetingController {
private final MessageRepo messageRepo;
private final MyUserDetailsService myUserDetailsService;


    @GetMapping("/home")
    public @ResponseBody String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String,Object> model) {
     model.put("name",name);

        return "greeting";
    }

    @GetMapping("/main")
    public @ResponseBody String main(Map<String,Object>model){
        Iterable<Message> messages =messageRepo.findAll();
        model.put("messages",messages);
        return "main";
    }

    @PostMapping("/main")
    public @ResponseBody String add(@RequestParam String text,@RequestParam String tag, Map<String,Object>model){
       Message message= Message.builder()
               .text(text)
               .tag(tag)
               .build();

       messageRepo.save(message);

        Iterable<Message> messages =messageRepo.findAll();
        model.put("messages",messages);
       return "main";
    }


    @PostMapping("filter")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public @ResponseBody String filter(@RequestParam(name = "filter") String tag, Map<String,Object>model){
        List<Message> messages = messageRepo.findByTag(tag);
        model.put("messages",messages);
        return "main";
    }

    @GetMapping("new-user")
    public @ResponseBody String addUser(Map<String,Object>model){
        return "registration";
    }
    @PostMapping("registration")
    public @ResponseBody String addUser(@RequestBody User user, Map<String,Object>model){
        myUserDetailsService.addUser(user);
        return "home";
    }


}