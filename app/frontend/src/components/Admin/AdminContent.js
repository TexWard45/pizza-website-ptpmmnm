import { ClassNames } from "@emotion/react";
import { Box } from "@mui/material";
import { makeStyles } from "@mui/styles";

const useStyles = makeStyles(theme => ({
    wrapper: {
        display: 'flex',
        flexDirection: 'column',
        position: 'relative',
        width: '100%',
    },
}));

function AdminContent(props) {
    const classes = useStyles();

    return(
        <Box className={classes.wrapper}> {props.children} </Box>
    );
}

export default AdminContent;