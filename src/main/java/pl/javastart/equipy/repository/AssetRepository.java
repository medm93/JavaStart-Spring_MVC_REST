package pl.javastart.equipy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.javastart.equipy.model.entity.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
