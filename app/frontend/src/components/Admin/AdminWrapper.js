import { Box } from "@mui/material";
import { makeStyles } from "@mui/styles";

const useStyles = makeStyles(theme => ({
    wrapper: {
        display: 'flex',
        flexDirection: 'row',
        position: 'relative'
    },
}));

function AdminWrapper(props) {
    const classes = useStyles();

    return(
        <Box className={classes.wrapper}>
            {props.children}
        </Box>
    );
}

export default AdminWrapper;