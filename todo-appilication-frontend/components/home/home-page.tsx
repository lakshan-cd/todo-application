'use client';
import { useState } from 'react';
import TaskForm from "./task-form";
import TaskList from "./task-list";
import { Task, CreateTaskRequest } from '@/types/task-types';

// Sample data
const sampleTasks: Task[] = [
  {
    id: 1,
    title: "Complete project proposal",
    description: "Write and submit the project proposal for the new client by end of week",
    completed: false,
    createdAt: "2024-01-15T10:30:00Z",
    updatedAt: "2024-01-15T10:30:00Z"
  },
  {
    id: 2,
    title: "Review team performance",
    description: "Analyze Q1 performance metrics and prepare feedback for team members",
    completed: false,
    createdAt: "2024-01-14T14:20:00Z",
    updatedAt: "2024-01-14T14:20:00Z"
  },
  {
    id: 3,
    title: "Update documentation",
    description: "Update API documentation with latest changes and new endpoints",
    completed: false,
    createdAt: "2024-01-13T09:15:00Z",
    updatedAt: "2024-01-13T09:15:00Z"
  },
  {
    id: 4,
    title: "Schedule team meeting",
    description: "Plan and schedule the weekly team standup for next Monday",
    completed: false,
    createdAt: "2024-01-12T16:45:00Z",
    updatedAt: "2024-01-12T16:45:00Z"
  },
  {
    id: 5,
    title: "Prepare presentation",
    description: "Create slides for the quarterly business review presentation",
    completed: false,
    createdAt: "2024-01-11T11:30:00Z",
    updatedAt: "2024-01-11T11:30:00Z"
  }
];

export default function HomePage() {
  const [tasks, setTasks] = useState<Task[]>(sampleTasks);
  const [isLoading, setIsLoading] = useState(false);

  const handleCreateTask = async (taskData: CreateTaskRequest) => {
    setIsLoading(true);
    
    // Simulate API call
    await new Promise(resolve => setTimeout(resolve, 1000));
    
    const newTask: Task = {
      id: Math.max(...tasks.map(t => t.id), 0) + 1,
      title: taskData.title,
      description: taskData.description,
      completed: false,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    };
    
    setTasks(prev => [newTask, ...prev]);
    setIsLoading(false);
  };

  const handleCompleteTask = (taskId: number) => {
    setTasks(prev => prev.filter(task => task.id !== taskId));
  };

  const handleDeleteTask = (taskId: number) => {
    setTasks(prev => prev.filter(task => task.id !== taskId));
  };

  return (
    <main className="min-h-screen bg-gradient-to-br from-gray-50 to-gray-100 dark:from-slate-900 dark:to-slate-800 p-4">
      <div className="max-w-8xl mx-auto">
        <div className="grid grid-cols-1 xl:grid-cols-2 gap-8">
          {/* Left side - Create Task Form */}
          <div className="space-y-6">
            <div className="animate-slide-in">
              <TaskForm onSubmit={handleCreateTask} isLoading={isLoading} />
            </div>
          </div>
          
          {/* Right side - Task List */}
          <div className="animate-slide-in" style={{ animationDelay: '0.1s' }}>
            <TaskList
              tasks={tasks}
              onCompleteTask={handleCompleteTask}
              onDeleteTask={handleDeleteTask}
              isLoading={false}
            />
          </div>
        </div>
      </div>
    </main>
  );
}