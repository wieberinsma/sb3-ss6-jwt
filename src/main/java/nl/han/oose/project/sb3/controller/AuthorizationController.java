package nl.han.oose.project.sb3.controller;

import nl.han.oose.project.sb3.exceptions.HelloException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resource")
public class AuthorizationController
{
    @GetMapping
    public ResponseEntity<String> sayHello(@RequestParam(required = false) String name)
    {
        if (name.isEmpty())
        {
            throw new HelloException("No one to say hello to!");
        }
        return ResponseEntity.ok("Here is your resource");
    }
}
