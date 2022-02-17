import { useAxios } from 'use-axios-client';

export default function AllTasksComponent() {
    const { data, error, loading } = useAxios({
        url: 'http://localhost:8080/task'
    });

    if(loading || !data) return "Loading...";
    if(error) return "Error!";

    const tasks = data.map(task => {
        return (
            <tr key={task.id}><td>{task.description}</td><td>{task.isDone.toString()}</td></tr>
        );
    })

    return (
        <div>
            <h1>All Tasks</h1>
            <table>
                <thead>
                    <tr>
                        <td>Task description</td><td>Status</td>
                    </tr>
                </thead>
                <tbody>
                    {tasks}
                </tbody>
            </table>
        </div>
    );
}