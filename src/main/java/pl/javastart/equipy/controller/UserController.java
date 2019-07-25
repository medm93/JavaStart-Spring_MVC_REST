package pl.javastart.equipy.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
    	dto = userService.createUser(dto);
        URI location = ServletUriComponentsBuilder
        		.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUser(@RequestParam(required = false) String lastName) {
    	if(lastName != null) {
    		return ResponseEntity.ok(userService.findAllUsersByLastName(lastName));
    	} else {
	        return ResponseEntity.ok(userService.findAllUsers());
    	}
    }
}
