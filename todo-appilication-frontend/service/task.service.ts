import { setCurrentPage, setPageSize, setTotalPages, setTotalResults } from "@/redux/slices/pagination-slice";
import { setTask, setTasks, addNewTask, removeTask } from "@/redux/slices/task-slice";
import { dispatch } from "@/redux/store";
import { ErrorType } from "@/types/common";
import { CreateTaskRequest, TaskResponse, Task } from "@/types/task-types";
import axiosInstance from "@/utils/axios";
import { enqueueSnackbar } from "notistack";

const baseUrl = "task";

export const getAllTasks = async (params: any[]) => {
    try {
        const response = await axiosInstance.get(`${baseUrl}?${params.join("&")}`, {
        });
        const data = response.data as TaskResponse;
        dispatch(setTasks(data.data.result));
        dispatch(setTotalPages(data.data.paginationInfo.totalPages));
        dispatch(setTotalResults(data.data.paginationInfo.totalResults));
        dispatch(setPageSize(data.data.paginationInfo.pageSize));
        dispatch(setCurrentPage(data.data.paginationInfo.currentPage));
        return data.data.result;
    } catch (error : ErrorType |any) {
        throw error;
    }
}

export const createTask = async (taskData: CreateTaskRequest) => {
    try {
        const response = await axiosInstance.post(`${baseUrl}`, taskData);
        const data = response.data as TaskResponse;
        if (data.data) {
            dispatch(addNewTask(data.data));
        }
        return data;
    } catch (error : ErrorType |any) {
        throw error;
    }
}

export const completeTask = async (taskId: number, obj: any) => {
    try {
        const response = await axiosInstance.patch(`${baseUrl}/${taskId}/complete`, obj);
        const data = response.data as TaskResponse;
        if (data.data) {
            dispatch(removeTask(taskId));
        }
        return data;
    } catch (error : ErrorType |any) {
        throw error;
    }
}

export const deleteTask = async (taskId: number, obj: any) => {
    try {
        const response = await axiosInstance.patch(`${baseUrl}/${taskId}/delete`, obj );
        const data = response.data as TaskResponse;
        if (data.data) {
            dispatch(removeTask(taskId));
        }
        return data;
    } catch (error : ErrorType |any) {
        throw error;
    }
}