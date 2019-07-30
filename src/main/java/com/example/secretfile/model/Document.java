package com.example.secretfile.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "mime_type")
	private String mimeType;
	
	@Column(name = "size")
	private long size = 0;

	@Column(name = "hash", nullable = false, unique = true)
	private String hash;
	
	public static final int RADIX = 16;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getHash() {
		return hash;
	}

	public void setHash() throws NoSuchAlgorithmException {
		String transformedName = new StringBuilder().append(this.name).append(this.mimeType).append(this.size)
				.append(new Date().getTime()).toString();
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(transformedName.getBytes(StandardCharsets.UTF_8));
		this.hash = new BigInteger(1, messageDigest.digest()).toString(RADIX);
	}
}