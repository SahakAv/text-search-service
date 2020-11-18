package com.interview_task.textsearchservice.service;

import com.interview_task.textsearchservice.controller.TextSearchRestController;
import com.interview_task.textsearchservice.model.FileTextSearchHistoryModel;
import com.interview_task.textsearchservice.repository.FileTextSearchHistoryModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Service
public class DefaultTextFileSearchServiceImpl implements TextFileSearchService {
    private final Logger LOG = LoggerFactory.getLogger(TextSearchRestController.class);

    private final FileTextSearchHistoryModelRepository searchHistoryRepository;

    public DefaultTextFileSearchServiceImpl(FileTextSearchHistoryModelRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    @Override
    public Stream<String> search(final String filePath, final String text) {
        addToHistory(filePath, text);
        Path path;
        try {
            if (filePath.contains("data")) {
                path = Paths.get(ClassLoader.getSystemResource("data/lorem-ipsum.txt").toURI());
            } else {
                path = Path.of(filePath);
            }
            return Files.lines(path).filter(s -> s.contains(text));
        } catch (URISyntaxException | IOException e) {
            LOG.error("Error occurred while trying to search in file {} with text {}", filePath, text);
        }


        return Stream.empty();
    }

    private void addToHistory(final String filePath, final String text) {
        FileTextSearchHistoryModel historyModel = new FileTextSearchHistoryModel();
        historyModel.setFilePath(filePath);
        historyModel.setSearchText(text);
        historyModel.setSearchDate(LocalDateTime.now());
        searchHistoryRepository.save(historyModel);
    }
}
