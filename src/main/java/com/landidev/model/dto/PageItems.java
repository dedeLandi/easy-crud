package com.landidev.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PageItems
 */

@NoArgsConstructor
@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-15T21:09:25.645641-03:00[America/Sao_Paulo]", comments = "Generator version: 7.5.0")
public class PageItems<ITEM> {

    private Integer totalElements;

    private Integer currentPage;

    private Integer totalPages;

    @Valid
    private List<@Valid ITEM> content = new ArrayList<>();

    public PageItems totalElements(Integer totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    /**
     * Get totalElements
     *
     * @return totalElements
     */
    @JsonProperty("totalElements")
    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public PageItems currentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    /**
     * Get currentPage
     *
     * @return currentPage
     */
    @JsonProperty("currentPage")
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public PageItems totalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * Get totalPages
     *
     * @return totalPages
     */
    @JsonProperty("totalPages")
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public PageItems content(List<@Valid ITEM> content) {
        this.content = content;
        return this;
    }

    public PageItems addContentItem(ITEM contentItem) {
        if (this.content == null) {
            this.content = new ArrayList<>();
        }
        this.content.add(contentItem);
        return this;
    }

    /**
     * Get content
     *
     * @return content
     */
    @Valid
    @JsonProperty("content")
    public List<@Valid ITEM> getContent() {
        return content;
    }

    public void setContent(List<@Valid ITEM> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageItems pageItems = (PageItems) o;
        return Objects.equals(this.totalElements, pageItems.totalElements) &&
                Objects.equals(this.currentPage, pageItems.currentPage) &&
                Objects.equals(this.totalPages, pageItems.totalPages) &&
                Objects.equals(this.content, pageItems.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalElements, currentPage, totalPages, content);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PageItems {\n");
        sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
        sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
        sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
        sb.append("    content: ").append(toIndentedString(content)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

