import {useEffect, useState} from "react";
import TaskService from "../service/TaskService";
import CenteredSpinner from "./CenteredSpinner";
import TasksViewComponent from "./TasksViewComponent";

export default function TasksComponent() {
    const [tasks, setTasks] = useState([]);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getTasks();
    }, [tasks]);

    const getTasks = () => {
        TaskService.getAllTasks()
            .then((response) => {
                setTasks(response.data);
                setLoading(false);
            })
            .catch(error => {
                setError(error);
                setLoading(false);
            });
    };

    const toggleTaskIsDone = (taskId) => {
        let updatedTask = tasks.find((task) => {
            return task.id === taskId;
        });
        updatedTask.isDone = !updatedTask.isDone;
        TaskService.updateTask(updatedTask);
    }

    const updateTaskDescription = (taskId, newDescription) => {
        let updatedTask = tasks.find((task) => {
            return task.id === taskId;
        });
        updatedTask.description = newDescription;
        TaskService.updateTask(updatedTask);
    };

    const deleteTask = (taskId) => {
        TaskService.deleteTask(taskId);
    }

    if(loading) {
        return <CenteredSpinner/>;
    }

    if(error) return "Error!";

    return <TasksViewComponent
        tasks={tasks}
        toggleTaskIsDone={toggleTaskIsDone}
        updateTaskDescription={updateTaskDescription}
        deleteTask={deleteTask}
    />
}
