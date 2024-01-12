package app.miniappspring.controller;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.entity.Message;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.MessageRepo;
import app.miniappspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class GreetingController {
private final MessageRepo messageRepo;
private final UserService userService;

    @GetMapping("/home")
    public  String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String,Object> model) {
     model.put("username","QQ");
//     model.put("users",myUserDetailsService.getListUsers());

        return "greeting";
    }

    @GetMapping("/main")
    public  String main(Map<String,Object>model){
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
  model.put("users",userService.getListUsers());
        Iterable<Message> messages =messageRepo.findAll();
        model.put("messages",messages);
       return "main";
    }


    @PostMapping("filter")
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public @ResponseBody String filter(@RequestParam(name = "filter") String tag, Map<String,Object>model){
        List<Message> messages = messageRepo.findByTag(tag);
        model.put("messages",messages);
        return "main";
    }

    @GetMapping("registration")
    public  String addUser(Map<String,Object>model){
        return "registration";
    }
    @PostMapping(path = "registration",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  String addUser(@ModelAttribute CreateUserArgument createUserArgument, Map<String,Object>model){
        userService.addUser(createUserArgument);
        return "registration";
    }

    @GetMapping("username")
    public String listUser(@RequestParam  String username, Map<String,Object>model){
        model.put("users",userService.getByUsername(username));
        return "greeting";
    }

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @PostMapping(path = "/photo",produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  String upLoadPhoto(Map<String,Object> model,@RequestParam("photoFile") MultipartFile multipartFile) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, multipartFile.getOriginalFilename());
        fileNames.append(multipartFile.getOriginalFilename());
        Files.write(fileNameAndPath, multipartFile.getBytes());
        model.put("msg", "Uploaded images: " + fileNames.toString());

        return "photo";
    }

    @GetMapping("/photoget")
    public String displayUploadForm() {
        return "photo";
    }


}