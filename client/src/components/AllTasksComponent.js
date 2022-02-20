import { useAxios } from 'use-axios-client';
import {apiUrl} from "../const/const";
import axios from "axios";
import {useCallback, useState} from "react";
import TaskService from "../service/TaskService";

export default function AllTasksComponent() {
    const { data, error, loading } = useAxios({
        url: apiUrl
    });

    const [, updateState] = useState();
    const forceUpdate = useCallback(() => updateState({}), []);

    if(loading || !data) {
        return (
            <div className="align-items-center d-flex" style={{height: 90+'vh'}}>
                <div className="spinner-border m-5 d-flex justify-content-center">
                    <span className="visually-hidden">Loading...</span>
                </div>
            </div>
        );
    }

    if(error) return "Error!";

    const tableRows = data.map(task => {
        return (
            <tr key={task.id}>
                <td>{task.description}</td>
                <td className="d-flex justify-content-center">{
                    task.isDone
                        ? <i className="bi bi-check-square text-success" onClick={() => {
                            toggleTaskIsDone(task);
                            forceUpdate();
                        }}></i>
                        : <i className="bi bi-square text-secondary fw-bold" onClick={() => {
                            toggleTaskIsDone(task);
                            forceUpdate();
                        }}></i>
                }</td>
                <td>Remove</td>
            </tr>
        );
    });

    return (
        <div className="m-auto w-75 fs-5">
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
        </div>
    );
}

function toggleTaskIsDone(task) {
    task.isDone = !task.isDone;
    TaskService.updateTask(task);
}