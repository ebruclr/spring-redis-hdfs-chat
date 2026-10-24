package com.example.project2.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HdfsService {
    private final FileSystem fileSystem;

    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String hdfsPathStr = "/images/" + fileName;
        Path hdfsPath = new Path(hdfsPathStr);

        Path parentDir = hdfsPath.getParent();
        if (!fileSystem.exists(parentDir)) {
            fileSystem.mkdirs(parentDir);
        }

        try (FSDataOutputStream outputStream = fileSystem.create(hdfsPath, true)) {
            outputStream.write(file.getBytes());
        }

        return hdfsPathStr;
    }

    public byte[] getFile(String hdfsFilePath) throws Exception {
        Path path = new Path(hdfsFilePath);

        if (!fileSystem.exists(path)) {
            return new byte[0];
        }

        try (FSDataInputStream inputStream = fileSystem.open(path)) {
            return IOUtils.toByteArray(inputStream);
        }
    }
}
