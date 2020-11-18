package com.interview_task.textsearchservice.model.response;

import com.interview_task.textsearchservice.model.FileTextSearchHistoryModel;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Objects;

@ResponseBody
public class TestFileSearchHistoryResponse {
    private String searchText;
    private String filePath;
    private LocalDateTime searchDate;

    public TestFileSearchHistoryResponse(FileTextSearchHistoryModel historyModel) {
        this.searchText = historyModel.getSearchText();
        this.filePath = historyModel.getFilePath();
        this.searchDate = historyModel.getSearchDate();
    }

    public TestFileSearchHistoryResponse() {
    }


    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestFileSearchHistoryResponse that = (TestFileSearchHistoryResponse) o;
        return Objects.equals(searchText, that.searchText) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(searchDate, that.searchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchText, filePath, searchDate);
    }
}
