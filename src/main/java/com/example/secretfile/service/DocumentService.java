package com.example.secretfile.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

	/**
	 * Add an array of files
	 * 
	 * @param multipartFiles
	 * @throws NoSuchAlgorithmException 
	 */
	public void addDocuments(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException;

}