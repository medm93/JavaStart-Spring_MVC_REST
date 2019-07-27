package pl.javastart.equipy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.javastart.equipy.model.dto.AssetDTO;
import pl.javastart.equipy.service.AssetService;

@RequestMapping("/api/assets")
@RestController
public class AssetController {

	private final AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }
    
    @GetMapping
    public ResponseEntity<List<AssetDTO>> findAllAssets() {
    	return ResponseEntity.ok(assetService.findAllAssets());
    }

}