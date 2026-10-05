package com.KeyStone.FieldService2.Cloud;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStoragePath {

	public String store(MultipartFile file, String folder);
	public byte[] read(String storagePath);
	public void deleteFilde(String cloudId);
}
