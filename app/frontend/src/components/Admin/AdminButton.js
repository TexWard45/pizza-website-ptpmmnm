import { Grid, Typography } from "@mui/material";

function AdminButton(props) {
    return(
        <Grid 
            item
            display="flex"
            flexWrap="wrap"
            gap={1}>
            {props.children}
        </Grid>
    );
}

export default AdminButton;