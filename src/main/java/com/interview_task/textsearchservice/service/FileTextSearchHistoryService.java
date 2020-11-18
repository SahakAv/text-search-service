package com.interview_task.textsearchservice.service;

import com.interview_task.textsearchservice.model.response.TestFileSearchHistoryResponse;
import com.interview_task.textsearchservice.repository.FileTextSearchHistoryModelRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class FileTextSearchHistoryService {

    private final FileTextSearchHistoryModelRepository repository;

    public FileTextSearchHistoryService(FileTextSearchHistoryModelRepository repository) {
        this.repository = repository;
    }


    public Flux<TestFileSearchHistoryResponse> getHistory(Sort.Direction direction) {
        return Flux.fromIterable(repository.findAll(Sort.by(direction, "searchDate"))).
                map(TestFileSearchHistoryResponse::new);
    }
}
