package com.example.secretfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.secretfile.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
