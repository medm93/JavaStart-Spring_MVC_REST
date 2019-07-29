package pl.javastart.equipy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.javastart.equipy.model.dto.AssetDTO;
import pl.javastart.equipy.repository.AssetRepository;
import pl.javastart.equipy.utils.mapper.AssetMapper;

@Service
public class AssetService {

	private final AssetRepository assetRepository;
	private final AssetMapper assetMapper;
	
	@Autowired
	public AssetService(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }
	
	public List<AssetDTO> findAllAssets() {
		return assetRepository.findAll().stream()
				.map(assetMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public List<AssetDTO> findAllByNameOrSerialNumber(String search) {
		return assetRepository.findAllByNameOrSerialNumber(search).stream()
				.map(assetMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	/*public List<AssetDTO> findAllAssetsByNameOrSerialN(String name, String serialNumber) {
		return assetRepository.findAllByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(name, serialNumber).stream()
				.map(assetMapper::toDTO)
				.collect(Collectors.toList());
	}*/
}