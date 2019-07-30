package com.example.secretfile.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

	/**
	 * Add an array of files
	 * 
	 * @param multipartFiles
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 */
	public void addDocuments(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException, IOException;

}