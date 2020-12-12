package ru.social.social.controller;

import org.springframework.web.bind.annotation.*;
import ru.social.social.exeptions.NotFoundExeptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<>(){{
       add(new HashMap<String, String>() {{ put("id", "1"); put("text", "first message"); }});
       add(new HashMap<String, String>() {{ put("id", "2"); put("text", "second message"); }});
       add(new HashMap<String, String>() {{ put("id", "3"); put("text", "third message"); }});
    }};

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(msg -> msg.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExeptions::new);
    }

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }


    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> msgFromDb = getMessage(id);
        msgFromDb.putAll(message);
        msgFromDb.put("id", id);

        return msgFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> msg = getMessage(id);
        messages.remove(msg);
    }
}
