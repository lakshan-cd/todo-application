import TaskCard from '../../components/home/task-card';
import * as taskService from '../../service/task.service';
import { createMockTask, fireEvent, render, screen, waitFor } from '../utils/test-utils';
import '@testing-library/jest-dom';

// Mock the task service
jest.mock('../../service/task.service');
const mockedTaskService = taskService as jest.Mocked<typeof taskService>;

describe('TaskCard Component', () => {
  const mockTask = createMockTask();
  const mockSetDeletingTaskId = jest.fn();

  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders task information correctly', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    expect(screen.getByText(mockTask.title)).toBeInTheDocument();
    expect(screen.getByText(mockTask.description)).toBeInTheDocument();
    expect(screen.getByText(mockTask.statusDescription)).toBeInTheDocument();
  });

  it('displays correct status badge for pending task', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const statusBadge = screen.getByText(mockTask.statusDescription);
    expect(statusBadge).toHaveClass('bg-gradient-to-r', 'from-[#25CCF7]', 'to-[#1B9CFC]');
  });

  it('displays correct status badge for completed task', () => {
    const completedTask = createMockTask({
      statusId: 20,
      statusDescription: 'Completed',
    });
    
    render(
      <TaskCard
        task={completedTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const statusBadge = screen.getByText('Completed');
    expect(statusBadge).toHaveClass('bg-gradient-to-r', 'from-[#25CCF7]', 'to-[#1B9CFC]');
  });

  it('shows action buttons for pending task', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    expect(screen.getByRole('button', { name: /mark.*as completed/i })).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /delete/i })).toBeInTheDocument();
  });

  it('handles complete task action', async () => {
    mockedTaskService.completeTask.mockResolvedValueOnce({
      data: {
        result: mockTask,
        paginationInfo: {
          currentPage: 1,
          totalPages: 1,
          totalResults: 1,
          pageSize: 10,
        },
      },
      code: 'SUCCESS',
      message: 'Task completed successfully',
    });

    mockedTaskService.getAllTasks.mockResolvedValueOnce([]);

    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const completeButton = screen.getByRole('button', { name: /mark.*as completed/i });
    fireEvent.click(completeButton);
    
    await waitFor(() => {
      expect(mockedTaskService.completeTask).toHaveBeenCalledWith(mockTask.uid, {
        statusId: 20,
      });
    });
  });

  it('shows confirmation dialog for delete action', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const deleteButton = screen.getByRole('button', { name: /delete/i });
    fireEvent.click(deleteButton);
    
    expect(screen.getByRole('button', { name: /confirm/i })).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /cancel/i })).toBeInTheDocument();
  });

  it('handles delete confirmation', async () => {
    mockedTaskService.deleteTask.mockResolvedValueOnce({
      data: {
        result: mockTask,
        paginationInfo: {
          currentPage: 1,
          totalPages: 1,
          totalResults: 1,
          pageSize: 10,
        },
      },
      code: 'SUCCESS',
      message: 'Task deleted successfully',
    });

    mockedTaskService.getAllTasks.mockResolvedValueOnce([]);

    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const deleteButton = screen.getByRole('button', { name: /delete/i });
    fireEvent.click(deleteButton);
    
    const confirmButton = screen.getByRole('button', { name: /confirm/i });
    fireEvent.click(confirmButton);
    
    await waitFor(() => {
      expect(mockedTaskService.deleteTask).toHaveBeenCalledWith(mockTask.uid, {
        isActive: false,
      });
      expect(mockSetDeletingTaskId).toHaveBeenCalledWith(mockTask.uid);
    });
  });

  it('handles delete cancellation', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const deleteButton = screen.getByRole('button', { name: /delete/i });
    fireEvent.click(deleteButton);
    
    const cancelButton = screen.getByRole('button', { name: /cancel/i });
    fireEvent.click(cancelButton);
    
    expect(screen.queryByRole('button', { name: /confirm/i })).not.toBeInTheDocument();
    expect(mockSetDeletingTaskId).not.toHaveBeenCalled();
  });

  it('shows loading state during complete action', async () => {
    mockedTaskService.completeTask.mockImplementation(
      () => new Promise(resolve => setTimeout(resolve, 100))
    );

    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const completeButton = screen.getByRole('button', { name: /mark.*as completed/i });
    fireEvent.click(completeButton);
    
    expect(screen.getByText(/completing/i)).toBeInTheDocument();
    expect(completeButton).toBeDisabled();
    
    await waitFor(() => {
      expect(completeButton).not.toBeDisabled();
    });
  });

  it('handles API errors gracefully', async () => {
    mockedTaskService.completeTask.mockRejectedValueOnce({
      response: {
        data: {
          code: 'ERROR',
          message: 'Failed to complete task',
        },
      },
    });

    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const completeButton = screen.getByRole('button', { name: /mark.*as completed/i });
    fireEvent.click(completeButton);
    
    await waitFor(() => {
      expect(completeButton).not.toBeDisabled();
    });
  });

  it('displays task creation date', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    // Check if date is displayed (format may vary)
    expect(screen.getByText(/2024/i)).toBeInTheDocument();
  });

  it('has proper accessibility attributes', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const completeButton = screen.getByRole('button', { name: /mark.*as completed/i });
    const deleteButton = screen.getByRole('button', { name: /delete/i });
    
    expect(completeButton).toHaveAttribute('aria-label');
    expect(deleteButton).toHaveAttribute('aria-label');
  });

  it('handles long task titles with proper wrapping', () => {
    const longTitleTask = createMockTask({
      title: 'This is a very long task title that should wrap to multiple lines and test the text wrapping functionality',
    });
    
    render(
      <TaskCard
        task={longTitleTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    const titleElement = screen.getByText(longTitleTask.title);
    expect(titleElement).toHaveClass('break-all', 'leading-tight');
  });

  it('applies correct CSS classes for animations', () => {
    render(
      <TaskCard
        task={mockTask}
        setDeletingTaskId={mockSetDeletingTaskId}
      />
    );
    
    // The component uses a div with card class, not an article
    const cardElement = screen.getByText(mockTask.title).closest('.card');
    expect(cardElement).toHaveClass('card', 'transition-all', 'duration-300');
  });
});
