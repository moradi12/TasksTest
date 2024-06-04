import React, { useState, useEffect } from "react";
import axios from "axios";
import "./AllTasks.css";

type Task = {
  id: number;
  taskName: string;
  description: string;
  complete: boolean;
};

export function AllTasks(): JSX.Element {
  const [tasks, setTasks] = useState<Task[]>([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/task/tasks")
      .then((response) => {
        setTasks(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  function toggleTask(task: Task) {
    console.log(task.complete);
    const updatedTask = { ...task, complete: !task.complete };
    console.log(updatedTask.complete);
    axios.put(`http://localhost:8080/api/v1/task/${task.id}`, updatedTask)
      .then((response) => {
        setTasks((prevTasks) =>
          prevTasks.map((t) =>
            t.id === task.id ? { ...t, complete: updatedTask.complete } : t
          )
        );
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div className="AllTasks">
      <div className="container">
        <h1>All Tasks</h1>
        <p className="description">Here are all the tasks:</p>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Task Name</th>
              <th>Description</th>
              <th>Is Complete</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {tasks.map((task) => (
              <tr key={task.id}>
                <td>{task.id}</td>
                <td>{task.taskName}</td>
                <td>{task.description}</td>
                <td>{task.complete ? "Yes" : "No"}</td>
                <td>
                  <button onClick={() => toggleTask(task)}>
                    {task.complete ? "Mark as Incomplete" : "Mark as Complete"}
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
