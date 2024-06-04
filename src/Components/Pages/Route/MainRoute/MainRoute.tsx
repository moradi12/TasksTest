import { Route, Routes } from "react-router-dom";
import "./MainRoute.css";
import { Main } from "../../../Layout/Main/Main";
import { AddTask } from "../../AddTask/AddTask";
import { AllTasks } from "../../AllTasks/AllTasks";
import { Page404 } from "../../Page404/Page404";
import { Complete } from "../../Complete/Complated";
import { Delete } from "../../Delete/Delete";
import { EditTask } from "../../EditTask/EditTask";

export function MainRoute(): JSX.Element {
    return (
        <div className="MainRoute">
			<Routes>
                <Route path="/" element={<AllTasks />} />
                <Route path="/add" element={<AddTask />} />
                <Route path="/edit" element={<EditTask />} />
                <Route path="/complete" element={<Complete />} />
                <Route path="/delete" element={<Delete />} />
                <Route path="*" element={<Page404 />} />
            </Routes>
        </div>
    );
}
