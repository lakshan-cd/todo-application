export interface Task {
  uid: number;
  title: string;
  description: string;
  statusId: number;
  statusDescription: string;
  isActive: boolean;
  createdDate: Date;
  createdBy: string;
  modifiedDate: Date;
  modifiedBy: string;
  }
  
  export interface CreateTaskRequest {
    title: string;
    description: string;
  }

  export type PaginationInfo = {
    currentPage: number;
    totalPages: number;
    totalResults: number;
    pageSize: number;
  };
  
  
  export type BaseResponse = {
    code: string;
    message: string;
  };

  export type Result = {
    result: Task[] | Task;
    paginationInfo: PaginationInfo;
  };
  export interface TaskResponse extends BaseResponse {
    data: Result;
  }

  export interface TaskState {
    tasks: Task[];
    task: Task | null;
  }