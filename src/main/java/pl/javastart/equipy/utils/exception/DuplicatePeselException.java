package pl.javastart.equipy.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Użytkownik z takim peselem już istnieje")
public class DuplicatePeselException extends RuntimeException{
}
