package pl.javastart.equipy.utils.mapper;

import org.mapstruct.Mapper;

import pl.javastart.equipy.model.dto.AssetDTO;
import pl.javastart.equipy.model.entity.Asset;

@Mapper(componentModel = "spring")
public interface AssetMapper {

	default AssetDTO toDTO(Asset entity) {
		if(entity == null) {
			return null;
		}
		AssetDTO dto = new AssetDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setSerialNumber(entity.getSerialNumber());
		dto.setCategory(entity.getCategory().getName());
		return dto;
	}
}