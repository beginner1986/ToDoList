import {useEffect, useState} from "react";
import TaskService from "../service/TaskService";
import CenteredSpinner from "./CenteredSpinner";

export default function TasksComponent() {
    const [tasks, setTasks] = useState([]);
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getTasks();
    }, []);

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
        let newTasks = [...tasks];
        let updatedTask = tasks[taskId-1];

        updatedTask.isDone = !updatedTask.isDone;
        TaskService.updateTask(updatedTask);
        setTasks(newTasks);
    }

    if(loading) {
        return <CenteredSpinner/>;
    }

    if(error) return "Error!";

    const tableRows = tasks.map(task => {
        return (
            <tr key={task.id}>
                <td>{task.description}</td>
                <td className="d-flex justify-content-center">{
                    task.isDone
                        ? <i className="bi bi-check-square text-success" onClick={() => {
                            toggleTaskIsDone(task.id);
                        }}></i>
                        : <i className="bi bi-square text-secondary fw-bold" onClick={() => {
                            toggleTaskIsDone(task.id);
                        }}></i>
                }</td>
                <td>Remove</td>
            </tr>
        );
    });

    return (
        <main className="m-auto w-75 fs-5">
            <h1 className="mt-3">All Tasks</h1>
            <table className="table table-bordered table-hover mt-3">
                <thead>
                    <tr className="text-center">
                        <th>Task description</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {tableRows}
                </tbody>
            </table>
        </main>
    );
}
