import { useAxios } from 'use-axios-client';

export default function AllTasksComponent() {
    const { data, error, loading } = useAxios({
        url: 'http://localhost:8080/task'
    });

    if(loading || !data) {
        return (
            <div style={{display: 'flex', height: 90 + 'vh'}} className="align-items-center">
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
    })

    return (
        <div>
            <h1>All Tasks</h1>
            <table>
                <thead>
                    <tr>
                        <td>Task description</td>
                        <td>Status</td>
                    </tr>
                </thead>
                <tbody>
                    {tasks}
                </tbody>
            </table>
        </div>
    );
}