import { RootState, useSelector } from '@/redux/store';
import { completeTask, deleteTask, getAllTasks } from '@/service/task.service';
import { ErrorType } from '@/types/common';
import { Task } from '@/types/task-types';
import { enqueueSnackbar } from 'notistack';
import React, { useState } from 'react';

interface TaskCardProps {
  task: Task;
  setDeletingTaskId: (taskId: number) => void;
}

const TaskCard = ({ task, setDeletingTaskId }: TaskCardProps) => {
  const [showDeleteConfirm, setShowDeleteConfirm] = useState(false);
  const currentPage = useSelector((state: RootState) => state.pagination.currentPage);
  const pageSize = useSelector((state: RootState) => state.pagination.pageSize);
  const [isCompleting, setIsCompleting] = useState(false);
  const [isDeleting, setIsDeleting] = useState(false);

  const handleComplete = async () => {
    try {
      setIsCompleting(true);
      const obj = {
        statusId: 20,
      };
      const response = await completeTask(task.uid, obj);
      await getAllTasks([`page=${currentPage}`, `pageSize=${pageSize}`, `sortDirection=${'desc'}`, `statusId=${10}`, `isActive=${true}`]);
      enqueueSnackbar(response.message, { variant: 'success' });
      setIsCompleting(false);
    } catch (error:ErrorType | any) {
      setIsCompleting(false);
      enqueueSnackbar(error.message, { variant: 'error' });
    }
  };

  const handleDelete = async () => {
    setIsDeleting(true);
    try {
      const obj = {
        isActive: false,
      };
      const response = await deleteTask(task.uid, obj);
      setDeletingTaskId(task.uid);
      await getAllTasks([`page=${currentPage}`, `pageSize=${pageSize}`, `sortDirection=${'desc'}`, `statusId=${10}`, `isActive=${true}`]);
      enqueueSnackbar(response.message, { variant: 'success' });
      setIsDeleting(false);
     } catch (error:ErrorType | any) {
       setIsDeleting(false);
       enqueueSnackbar(error.message, { variant: 'error' });
     }
   };

  const handleDeleteConfirm = () => {
    setShowDeleteConfirm(true);
  };

  const handleDeleteCancel = () => {
    setShowDeleteConfirm(false);
  };

  return (
    <div className={`card transition-all duration-300 hover:shadow-xl hover:scale-[1.02] ${
      isDeleting ? 'opacity-50 pointer-events-none' : ''
    }`}>
      <div className="flex justify-between items-start mb-4 gap-4">
        <h3 className="text-lg font-semibold text-gray-900 dark:text-gray-100 flex-1 break-all leading-tight">
          {task.title}
        </h3>
        <div className="flex items-center space-x-2 flex-shrink-0">
          {!showDeleteConfirm ? (
            <>
              <button
                onClick={handleComplete}
                disabled={isCompleting || isDeleting}
                className={`btn-primary text-sm px-3 py-1.5 transition-all duration-300 ${
                  isCompleting ? 'opacity-50 cursor-not-allowed' : 'hover:scale-105'
                }`}
                aria-label={`Mark "${task.title}" as completed`}
              >
                {isCompleting ? (
                  <div className="flex items-center space-x-1">
                    <div className="w-3 h-3 border-2 border-white border-t-transparent rounded-full animate-spin"></div>
                    <span>Completing...</span>
                  </div>
                ) : (
                  'Done'
                )}
              </button>
              <button
                onClick={handleDeleteConfirm}
                disabled={isCompleting || isDeleting}
                className="p-2 text-gray-400 hover:text-red-500 hover:bg-red-50 dark:hover:bg-red-900/20 rounded-lg transition-all duration-300 transform hover:scale-105"
                aria-label={`Delete "${task.title}"`}
              >
                <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                </svg>
              </button>
            </>
          ) : (
            <div className="flex items-center space-x-2 animate-scale-in">
              <button
                onClick={handleDelete}
                className="btn-danger text-sm px-3 py-1.5"
              >
                Confirm
              </button>
              <button
                onClick={handleDeleteCancel}
                className="px-3 py-1.5 text-sm text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-200 transition-colors duration-300"
              >
                Cancel
              </button>
            </div>
          )}
        </div>
      </div>
      
      {task.description && (
        <p className="text-gray-600 dark:text-gray-300 mb-4 leading-relaxed">
          {task.description}
        </p>
      )}
      
      <div className="flex justify-between items-center text-sm text-gray-500 dark:text-gray-400">
        <span>Created: {new Date(task.createdDate).toLocaleDateString()}</span>
        {task.statusId == 10 && (
          <span className="px-2 py-1 bg-gradient-to-r from-[#25CCF7] to-[#1B9CFC] text-white rounded-full text-xs font-medium">
            Pending
          </span>
        )}
        {task.statusId == 20 && (
          <span className="px-2 py-1 bg-gradient-to-r from-[#25CCF7] to-[#1B9CFC] text-white rounded-full text-xs font-medium">
            Completed
          </span>
        )}
      </div>
    </div>
  );
};

export default TaskCard;
