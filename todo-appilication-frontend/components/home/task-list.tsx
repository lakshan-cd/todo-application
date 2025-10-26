'use client';

import { Task } from '@/types/task-types';
import TaskCard from './task-card';
import { useState } from 'react';

interface TaskListProps {
  tasks: Task[];
  onCompleteTask: (taskId: number) => void;
  onDeleteTask: (taskId: number) => void;
  isLoading: boolean;
}

const TaskList: React.FC<TaskListProps> = ({ tasks, onCompleteTask, onDeleteTask, isLoading }) => {
  const [deletingTaskId, setDeletingTaskId] = useState<number | null>(null);
  const [completingTaskId, setCompletingTaskId] = useState<number | null>(null);

  const handleComplete = async (taskId: number) => {
    setCompletingTaskId(taskId);
    // Simulate API call delay
    await new Promise(resolve => setTimeout(resolve, 500));
    onCompleteTask(taskId);
    setCompletingTaskId(null);
  };

  const handleDelete = async (taskId: number) => {
    setDeletingTaskId(taskId);
    // Simulate API call delay
    await new Promise(resolve => setTimeout(resolve, 300));
    onDeleteTask(taskId);
    setDeletingTaskId(null);
  };

  if (isLoading) {
    return (
      <div className="space-y-4">
        {[...Array(3)].map((_, index) => (
          <div key={index} className="card animate-pulse">
            <div className="h-4 bg-gray-200 dark:bg-slate-700 rounded w-3/4 mb-3"></div>
            <div className="h-3 bg-gray-200 dark:bg-slate-700 rounded w-1/2 mb-4"></div>
            <div className="flex justify-between items-center">
              <div className="h-3 bg-gray-200 dark:bg-slate-700 rounded w-1/4"></div>
              <div className="h-6 bg-gray-200 dark:bg-slate-700 rounded w-16"></div>
            </div>
          </div>
        ))}
      </div>
    );
  }

  if (tasks.length === 0) {
    return (
      <div className="card text-center py-12">
        <div className="w-16 h-16 bg-gradient-to-r from-[#25CCF7] to-[#1B9CFC] rounded-full flex items-center justify-center mx-auto mb-4">
          <svg className="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </div>
        <h3 className="text-lg font-semibold text-gray-700 dark:text-gray-300 mb-2">No tasks yet</h3>
        <p className="text-gray-500 dark:text-gray-400">Create your first task to get started!</p>
      </div>
    );
  }

  return (
    <div className="space-y-4">
      <div className="flex items-center justify-between mb-6">
        <h2 className="text-xl font-semibold text-gray-800 dark:text-gray-200">Your Tasks</h2>
        <span className="text-sm text-gray-500 dark:text-gray-400 bg-gray-100 dark:bg-slate-700 px-3 py-1 rounded-full">
          {tasks.length} task{tasks.length !== 1 ? 's' : ''}
        </span>
      </div>
      
      <div className="space-y-4">
        {tasks.map((task) => (
          <div
            key={task.id}
            className={`transition-all duration-300 ${
              deletingTaskId === task.id 
                ? 'animate-slide-out opacity-0' 
                : 'animate-fade-in'
            }`}
          >
            <TaskCard
              task={task}
              onComplete={() => handleComplete(task.id)}
              onDelete={() => handleDelete(task.id)}
              isCompleting={completingTaskId === task.id}
              isDeleting={deletingTaskId === task.id}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default TaskList;
