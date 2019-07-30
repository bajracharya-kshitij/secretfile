package com.example.secretfile.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.secretfile.model.Document;
import com.example.secretfile.property.DocumentStorageProperty;
import com.example.secretfile.repository.DocumentRepository;
import com.example.secretfile.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	private final Path docStorageLocation;
	
	@Autowired
	public DocumentServiceImpl(DocumentStorageProperty documentStorageProperty) throws IOException {
		this.docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
		Files.createDirectories(this.docStorageLocation);
	}
	
	@Override
	@Transactional
	public void addDocuments(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException, IOException {
		for (MultipartFile multipartFile : multipartFiles) {
			create(multipartFile);
		}
	}
	
	private void create(MultipartFile multipartFile) throws NoSuchAlgorithmException, IOException {
		Document document = new Document();
		document.setName(multipartFile.getOriginalFilename());
		document.setMimeType(multipartFile.getContentType());
		document.setSize(multipartFile.getSize());
		document.setHash();
		storeDocument(multipartFile, document.getHash());
		documentRepository.save(document);
	}
	
	private void storeDocument(MultipartFile file, String hash) throws IOException {
		Path targetLocation = this.docStorageLocation.resolve(hash);
		Files.copy(file.getInputStream(), targetLocation);
	}
}