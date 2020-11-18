package com.interview_task.textsearchservice.util;

import com.interview_task.textsearchservice.controller.TextSearchRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtils {

    private static final String DATA_RESOURCES_PACKAGE = "data";
    private final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public boolean fileExist(String filePath) {
        final Path path = getFilePath(filePath);
        return new File(path.toUri()).exists();
    }

    public Path getFilePath(String filePath) {
        Path path = null;
        if (filePath.contains(DATA_RESOURCES_PACKAGE)) {
            try {
                path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
            } catch (URISyntaxException e) {
                LOG.error("Error occurred while trying to get file path {}", filePath);
            }
        } else {
            path = Path.of(filePath);

        }
        return path;
    }
}
