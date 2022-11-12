import { createBrowserRouter } from "react-router-dom";
import { LoginAdminPath, RegisterAdminPath, RootPath, UserAdminPath, UserCreateAdminPath } from "./data/PathData";
import UserAdmin from "./pages/admin/user/UserAdmin";
import UserCreateAdmin from "./pages/admin/user/UserCreateAdmin";
import Login from "./pages/Login";
import Register from "./pages/Register";

export const router = createBrowserRouter([
    {
        path: RootPath,
        element: <Login />
    },
    {
        path: LoginAdminPath,
        element: <Login />
    },
    {
        path: RegisterAdminPath,
        element: <Register />
    },
    {
        path: UserAdminPath,
        element: <UserAdmin />
    },
    {
        path: UserCreateAdminPath,
        element: <UserCreateAdmin />
    },
]);