package com.example.secretfile.service.impl;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.secretfile.model.Document;
import com.example.secretfile.repository.DocumentRepository;
import com.example.secretfile.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Override
	@Transactional
	public void addDocuments(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException {
		for (MultipartFile multipartFile : multipartFiles) {
			create(multipartFile);
		}
	}
	
	private void create(MultipartFile multipartFile) throws NoSuchAlgorithmException {
		Document document = new Document();
		document.setName(multipartFile.getOriginalFilename());
		document.setMimeType(multipartFile.getContentType());
		document.setSize(multipartFile.getSize());
		document.setHash();
		documentRepository.save(document);
	}
}