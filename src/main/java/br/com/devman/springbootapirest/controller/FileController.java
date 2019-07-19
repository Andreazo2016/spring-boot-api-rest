package br.com.devman.springbootapirest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.devman.springbootapirest.service.FileStorageService;
import br.com.devman.springbootapirest.vo.UploadFileResponseVO;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadArquivo")
	public UploadFileResponseVO uploadArquivo( @RequestParam("file") MultipartFile file ) {
		String fileName = fileStorageService.storageFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/v1/file/downloadFile/")
				.path(fileName)
				.toUriString();
		return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	
	@PostMapping("/uploadMultiArquivo")
	public List<UploadFileResponseVO> uploadMultiArquivo( @RequestParam("files") MultipartFile[] files ) {
	
		return Arrays.asList( files )
				.stream()
				.map( file -> uploadArquivo(file) )
				.collect(Collectors.toList());
	}
	
	@GetMapping("/downloadFile/{filename:.+}")
	public ResponseEntity<Resource> downloadFile( @PathVariable("filename") String fileName, HttpServletRequest request) {
		
		Resource resource =  fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType =  request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Não foi possível determinar o tipo de arquivo.");
		}
		
		if( contentType == null ) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
				.body( resource );
	}
}
