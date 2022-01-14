package com.example.studentssocial.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    public void save(MultipartFile file, String filePath, String fileName);

    public Resource load(String filename,String subjectName);

    public void deleteAll();

    public void checkAndAddFolder(String folderName);

    public Stream<Path> loadAll();

    public String getRootPath();

    public String generateNameForExistingFile(String fileName, String filePath);

    public boolean checkIfFileNameExists(String fileName, String filePath);
}
