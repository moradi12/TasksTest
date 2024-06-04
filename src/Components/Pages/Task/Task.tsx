import { useState } from "react";
import ITask from "../../Layout/Model/ITask";
import "./Task.css";

export function Task(props: ITask): JSX.Element {
    const [task, setTask] = useState<ITask>(props);
    
    const toggleIsComplete = () => {
        setTask((prevTask) => ({
            ...prevTask,
            complete: !prevTask.complete
        }));
    }

    return (
        <table className="Task">
            <tr key={task.id}>
                <th>{task.id}</th>
                <th>{task.taskName}</th>
                <th>{task.description}</th>
                <th>
                    <button onClick={toggleIsComplete}>
                        {task.complete ? "Yes" : "No"}
                    </button>
                </th>
            </tr>
        </table>
    );
}
