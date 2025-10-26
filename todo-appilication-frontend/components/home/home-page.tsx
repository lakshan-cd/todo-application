'use client';
import TaskForm from "./task-form";
import TaskList from './task-list';

export default function HomePage() {
  return (
    <main className="min-h-screen bg-gradient-to-br from-gray-50 to-gray-100 dark:from-slate-900 dark:to-slate-800 p-4">
      <div className="max-w-8xl mx-auto">
        <div className="grid grid-cols-1 xl:grid-cols-2 gap-8">
          {/* Left side - Create Task Form */}
          <div className="space-y-6">
            <div className="animate-slide-in">
              <TaskForm />
            </div>
          </div>
          
          {/* Right side - Task List */}
          <div className="animate-slide-in" style={{ animationDelay: '0.1s' }}>
            <TaskList
              isLoading={false}
            />
          </div>
        </div>
      </div>
    </main>
  );
}