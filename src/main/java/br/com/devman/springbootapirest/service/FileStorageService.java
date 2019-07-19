package br.com.devman.springbootapirest.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.devman.springbootapirest.util.Constantes;


@Service
public class FileStorageService {
	

	private final Path fileStorageLocation;
	
	
	public FileStorageService() {
		this.fileStorageLocation = Paths.get( Constantes.DIRETORIO_DE_SALVAR_ARQUIVO ).toAbsolutePath().normalize();
		criarDiretorio(fileStorageLocation);
	}
	
	public Resource loadFileAsResource( String fileName ) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource( filePath.toUri() );
			if( resource.exists() ) {
				return resource;
			}else {
				throw new RuntimeException("Arquivo não encontrado.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Arquivo não encontrado.");
		}
		
	}
	public String storageFile( MultipartFile file ) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new RuntimeException("Formato inválido de arquivo: " + fileName);
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING );
			
			return fileName;
			
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel salvar arquivo");
		}
		
	}
	private void criarDiretorio( Path diretory ) {
		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception e) {
			throw new RuntimeException("Não foi possivel criar diretorio para salvar arquivo");
		}
	}
}
