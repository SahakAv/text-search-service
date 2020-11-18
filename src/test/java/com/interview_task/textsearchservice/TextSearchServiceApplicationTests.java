package com.interview_task.textsearchservice;

import com.interview_task.textsearchservice.model.response.TestFileSearchHistoryResponse;
import com.interview_task.textsearchservice.model.response.TextFileSearchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TextSearchServiceApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private static final String API_PATH  = "http://localhost:%s";

    private static final String TEXT_FILE_PATH = "data/lorem-ipsum.txt";

    @Test
    public void searchTextWithInvalidFile() {
        final ResponseEntity<String> response = this.restTemplate.getForEntity(
                URI.create(getSearchUrl(UUID.randomUUID().toString(), "TEXT")), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("File with this path not exist");
    }

    @Test
    public void searchWithInvalidText() {
        final String blank = "";
        final ResponseEntity<String> response = this.restTemplate.getForEntity(
                URI.create(getSearchUrl(TEXT_FILE_PATH, blank)), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("empty");
    }

    @Test
    public void searchInFileWithExpectedResult() {
        final String searchText = "Lorem";
        final ResponseEntity<List<TextFileSearchResponse>> responseEntity = search(searchText);
        final List<String> response = responseEntity.getBody().stream().map(TextFileSearchResponse::getResponseLine).collect(Collectors.toList());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotEmpty();
        response.forEach(s -> {
            assertThat(s).contains(searchText);
        });
    }

    @Test
    public void testSearchHistory() {
        final String first = "first";
        final String second = "second";
        search(first);
        search(second);
        final ResponseEntity<List<TestFileSearchHistoryResponse>> responseEntity = getHistory(Sort.Direction.ASC);
        final List<TestFileSearchHistoryResponse> response = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotEmpty();
        assertThat(response.get(0).getSearchText()).isEqualTo(first);
        assertThat(response.get(1).getSearchText()).isEqualTo(second);
    }

    @Test
    public void testHistoryDirectionSorting() {
        final String first = "first";
        final String second = "second";
        search(first);
        search(second);
        final ResponseEntity<List<TestFileSearchHistoryResponse>> responseEntity = getHistory(Sort.Direction.DESC);
        final List<TestFileSearchHistoryResponse> response = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotEmpty();
        assertThat(response.get(0).getSearchText()).isEqualTo(second);
        assertThat(response.get(1).getSearchText()).isEqualTo(first);
    }

    private ResponseEntity<List<TextFileSearchResponse>> search(final String searchText) {
        return restTemplate.exchange(URI.create(getSearchUrl(TEXT_FILE_PATH, searchText)), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TextFileSearchResponse>>() {
                });
    }

    private ResponseEntity<List<TestFileSearchHistoryResponse>> getHistory(final Sort.Direction direction ) {
        return restTemplate.exchange(
                URI.create(getSearchHistoryUrl(direction)), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TestFileSearchHistoryResponse>>() {
                });

    }

        private String getSearchHistoryUrl(final Sort.Direction direction) {
        return String.format(API_PATH + "/search/history?direction=%s", port, direction);
    }

    private String getSearchUrl(final String filePath, final String text) {
        return String.format(API_PATH + "/search?filePath=%s&text=%s", port, filePath, text);
    }
}
