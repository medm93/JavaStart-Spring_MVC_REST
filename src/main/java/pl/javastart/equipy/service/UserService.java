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
    		throw new BadRequestException("Zapisywany obiekt nie może mieć ustawionego id");
		}
		Optional<User> searchResult = userRepository.findByPesel(userDTO.getPesel());
		searchResult.ifPresent(userEntity -> {
			throw new DuplicatePeselException();
		});
		return mapAndSaveUser(userDTO);
	}
	
	public Optional<UserDTO> findUser(Long id) {
		return userRepository.findById(id).map(userMapper::toDTO);
	}
	
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		if(!id.equals(userDTO.getId())) {
    		throw new BadRequestException("Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
		}
		Optional<User> searchResult = userRepository.findByPesel(userDTO.getPesel());
		searchResult.ifPresent(userEntity -> {
			if(!userEntity.getId().equals(userDTO.getId())) {
				throw new DuplicatePeselException();
			}
		});
		return mapAndSaveUser(userDTO);
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

	private UserDTO mapAndSaveUser(UserDTO userDTO) {
		User userEntity = userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(userEntity);
		return userMapper.toDTO(savedUser);
	}
}
