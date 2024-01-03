package app.miniappspring.controller;

import app.miniappspring.entity.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

     @PostMapping("/create")
    public Message createPost(@Valid @RequestBody Message message) {
        return new Message(message.getId(),message.getText(),message.getTag());
    }

    @GetMapping("/{id}")
    public Message getById(@PathVariable UUID id) {
        return new Message(1,"text","tag");
    }
}
