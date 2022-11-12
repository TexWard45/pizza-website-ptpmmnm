import { Box } from "@mui/material";
import AdminContent from "./AdminContent";
import AdminSidebar from "./AdminSidebar";
import AdminWrapper from "./AdminWrapper";

function AdminLayout(props) {
    return(
        <AdminWrapper>
            <AdminSidebar />
            <AdminContent>
                {props.children}
            </AdminContent>
        </AdminWrapper>
    );
}

export default AdminLayout;