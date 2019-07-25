package pl.javastart.equipy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.javastart.equipy.model.dto.UserDTO;
import pl.javastart.equipy.model.entity.User;
import pl.javastart.equipy.repository.UserRepository;
import pl.javastart.equipy.utils.exception.*;
import pl.javastart.equipy.utils.mapper.UserMapper;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Autowired
	public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
	
	public UserDTO createUser(UserDTO userDTO) {
		if(userDTO.getId() != null) {
			throw new BadRequestException("Zapisywanie obiektu nie może mieć ustawionego ID");
		}
		if(userRepository.existsByPesel(userDTO.getPesel())) {
			throw new ConflictException("Użytkownik z takim peselem już istnieje");
		}
		User userEntity = userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(userEntity);
		return userMapper.toDTO(savedUser);
	}
	
	public UserDTO findUser(Long id) {
		Optional<User> searchResult = userRepository.findById(id);
		if(!searchResult.isPresent()) {
			throw new NotFoundException("Użytkownik o wskazanym id nie istnieje");
		} else {
			return searchResult.map(userMapper::toDTO).get();
		}
	}
	
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		if(id != userDTO.getId()) {
			throw new BadRequestException("Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
		}
		if(userRepository.existsByPesel(userDTO.getPesel())) {
			throw new ConflictException("Inny użytkownik z takim peselem już istnieje");
		}
		Optional<User> searchResult = userRepository.findById(id);
		User userEntity = searchResult.get();
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setPesel(userDTO.getPesel());
	    User updatedUser = userRepository.save(userEntity);
	    return userMapper.toDTO(updatedUser);
	}
	
	public List<UserDTO> findAllUsers() {
		return userRepository.findAll().stream()
				.map(userMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public List<UserDTO> findAllUsersByLastName(String lastName) {
		return userRepository.findAllByLastNameContainingIgnoreCase(lastName).stream()
				.map(userMapper::toDTO)
				.collect(Collectors.toList());
	}

}
