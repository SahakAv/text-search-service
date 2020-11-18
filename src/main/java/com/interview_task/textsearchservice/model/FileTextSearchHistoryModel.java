package com.interview_task.textsearchservice.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "FILE_SEARCH_HISTORY")
public class FileTextSearchHistoryModel {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SEARCHTEXT")
    private String searchText;

    @Column(name = "FILEPATH")
    private String filePath;

    @Column(name = "SEARCHDATE")
   private LocalDateTime searchDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        FileTextSearchHistoryModel that = (FileTextSearchHistoryModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(searchText, that.searchText) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(searchDate, that.searchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, searchText, filePath, searchDate);
    }
}
