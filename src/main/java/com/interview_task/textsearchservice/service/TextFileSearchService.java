package com.interview_task.textsearchservice.service;

import java.util.stream.Stream;


public interface TextFileSearchService {


    Stream<String> search(final String filePath, final String text);
}
