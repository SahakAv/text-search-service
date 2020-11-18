package com.interview_task.textsearchservice.controller;

import com.interview_task.textsearchservice.model.response.TestFileSearchHistoryResponse;
import com.interview_task.textsearchservice.model.response.TextFileSearchResponse;
import com.interview_task.textsearchservice.service.FileTextSearchHistoryService;
import com.interview_task.textsearchservice.service.TextFileSearchService;
import com.interview_task.textsearchservice.validator.FileExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class TextSearchRestController {

    private final FileTextSearchHistoryService fileTextSearchHistoryService;
    private final TextFileSearchService fileSearchService;
    private final Logger LOG = LoggerFactory.getLogger(TextSearchRestController.class);

    public TextSearchRestController(FileTextSearchHistoryService fileTextSearchHistoryService, TextFileSearchService fileSearchService, JdbcTemplate template) {
        this.fileTextSearchHistoryService = fileTextSearchHistoryService;
        this.fileSearchService = fileSearchService;
    }

    @GetMapping("search")
    public Flux<TextFileSearchResponse> search(@RequestParam @FileExist String filePath,
                                               @RequestParam @NotBlank(message = "Search text should not be empty") String text) {
        LOG.info("Requested to search in file {} with text {}", filePath, text);
        return Flux.fromStream(fileSearchService.search(filePath, text)).map(TextFileSearchResponse::new);
    }

    @GetMapping("search/history")
    public Flux<TestFileSearchHistoryResponse> searchHistory(@RequestParam @NotNull(message =
            "Search direction should not be null ASC/DESC") Sort.Direction direction) {
        LOG.info("Requested to get search history by a sort direction {}", direction);

        return fileTextSearchHistoryService.getHistory(direction);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public void exception(ConstraintViolationException exception) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
    }


}
