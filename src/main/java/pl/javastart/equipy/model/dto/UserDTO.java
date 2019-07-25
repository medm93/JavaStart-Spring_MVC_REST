package pl.javastart.equipy.model.dto;

public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String pesel;
	
	public UserDTO() {
	}
	
	public UserDTO(Long id, String firstName, String lastName, String pesel) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", pesel=" + pesel + "]";
	}
	
	
}
