import { createMockTasks, render, screen, waitFor } from '../utils/test-utils';
import TaskList from '../../components/home/task-list';
import * as taskService from '../../service/task.service';
import { setTasks } from '../../redux/slices/task-slice';
import { setTotalPages, setCurrentPage, setPageSize, setTotalResults } from '../../redux/slices/pagination-slice';
import { store } from '../../redux/store';
import '@testing-library/jest-dom';

// Mock the task service
jest.mock('../../service/task.service');
const mockedTaskService = taskService as jest.Mocked<typeof taskService>;

describe('TaskList Component', () => {
  const mockTasks = createMockTasks(3);

  beforeEach(() => {
    jest.clearAllMocks();
    store.dispatch(setTasks([]));
    store.dispatch(setTotalResults(0));
    store.dispatch(setTotalPages(0));
    store.dispatch(setCurrentPage(1));
    store.dispatch(setPageSize(5));
  });

  it('displays loading skeleton initially', () => {
    mockedTaskService.getAllTasks.mockImplementation(
      () => new Promise(resolve => setTimeout(resolve, 100))
    );

    render(<TaskList />);
    
    const skeletons = document.querySelectorAll('.card.animate-pulse');
    expect(skeletons).toHaveLength(3);
  });
});
