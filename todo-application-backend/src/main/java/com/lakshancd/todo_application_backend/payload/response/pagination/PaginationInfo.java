package com.lakshancd.todo_application_backend.payload.response.pagination;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaginationInfo {
    private Integer currentPage;
    private Integer totalPages;
    private Long totalResults;
    private Integer pageSize;
}
