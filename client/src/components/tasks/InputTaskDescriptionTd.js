import {useEffect, useState} from "react";

export default function InputTaskDescriptionTd(props) {
    const [text, setText] = useState(props.task.description);

    useEffect(() => {
        document
            .querySelectorAll(".edit-button")
            .forEach((btn) => {
                btn.setAttribute("disabled", "");
            });
    }, []);

    const update = (event) => {
        setText(event.target.value);
    }

    return (
        <span className="d-flex">
            <input
                type="text"
                value={text}
                onChange={update}
                className="form-control"
            />
            <input
                type="submit"
                value="Save"
                className="btn btn-success"
                onClick={() => props.editTaskDescription(props.task.id, text)}
            />
        </span>
    );
}