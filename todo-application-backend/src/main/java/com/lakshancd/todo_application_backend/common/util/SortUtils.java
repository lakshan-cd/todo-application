package com.lakshancd.todo_application_backend.common.util;

import org.springframework.data.domain.Sort;

public class SortUtils {

    public static Sort getSort(String sortDirection, String... properties) {
        Sort sort = Sort.by(properties);
        if (sortDirection != null && sortDirection.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }
}