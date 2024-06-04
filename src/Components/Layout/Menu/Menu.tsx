import React from 'react';
import { NavLink } from 'react-router-dom';
import './Menu.css';

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
            <NavLink to="/">Home</NavLink> |
            <NavLink to="/add">Add Task</NavLink> |
            <NavLink to="/delete">Delete Task</NavLink> |
            <NavLink to="/edit">Edit Task</NavLink>|


        </div>
    );
}
