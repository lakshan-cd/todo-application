import { TaskState, Task } from "@/types/task-types";
import { createSlice } from "@reduxjs/toolkit";

const initialState: TaskState = {
    tasks: [],
    task: null,
};

export const taskSlice = createSlice({
    name: "Tasks",
    initialState,
    reducers: {
        setTasks(state, action) {
            state.tasks = action.payload;
        },
        setTask(state, action) {
            state.task = action.payload;
        },
        addNewTask(state, action) {
            const newTask: Task = action.payload;
            
            state.tasks.unshift(newTask);
            
            if (state.tasks.length > 5) {
                const sortedTasks = [...state.tasks].sort((a, b) => 
                    new Date(a.createdDate).getTime() - new Date(b.createdDate).getTime()
                );
                
                const oldestTask = sortedTasks[0];
                state.tasks = state.tasks.filter(task => task.uid !== oldestTask.uid);
            }
        },
        removeTask(state, action) {
            const taskId = action.payload;
            state.tasks = state.tasks.filter(task => task.uid !== taskId);
        },
    },
});

export const {
   setTasks,
   setTask,
   addNewTask,
   removeTask
} = taskSlice.actions;
export default taskSlice.reducer;