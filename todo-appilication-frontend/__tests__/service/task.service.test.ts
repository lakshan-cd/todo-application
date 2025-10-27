import * as taskService from '../../service/task.service';
import { createMockTask } from '../utils/test-utils';
import axiosInstance from '../../utils/axios';

// Mock axios module
jest.mock('../../utils/axios');

jest.mock('../../redux/store', () => ({
  dispatch: jest.fn(),
}));

describe('Task Service', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  describe('createTask', () => {
    it('creates task successfully', async () => {
      const mockTask = createMockTask();
      const mockTaskResponse = {
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
      };
      
      (axiosInstance.post as jest.Mock).mockResolvedValueOnce({
        data: mockTaskResponse
      });

      const result = await taskService.createTask({
        title: 'Test Task',
        description: 'Test Description',
      });

      expect(axiosInstance.post).toHaveBeenCalled();
      expect(result).toEqual(mockTaskResponse);
    });

    it('handles create task error', async () => {
      const mockError = new Error('Failed to create task');
      (mockError as any).response = {
        data: {
          error: 'Failed to create task',
          exceptionCode: '400',
          status: '400',
        }
      };
      (axiosInstance.post as jest.Mock).mockRejectedValueOnce(mockError);

      await expect(
        taskService.createTask({
          title: 'Test Task',
          description: 'Test Description',
        })
      ).rejects.toThrow();
    });
  });

  describe('getAllTasks', () => {
    it('fetches all tasks successfully', async () => {
      const mockTasks = [createMockTask()];
      const mockTaskResponse = {
        data: {
          result: mockTasks,
          paginationInfo: {
            currentPage: 1,
            totalPages: 1,
            totalResults: 1,
            pageSize: 10,
          },
        },
        code: 'SUCCESS',
        message: 'Success',
      };
      
      (axiosInstance.get as jest.Mock).mockResolvedValueOnce({
        data: mockTaskResponse
      });

      const result = await taskService.getAllTasks([
        'page=1',
        'pageSize=10',
        'sortDirection=desc',
        'statusId=10',
        'isActive=true',
      ]);

      expect(axiosInstance.get as jest.Mock).toHaveBeenCalled();
      expect(result).toEqual(mockTasks);
    });

    it('handles fetch tasks error', async () => {
      const mockError = new Error('Failed to fetch tasks');
      (mockError as any).response = {
        data: {
          error: 'Failed to fetch tasks',
          exceptionCode: '400',
          status: '400',
        }
      };
      (axiosInstance.get as jest.Mock).mockRejectedValueOnce(mockError);

      await expect(
        taskService.getAllTasks(['page=1', 'pageSize=10'])
      ).rejects.toThrow();
    });
  });

  describe('completeTask', () => {
    it('completes task successfully', async () => {
      const mockTask = createMockTask({ statusId: 20, statusDescription: 'Completed' });
      const mockTaskResponse = {
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
      };
      
      (axiosInstance.patch as jest.Mock).mockResolvedValueOnce({
        data: mockTaskResponse
      });

      const result = await taskService.completeTask(1, { statusId: 20 });

      expect(axiosInstance.patch as jest.Mock).toHaveBeenCalled();
      expect(result).toEqual(mockTaskResponse);
    });

    it('handles complete task error', async () => {
      const mockError = new Error('Failed to complete task');
      (mockError as any).response = {
        data: {
          error: 'Failed to complete task',
          exceptionCode: '400',
          status: '400',
        }
      };
      (axiosInstance.patch as jest.Mock).mockRejectedValueOnce(mockError);

      await expect(
        taskService.completeTask(1, { statusId: 20 })
      ).rejects.toThrow();
    });
  });

  describe('deleteTask', () => {
    it('deletes task successfully', async () => {
      const mockTask = createMockTask({ isActive: false });
      const mockTaskResponse = {
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
      };
      
      (axiosInstance.patch as jest.Mock).mockResolvedValueOnce({
        data: mockTaskResponse
      });

      const result = await taskService.deleteTask(1, { isActive: false });

      expect(axiosInstance.patch as jest.Mock).toHaveBeenCalled();
      expect(result).toEqual(mockTaskResponse);
    });

    it('handles delete task error', async () => {
      const mockError = new Error('Failed to delete task');
      (mockError as any).response = {
        data: {
          error: 'Failed to delete task',
          exceptionCode: '400',
          status: '400',
        }
      };
      (axiosInstance.patch as jest.Mock).mockRejectedValueOnce(mockError);

      await expect(
        taskService.deleteTask(1, { isActive: false })
      ).rejects.toThrow();
    });
  });
});
