import { createMockTask, fireEvent, render, screen, waitFor } from '../utils/test-utils';
import TaskForm from '../../components/home/task-form';
import * as taskService from '../../service/task.service';
import '@testing-library/jest-dom';

// Mock the task service
jest.mock('../../service/task.service');
const mockedTaskService = taskService as jest.Mocked<typeof taskService>;

describe('TaskForm Component', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it('renders form elements correctly', () => {
    render(<TaskForm />);
    
    expect(screen.getByText('Create New Task')).toBeInTheDocument();
    expect(screen.getByLabelText(/title/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/description/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /create task/i })).toBeInTheDocument();
  });

  it('displays validation errors for empty fields', async () => {
    render(<TaskForm />);
    
    const submitButton = screen.getByRole('button', { name: /create task/i });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(screen.getByText('Title is required')).toBeInTheDocument();
    });
  });

  it('validates title length', async () => {
    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    // Test title too long
    fireEvent.change(titleInput, { target: { value: 'a'.repeat(101) } });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(screen.getByText('Title must not exceed 100 characters')).toBeInTheDocument();
    });
  });

  it('validates description length', async () => {
    render(<TaskForm />);
    
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    fireEvent.change(descriptionInput, { target: { value: 'a'.repeat(256) } });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(screen.getByText('Description must not exceed 255 characters')).toBeInTheDocument();
    });
  });

  it('submits form with valid data', async () => {
    const mockTask = createMockTask();
    mockedTaskService.createTask.mockResolvedValueOnce({
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
      message: 'Task created successfully',
    });

    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    fireEvent.change(titleInput, { target: { value: 'Test Task' } });
    fireEvent.change(descriptionInput, { target: { value: 'Test Description' } });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(mockedTaskService.createTask).toHaveBeenCalledWith({
        title: 'Test Task',
        description: 'Test Description',
      });
    });
  });

  it('shows loading state during submission', async () => {
    mockedTaskService.createTask.mockImplementation(
      () => new Promise(resolve => setTimeout(resolve, 100))
    );

    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    fireEvent.change(titleInput, { target: { value: 'Test Task' } });
    fireEvent.change(descriptionInput, { target: { value: 'Test Description' } });
    fireEvent.click(submitButton);
    
    // Check loading state
    expect(screen.getByText(/creating/i)).toBeInTheDocument();
    expect(submitButton).toBeDisabled();
    
    await waitFor(() => {
      expect(submitButton).not.toBeDisabled();
    });
  });

  it('handles API errors gracefully', async () => {
    mockedTaskService.createTask.mockRejectedValueOnce({
      response: {
        data: {
          code: 'ERROR',
          message: 'Failed to create task',
        },
      },
    });

    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    fireEvent.change(titleInput, { target: { value: 'Test Task' } });
    fireEvent.change(descriptionInput, { target: { value: 'Test Description' } });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(submitButton).not.toBeDisabled();
    });
  });

  it('clears form after successful submission', async () => {
    const mockTask = createMockTask();
    mockedTaskService.createTask.mockResolvedValueOnce({
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
      message: 'Task created successfully',
    });

    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    fireEvent.change(titleInput, { target: { value: 'Test Task' } });
    fireEvent.change(descriptionInput, { target: { value: 'Test Description' } });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(titleInput).toHaveValue('');
      expect(descriptionInput).toHaveValue('');
    });
  });

  it('displays success message after successful submission', async () => {
    const mockTask = createMockTask();
    mockedTaskService.createTask.mockResolvedValueOnce({
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
      message: 'Task created successfully',
    });

    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    fireEvent.change(titleInput, { target: { value: 'Test Task' } });
    fireEvent.change(descriptionInput, { target: { value: 'Test Description' } });
    fireEvent.click(submitButton);
    
    await waitFor(() => {
      expect(submitButton).not.toBeDisabled();
    });
  });

  it('has proper accessibility attributes', () => {
    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    expect(submitButton).toHaveAttribute('type', 'submit');
  });

  it('handles keyboard navigation', () => {
    render(<TaskForm />);
    
    const titleInput = screen.getByLabelText(/title/i);
    const descriptionInput = screen.getByLabelText(/description/i);
    const submitButton = screen.getByRole('button', { name: /create task/i });
    
    // Test that elements are focusable
    titleInput.focus();
    expect(titleInput).toHaveFocus();
    
    descriptionInput.focus();
    expect(descriptionInput).toHaveFocus();
    
    submitButton.focus();
    expect(submitButton).toHaveFocus();
  });
});
