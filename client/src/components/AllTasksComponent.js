import { useAxios } from 'use-axios-client';

export default function AllTasksComponent() {
    const { data, error, loading } = useAxios({
        url: 'http://localhost:8080/task'
    });

    if(loading || !data) return "Loading...";
    if(error) return "Error!";

    const tasks = data.map(task => {
        return (
            <li key={task.id}>{task.description} - {task.isDone.toString()}</li>
        );
    })

    return (
        <div>
            <div>All Tasks</div>
            <ul>
                {tasks}
            </ul>
        </div>
    );
}