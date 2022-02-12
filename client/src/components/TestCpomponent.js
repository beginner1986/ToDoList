import React, {useEffect, useState} from "react";
import axios from "axios";

export default function TestComponent() {

    const [message, setMessage] = useState();

    useEffect(() => {
        axios.get("http://localhost:8080/test")
            .then(function(result) {
                console.log(result.data);
                setMessage(result.data);
            })
            .catch(function(error) {
                console.log(error);
            });
    }, []);

    return (
        <h1>{message}</h1>
    );
}