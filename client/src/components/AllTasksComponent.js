import { useAxios } from 'use-axios-client';

export default function AllTasksComponent() {
    const { data, error, loading } = useAxios({
        url: 'http://localhost:8080/task'
    });

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

    const tasks = data.map(task => {
        return (
            <tr key={task.id}>
                <td>{task.description}</td>
                <td>{task.isDone.toString()}</td>
            </tr>
        );
    });

    return (
        <div className="m-auto w-75">
            <h1 className="mt-3">All Tasks</h1>
            <table className="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>Task description</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {tasks}
                </tbody>
            </table>
        </div>
    );
}