import { ClassNames } from "@emotion/react";
import { Avatar, Box, List, ListItemButton, ListItemIcon, ListItemText, Typography } from "@mui/material";
import { makeStyles } from "@mui/styles";
import LocalPizzaIcon from '@mui/icons-material/LocalPizza';
import PeopleAltIcon from '@mui/icons-material/PeopleAlt';
import PersonIcon from '@mui/icons-material/Person';
import BlurCircularIcon from '@mui/icons-material/BlurCircular';
import StraightenIcon from '@mui/icons-material/Straighten';
import CategoryIcon from '@mui/icons-material/Category';
import EggIcon from '@mui/icons-material/Egg';
import ReceiptIcon from '@mui/icons-material/Receipt';
import EqualizerIcon from '@mui/icons-material/Equalizer';
import InventoryIcon from '@mui/icons-material/Inventory';

const useStyles = makeStyles(theme => ({
    wrapper: {
        width: '20%',
        minWidth: '50px',
        maxWidth: '250px',
        minHeight: '100vh',
        backgroundColor: '#343a40'
    },
    logo_wrapper: {
        margin: '1rem 0rem'
    },
    logo_content: {
        display: 'flex',
        width: '80%',
        maxWidth: '120px',
        margin: 'auto'
    },
    logo_name: {
        [theme.breakpoints.down("sm")]: {
            fontSize: '0.75rem !important'
        },
        color: 'white',
        textAlign: 'center',
        fontWeight: '700 !important',
        marginTop: '0.75rem !important'
    },
    item_button: {
        backgroundColor: '#6c757d !important',
        marginBottom: '0.25rem !important',
        borderRadius: '0.25rem !important',
        padding: '0.25rem 1rem !important',
        '&:hover': {
            color: '#fff',
            backgroundColor: '#5c636a !important',
            borderColor: '#565e64'
        }
    },
    item_icon: {
        [theme.breakpoints.down("md")]: {
            width: '100% !important',
            textAlign: 'center !important',
            justifyContent: 'center !important',
            minWidth: '0px !important'
        },
        minWidth: '40px !important',
        '& > svg': {
            fill: 'white'
        }
    },
    item_font: {
        [theme.breakpoints.down("md")]: {
            display: 'none !important'
        },
        color: 'white'
    },
    group_item: {
        height: '0px',
        overflow: 'hidden',
    }
}));

function AdminSidebar() {
    const classes = useStyles();

    function handleClick(e) {
        e.currentTarget.classList.toggle('active');
        console.log(e);
    }

    return(
        <Box className={classes.wrapper}>
            <Box className={classes.logo_wrapper}> 
                <Box
                    className={classes.logo_content}
                    component="img"
                    alt="Logo"
                    src={require('../../assets/logo.png')}
                />

                <Typography className={classes.logo_name}> 
                    FastPizza
                </Typography>
            </Box>

            <List>
                {SidebarData.map((item, index) => {
                    return (
                        <ListItemButton key={item.key} className={classes.item_button}>
                            <ListItemIcon className={classes.item_icon}>
                                {item.icon}
                            </ListItemIcon>
                            <ListItemText className={classes.item_font} primary={item.text} />
                        </ListItemButton>
                    )
                })}
            </List>
        </Box>
    );
}

const SidebarData = [ 
    { 
        key: 'Group',
        text : 'Nhóm tài khoản', 
        path : '/',
        icon : <PeopleAltIcon/>
    },
    { 
        key: 'User',
        text : 'Tài khoản', 
        path : '/',
        icon : <PersonIcon/>
    },
    { 
        key: 'Category',
        text : 'Danh mục', 
        path : '/',
        icon : <CategoryIcon/>
    },
    { 
        key: 'Topping',
        text : 'Nhân bánh', 
        path : '/',
        icon : <EggIcon/>
    },
    { 
        key: 'Size',
        text : 'Kích thước', 
        path : '/',
        icon : <StraightenIcon/>
    },
    { 
        key: 'Base',
        text : 'Đế bánh', 
        path : '/',
        icon : <BlurCircularIcon/>
    },
    { 
        key: 'Pizza',
        text : 'Bánh pizza', 
        path : '/',
        icon : <LocalPizzaIcon/>
    },
    { 
        key: 'Order',
        text : 'Đơn hàng', 
        path : '/',
        icon : <ReceiptIcon/>
    },
    { 
        key: 'Statistic',
        text : 'Thống kê', 
        path : '/',
        icon : <EqualizerIcon/>
    }
]

export default AdminSidebar;