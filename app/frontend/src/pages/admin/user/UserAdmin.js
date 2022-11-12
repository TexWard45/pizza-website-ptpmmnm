import { AddBox } from "@mui/icons-material";
import DeleteIcon from '@mui/icons-material/Delete';
import BorderColorIcon from '@mui/icons-material/BorderColor';
import SearchIcon from '@mui/icons-material/Search';
import InfoIcon from '@mui/icons-material/Info';
import KeyIcon from '@mui/icons-material/Key';
import { Button, Divider } from "@mui/material";
import { DataGrid, viVN } from '@mui/x-data-grid';
import AdminLayout from "../../../components/Admin/AdminLayout";
import AdminTitle from "../../../components/Admin/AdminTitle";
import AdminButton from "../../../components/Admin/AdminButton";
import AdminHeader from "../../../components/Admin/AdminHeader";
import axios from "axios";
import { useEffect, useState } from "react";
import { UserColumns } from "../../../data/TableData";
import { Link } from "react-router-dom";
import AdminBody from "../../../components/Admin/AdminTable";
import { UserCreateAdminPath } from "../../../data/PathData";
  
function UserAdmin(props) {
    const [rows, setRows] = useState([]);
    const [columns, setColumns] = useState(UserColumns);
    
    const loadDataTable = () => {
        axios.get(`http://localhost:8080/api/v1/user`).then((response) => {
            response.data.forEach((element, index) => {
                var rows = [];
                rows.push({ 
                    id: index + 1, 
                    col1: element.username, 
                    col2: element.groupid,
                    col3: element.fullname,
                    col4: element.birth,
                    col5: element.address,
                    col6: element.phone,
                    col7: element.email,
                });
                setRows(rows);
            });
        });
    }

    useEffect(()=>{
        loadDataTable();
    }, [columns]);

    return(
        <AdminLayout>
            <AdminHeader>
                <AdminTitle> Quản lý tài khoản </AdminTitle>
                <AdminButton> 
                    <Button variant="outlined" startIcon={<SearchIcon />}> Tìm kiếm </Button>
                    <Button variant="outlined" startIcon={<InfoIcon />}> Thông tin </Button>
                    <Button variant="outlined" startIcon={<AddBox />} component={Link} to={{pathname: UserCreateAdminPath, state: [{id: 1, name: 'Test'}]}}> Tạo </Button>
                    <Button variant="outlined" startIcon={<BorderColorIcon />}> Sửa </Button>
                    <Button variant="outlined" startIcon={<KeyIcon />}> Phân quyền </Button>
                    <Button variant="outlined" startIcon={<DeleteIcon />}> Xóa </Button>
                </AdminButton>
            </AdminHeader>

            <Divider />

            <AdminBody>
                <DataGrid 
                    rows={rows} 
                    columns={columns} 
                    checkboxSelection     
                    onSelectionModelChange={itm => console.log(itm)}
                    localeText={viVN.components.MuiDataGrid.defaultProps.localeText}/>
            </AdminBody>
        </AdminLayout>
    );
}

export default UserAdmin;