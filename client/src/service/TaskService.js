import axios from "axios";
import {apiUrl} from "../const/const";

class TaskService {

    getAllTasks() {
        return axios.get(apiUrl);
    }

    updateTask(task) {
        axios.patch(`${apiUrl}/${task.id}`, task)
            .catch(error => {
                console.log(error);
            });
    }

    deleteTask(taskId) {
        axios.delete(`${apiUrl}/${taskId}`)
            .catch(error => {
                console.log(error);
            });
    }
}

export default new TaskService();