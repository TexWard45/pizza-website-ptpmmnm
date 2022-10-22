import { useState } from "react";
import { Input, InputLabel, Button, Box, Typography, FormGroup } from "@mui/material";
import { makeStyles } from "@mui/styles";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

const useStyles = makeStyles(theme => ({
    wrapper: {
        position: 'relative',
        width: '100vw',
        height: '100vh',
        backgroundColor: '#FA1E3C'
    },
    content: {
        [theme.breakpoints.down("xs")]: {
            position: 'block',
            top: 'unset',
            left: 'unset',
            width: '100vw',
            height: '100vh',
        },
        [theme.breakpoints.up("sm")]: {
            position: 'absolute',
            top: '50%',
            left: '50%',
            width: '400px',
            transform: 'translate(-50%, -50%)'
        },
        borderRadius: '8px',
        backgroundColor: 'white !important',
        override: 'hidden',
    },
    formContent: {
        padding: '1rem',
    },
    headerIcon: {
        display: 'block !important',
        margin: 'auto !important',
        width: '7.5rem !important',
        height: '7.5rem !important',
        textAlign: 'center !important',
    },
    headerFont: {
        paddingBottom: '1rem',
        fontWeight: '600',
        textAlign: 'center',
    },
    inputFont: {
        fontWeight: '700 !important',
        fontSize: '1.25rem !important'
    }
}));

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const classes = useStyles();

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log( 'Email:', username, 'Password:', password);
    };
    
    return (
    <Box className={classes.wrapper}>
        <Box as="form" className={classes.content}>
            <FormGroup className={classes.formContent}>
                <AccountCircleIcon className={classes.headerIcon} />
                <Typography className={classes.headerFont} variant="h4"> Đăng nhập </Typography>
                <Box mb={1}></Box>
                <InputLabel className={classes.inputFont} htmlFor="username">Tài khoản</InputLabel>
                <Input 
                    name="username" 
                    id="username" 
                    placeholder='Nhập tài khoản' 
                    value={username} 
                    onInput={ e=> setUsername(e.target.value) } />
                <Box mb={2}></Box>
                <InputLabel className={classes.inputFont} htmlFor="password">Mật khẩu</InputLabel>
                <Input 
                    type="password" 
                    name="password" 
                    id="password" 
                    placeholder='Nhập mật khẩu' 
                    value={password} 
                    onInput={ e=> setPassword(e.target.value) }/>
                <Box mb={2}></Box>
                <Button variant="contained" color='primary' onClick={handleSubmit}> Đăng nhập </Button>
            </FormGroup>
        </Box>
    </Box>);
}

export default Login;