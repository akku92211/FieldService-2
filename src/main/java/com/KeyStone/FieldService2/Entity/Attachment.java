package com.KeyStone.FieldService2.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attachment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	private String contentType;
	@Column(length=1000)
	private String storagePath;
	private Long sizeOffile;
	private String cloudinaryId;

	private LocalDateTime uploadedAt=LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public Long getSizeOffile() {
		return sizeOffile;
	}

	public void setSizeOffile(Long sizeOffile) {
		this.sizeOffile = sizeOffile;
	}

	public String getCloudinaryId() {
		return cloudinaryId;
	}

	public void setCloudinaryId(String cloudinaryId) {
		this.cloudinaryId = cloudinaryId;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public static Attachment save(Attachment attach) {
		// TODO Auto-generated method stub
		return null;
	}
}
