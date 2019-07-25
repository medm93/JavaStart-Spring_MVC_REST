package pl.javastart.equipy.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    	UserDTO savedUser = userService.createUser(userDTO);
        URI location = ServletUriComponentsBuilder
        		.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Long id) {
    	return userService.findUser(id)
    			.map(ResponseEntity::ok)
    			.orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    	UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers(@RequestParam(required = false) String lastName) {
    	if(lastName != null) {
    		return ResponseEntity.ok(userService.findAllUsersByLastName(lastName));
    	} else {
	        return ResponseEntity.ok(userService.findAllUsers());
    	}
    }
}
