package pl.javastart.equipy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.javastart.equipy.model.dto.UserDTO;
import pl.javastart.equipy.service.UserService;

@RequestMapping("/api/users")
@RestController
public class UserController {
	
	private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUser() {
        List<UserDTO> users = userService.findAllUsers();
        //log.info("Received {} results for users search", users.size());
        return ResponseEntity.ok(users);
    }

}
