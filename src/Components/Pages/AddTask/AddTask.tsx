import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./AddTask.css"; 

export const AddTask: React.FC = () => {
  const navigate = useNavigate();
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [submitMessage, setSubmitMessage] = useState("");
  const [submitError, setSubmitError] = useState("");

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/v1/task/tasks", {
        taskName: name,
        description: description,
      })
      .then(() => {
        setSubmitMessage("Task added successfully.");
        setSubmitError("");
        navigate("/");
      })
      .catch((error) => {
        setSubmitError(`Failed to add task. Error: ${error.message}`);
        setSubmitMessage("");
        console.error("Error adding task:", error);
      });
  };

  return (
    <div className="AddTask">
      <form onSubmit={handleSubmit}>
        <h1>Add Task</h1>
        <input
          type="text"
          name="taskName"
          placeholder="Task Name"
          onChange={(val) => setName(val.target.value)}
        />
        <br />
        <br />
        <input
          type="text"
          name="description"
          placeholder="Description"
          onChange={(val) => setDescription(val.target.value)}
        />
        <br />
        <br />
        <input type="submit" value="Add Task" />
        {submitMessage && <p style={{ color: "green" }}>{submitMessage}</p>}
        {submitError && <p style={{ color: "red" }}>{submitError}</p>}
      </form>
    </div>
  );
};
