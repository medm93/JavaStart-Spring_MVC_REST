package pl.javastart.equipy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.javastart.equipy.model.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
	
	//WARIANT 1 | MAŁO CZYTELNA METODA
	List<Asset> findAllByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(String name, String serialNumber);
	
	//WARIANT 2
	@Query("SELECT a FROM Asset a WHERE lower(a.name) LIKE lower(concat('%', :search, '%')) " +
            "OR lower(a.serialNumber) LIKE lower(concat('%', :search, '%'))")
	List<Asset> findAllByNameOrSerialNumber(@Param("search") String search);
}
