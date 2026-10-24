package com.example.project2.config;

import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URI;

@Configuration
public class HdfsConfig {

    static {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            File tempHadoopBin = new File(System.getProperty("java.io.tmpdir"), "hadoop/bin");
            if (!tempHadoopBin.exists()) {
                tempHadoopBin.mkdirs();
            }
            File tempHadoopDir = tempHadoopBin.getParentFile();
            System.setProperty("hadoop.home.dir", tempHadoopDir.getAbsolutePath());
        }
    }

    @Value("${hdfs.uri:hdfs://localhost:9000}")
    private String hdfsUri;

    @Bean
    public FileSystem fileSystem() throws Exception {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();

        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        return FileSystem.get(new URI(hdfsUri), conf, "kealy");
    }
}