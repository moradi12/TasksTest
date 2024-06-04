import React, { useState } from 'react';
import axios from 'axios';
import './EditTask.css';

export function EditTask(): JSX.Element {
  const [taskId, setTaskId] = useState('');
  const [newTaskName, setNewTaskName] = useState('');
  const [newDescription, setNewDescription] = useState('');
  const [updateMessage, setUpdateMessage] = useState('');
  const [updateError, setUpdateError] = useState('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>): void => {
    const { name, value } = e.target;
    if (name === 'taskId') setTaskId(value);
    else if (name === 'newTaskName') setNewTaskName(value);
    else if (name === 'newDescription') setNewDescription(value);
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const confirmed = window.confirm('Are you sure you want to update this task?');
    if (!confirmed) {
      return;
    }

    axios.put(`http://localhost:8080/api/v1/task/${taskId}`, {
        taskName: newTaskName,
        description: newDescription
      })
      .then(() => {
        setUpdateMessage('Task updated successfully.');
        setUpdateError('');
        setTaskId('');
        setNewTaskName('');
        setNewDescription('');
      })
      .catch(error => {
        setUpdateError(`Failed to update task. Error: ${error.message}`);
        setUpdateMessage('');
        console.error('Error updating task:', error);
      });
  };

  return (
    <div className="EditTask">
      <form onSubmit={handleSubmit}>
        <h1>Edit Task</h1>
        <input
          type="text"
          name="taskId"
          placeholder="Task ID"
          value={taskId}
          onChange={handleChange}
        />
        <br /><br />
        <input
          type="text"
          name="newTaskName"
          placeholder="New Task Name"
          value={newTaskName}
          onChange={handleChange}
        />
        <br /><br />
        <textarea
          name="newDescription"
          placeholder="New Description"
          value={newDescription}
          onChange={handleChange}
        />
        <br /><br />
        <input type="submit" value="Update Task" />
        {updateMessage && <p style={{ color: "green" }}>{updateMessage}</p>}
        {updateError && <p style={{ color: "red" }}>{updateError}</p>}
      </form>
    </div>
  );
}
