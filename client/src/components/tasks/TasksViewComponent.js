import {useState} from "react";
import InputTaskDescriptionTd from "./InputTaskDescriptionTd";

export default function TasksViewComponent(props) {
    const [editable, setEditable] = useState(0);

    const editCell = (taskId) => {
        setEditable(taskId);
    };

    const editTaskDescription = (taskId, text) => {
        props.updateTaskDescription(taskId, text);
        setEditable(-1);
        document
            .querySelectorAll(".edit-button")
            .forEach((btn) => {
                btn.removeAttribute("disabled");
            });
    };

    const tableRows = props.tasks.map(task => {
        return (
            <tr key={task.id}>
                <td onDoubleClick={() => editCell(task.id)}>
                    {
                        (editable === task.id)
                            ? (<InputTaskDescriptionTd
                                task={task}
                                editTaskDescription={editTaskDescription}
                            />)
                            : (<p className={task.isDone ? "text-decoration-line-through text-secondary" : ""}>{task.description}</p>)
                    }
                </td>
                <td className="text-center">
                    {
                        task.isDone
                            ? <i className="bi bi-check-square text-success" onClick={() => {
                                props.toggleTaskIsDone(task.id);
                            }}></i>
                            : <i className="bi bi-square text-secondary fw-bold" onClick={() => {
                                props.toggleTaskIsDone(task.id);
                            }}></i>
                    }
                </td>
                <td className="d-flex justify-content-around">
                    <button type="button" className="btn btn-primary edit-button" onClick={() => editCell(task.id)}>
                        Edit
                    </button>
                    <button type="button" className="btn btn-danger" onClick={() => props.deleteTask(task.id)}>
                        Delete
                    </button>
                </td>
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