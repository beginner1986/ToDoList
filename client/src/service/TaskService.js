import axios from "axios";
import {apiUrl} from "../const/const";

class TaskService {

    updateTask(task) {
        axios
            .patch(`${apiUrl}/${task.id}`, task)
            .catch(error => {
                console.log(error);
            });
    }
}

export default new TaskService();