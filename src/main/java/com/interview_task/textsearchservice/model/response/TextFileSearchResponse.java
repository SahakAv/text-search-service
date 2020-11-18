package com.interview_task.textsearchservice.model.response;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ResponseBody
public class TextFileSearchResponse {
    private String responseLine;

    public TextFileSearchResponse(String responseLine) {
        this.responseLine = responseLine;
    }

    public TextFileSearchResponse() {
    }

    public String getResponseLine() {
        return responseLine;
    }

    public void setResponseLine(String responseLine) {
        this.responseLine = responseLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextFileSearchResponse that = (TextFileSearchResponse) o;
        return Objects.equals(responseLine, that.responseLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseLine);
    }
}
