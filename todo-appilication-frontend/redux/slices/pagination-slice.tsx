import { PaginationSliceState } from "@/types/pagination-types";
import { createSlice } from "@reduxjs/toolkit";

const initialState: PaginationSliceState = {
    currentPage: 1,
    totalPages: 0,
    totalResults: 0,
    pageSize: 5,
};

export const paginationSlice = createSlice({
    name: "Pagination",
    initialState,
    reducers: {
        setCurrentPage(state, action) {
            state.currentPage = action.payload;
        },
        setTotalPages(state, action) {
            state.totalPages = action.payload;
        },
        setTotalResults(state, action) {
            state.totalResults = action.payload;
        },
        setPageSize(state, action) {
            state.pageSize = action.payload;
        },
    },
});

export const {
   setCurrentPage,
   setTotalPages,
   setTotalResults,
   setPageSize,
} = paginationSlice.actions;
export default paginationSlice.reducer;
