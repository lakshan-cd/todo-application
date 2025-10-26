import { Task } from '@/types/task-types';
import React, { useState } from 'react';

interface TaskCardProps {
  task: Task;
  onComplete: (taskId: number) => void;
  onDelete: (taskId: number) => void;
  isCompleting?: boolean;
  isDeleting?: boolean;
}

const TaskCard: React.FC<TaskCardProps> = ({ 
  task, 
  onComplete, 
  onDelete, 
  isCompleting = false, 
  isDeleting = false 
}) => {
  const [showDeleteConfirm, setShowDeleteConfirm] = useState(false);

  const handleComplete = () => {
    onComplete(task.id);
  };

  const handleDelete = () => {
    onDelete(task.id);
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
      <div className="flex justify-between items-start mb-4">
        <h3 className="text-lg font-semibold text-gray-900 dark:text-gray-100 flex-1 mr-4">
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
        <span>Created: {new Date(task.createdAt).toLocaleDateString()}</span>
        <span className="px-2 py-1 bg-gradient-to-r from-[#25CCF7] to-[#1B9CFC] text-white rounded-full text-xs font-medium">
          Pending
        </span>
      </div>
    </div>
  );
};

export default TaskCard;
