import { Button, Divider } from "@mui/material";
import AdminLayout from "../../../components/Admin/AdminLayout";
import AdminTitle from "../../../components/Admin/AdminTitle";
import AdminButton from "../../../components/Admin/AdminButton";
import AdminHeader from "../../../components/Admin/AdminHeader";
import KeyboardReturnIcon from '@mui/icons-material/KeyboardReturn';
import AdminBody from "../../../components/Admin/AdminTable";
import { Box } from "@mui/system";
import { Link, useLocation } from "react-router-dom";
import CustomInput from "../../../components/CustomInput";
import { UserAdminPath } from "../../../data/PathData";
import { useState } from "react";
  
function UserCreateAdmin(props) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [fullname, setFullname] = useState('');
    const [birth, setBirth] = useState('');
    const [address, setAddress] = useState('');
    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState('');

    const onSubmit = () => {
        
    }

    return(
        <AdminLayout>
            <AdminHeader>
                <AdminTitle> Tạo tài khoản </AdminTitle>
                <AdminButton> 
                    <Button variant="outlined" startIcon={<KeyboardReturnIcon />} component={Link} to={UserAdminPath}> Quay lại </Button>
                </AdminButton>
            </AdminHeader>

            <Divider />

            <AdminBody>
                <CustomInput name="username" label="Tài khoản" placeholder="Nhập tài khoản" onInput={e=> setUsername(e.target.value)} />
                <CustomInput name="password" label="Mật khẩu" type="password" placeholder="Nhập mật khẩu" onInput={e=> setPassword(e.target.value)}/>
                <CustomInput name="fullname" label="Họ tên" placeholder="Nhập họ tên" onInput={e=> setFullname(e.target.value)}/>
                <CustomInput name="birth" label="Ngày sinh" type="datetime" onInput={e=> setBirth(e.target.value)}/>
                <CustomInput name="address" label="Địa chỉ" placeholder="Nhập địa chỉ" onInput={e=> setAddress(e.target.value)}/>
                <CustomInput name="phone" label="Số điện thoại" placeholder="Nhập số điện thoại" onInput={e=> setPhone(e.target.value)}/>
                <CustomInput name="email" label="Email" placeholder="Nhập email" onInput={e=> setEmail(e.target.value)}/>
                <Button variant="outlined" onClick={onSubmit}> Tạo </Button>
            </AdminBody>
        </AdminLayout>
    );
}

export default UserCreateAdmin;