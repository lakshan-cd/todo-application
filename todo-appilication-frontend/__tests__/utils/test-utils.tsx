import { render, RenderOptions, screen, fireEvent, waitFor } from '@testing-library/react';
import { Provider } from 'react-redux';
import { configureStore } from '@reduxjs/toolkit';
import { ThemeProvider } from 'next-themes';
import { SnackbarProvider } from 'notistack';
import { ReactElement } from 'react';
import { rootReducer } from '@/redux/rootReducer';
import { store } from '../../redux/store';

// Create a test store
export const createTestStore = (preloadedState = {}) => {
  return configureStore({
    reducer: rootReducer,
    preloadedState,
  });
};

// Custom render function that includes providers
const AllTheProviders = ({ children }: { children: React.ReactNode }) => {
  return (
    <Provider store={store}>
      <ThemeProvider attribute="class" defaultTheme="light" enableSystem={false}>
        <SnackbarProvider maxSnack={3}>
          {children}
        </SnackbarProvider>
      </ThemeProvider>
    </Provider>
  );
};

const customRender = (
  ui: ReactElement,
  options?: Omit<RenderOptions, 'wrapper'>
) => render(ui, { wrapper: AllTheProviders, ...options });

// Re-export everything
export * from '@testing-library/react';
export { customRender as render };

// Test data factories
export const createMockTask = (overrides = {}) => ({
  uid: 1,
  title: 'Test Task',
  description: 'Test Description',
  statusId: 10,
  statusDescription: 'Pending',
  isActive: true,
  createdDate: new Date('2024-01-01T00:00:00Z'),
  createdBy: 'testUser',
  modifiedDate: new Date('2024-01-01T00:00:00Z'),
  modifiedBy: 'testUser',
  ...overrides,
});

export const createMockTasks = (count = 3) => {
  return Array.from({ length: count }, (_, index) => 
    createMockTask({
      uid: index + 1,
      title: `Test Task ${index + 1}`,
      description: `Test Description ${index + 1}`,
      statusId: 10,
      statusDescription: 'Pending',
      isActive: true,
      createdDate: new Date('2024-01-01T00:00:00Z'),
      createdBy: 'testUser',
      modifiedDate: new Date('2024-01-01T00:00:00Z'),
      modifiedBy: 'testUser',
    })
  );
};

export const createMockPaginatedResponse = (tasks: any[] = [], page = 1, pageSize = 10, totalResults = 0) => ({
  result: tasks,
  paginationInfo: {
    currentPage: page,
    totalPages: Math.ceil(totalResults / pageSize),
    totalResults,
    pageSize,
  },
});

// Mock API responses
export const mockApiResponses = {
  success: (data: any) => ({
    data: {
      result: data,
      paginationInfo: {
        currentPage: 1,
        totalPages: 1,
        totalResults: Array.isArray(data) ? data.length : 1,
        pageSize: 10,
      },
    },
    code: 'SUCCESS',
    message: 'Success',
    status: 200,
  }),
  error: (message = 'Error occurred') => ({
    response: {
      data: {
        code: 'ERROR',
        message,
      },
      status: 400,
    },
  }),
};
