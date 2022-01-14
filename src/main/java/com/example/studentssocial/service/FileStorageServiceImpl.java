package com.example.studentssocial.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String ROOT_NAME = "uploads";
    private final Path root = Path.of("uploads");

    @Override
    public void save(MultipartFile file, String filePath, String fileName) {
        try {
            Files.copy(file.getInputStream(), Path.of(filePath).resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public boolean checkIfFileNameExists(String filename, String filePath) {
        return Files.exists(Path.of(filePath + File.separator + filename));
    }

    @Override
    public String generateNameForExistingFile(String fileName, String filePath) {
        try {

            String[] fileNameSplitted = fileName.split("\\.");
            String extension = "";
            String fileNameTruncated = fileName;
            if (fileNameSplitted.length > 1) {

                extension = fileNameSplitted[fileNameSplitted.length - 1];
                fileNameTruncated = fileName.substring(0, fileName.length() - (extension.length() + 1));
            }
            return fileNameTruncated + "_copy." + extension;

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public String getRootPath() {
        return this.ROOT_NAME;
    }

    @Override
    public void checkAndAddFolder(String folderName) {
        try {
            if (!Files.exists(Path.of(folderName))) {
                Files.createDirectory(Path.of(folderName));
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Resource load(String filename,String subjectName) {
        try {
            Path file = root.resolve(subjectName+File.separator + filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}