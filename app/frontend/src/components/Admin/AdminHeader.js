import { Grid } from "@mui/material";

function AdminHeader(props) {
    return(
        <Grid 
            container
            justifyContent="space-between"
            sx={{ p: 2 }}>
            {props.children}
        </Grid>
    );
}

export default AdminHeader;