package com.interview_task.textsearchservice.repository;

import com.interview_task.textsearchservice.model.FileTextSearchHistoryModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileTextSearchHistoryModelRepository extends PagingAndSortingRepository<FileTextSearchHistoryModel, String> {



}
