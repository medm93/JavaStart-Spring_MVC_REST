package pl.javastart.equipy.model.dto;

import java.util.List;

import pl.javastart.equipy.model.entity.Asset;

public class CategoryDTO {

	private Long id;
	private String name;
	private String description;
	private List<Asset> assets;
	
	public CategoryDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
}