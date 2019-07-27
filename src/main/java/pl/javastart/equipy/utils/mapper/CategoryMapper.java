package pl.javastart.equipy.utils.mapper;

import org.mapstruct.Mapper;

import pl.javastart.equipy.model.dto.CategoryDTO;
import pl.javastart.equipy.model.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	Category toEntity(CategoryDTO dto);
	CategoryDTO toDTO(Category entity);
}