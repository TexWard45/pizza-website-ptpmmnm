import { Grid, Typography } from "@mui/material";

function AdminTitle(props) {
    return(
        <Grid item
            display="flex"
            alignItems="center">
            <Typography variant="h5">
                {props.children}
            </Typography>
        </Grid>
    );
}

export default AdminTitle;