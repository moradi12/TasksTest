import React, { useState } from 'react';
import axios from 'axios';
import './Delete.css';

export function Delete(): JSX.Element {
  const [taskId, setTaskId] = useState('');
  const [deleteMessage, setDeleteMessage] = useState('');
  const [deleteError, setDeleteError] = useState('');
  const [confirmDelete, setConfirmDelete] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    setTaskId(e.target.value);
  };

  const handleDelete = () => {
    axios.delete(`http://localhost:8080/api/v1/task/tasks/${taskId}`)
      .then(() => {
        setDeleteMessage('Task deleted successfully.');
        setDeleteError('');
        setTaskId('');
        setConfirmDelete(false);
      })
      .catch(error => {
        setDeleteError(`Failed to delete task. Error: ${error.message}`);
        setDeleteMessage('');
        console.error('Error deleting task:', error);
      });
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!confirmDelete) {
      setConfirmDelete(true);
    } else {
      handleDelete();
    }
  };

  return (
    <div className="Delete">
      <form onSubmit={handleSubmit}>
        <h1>Delete Task</h1>
        <input
          type="text"
          name="taskId"
          placeholder="Task ID"
          value={taskId}
          onChange={handleChange}
        />
        <br /><br />
        <input type="submit" value={confirmDelete ? "Confirm Delete" : "Delete Task"} />
        {confirmDelete && (
          <p style={{ color: "red" }}>Are you sure you want to delete this task? Press "Confirm Delete" to proceed.</p>
        )}
        {deleteMessage && <p style={{ color: "green" }}>{deleteMessage}</p>}
        {deleteError && <p style={{ color: "red" }}>{deleteError}</p>}
      </form>
    </div>
  );
}
