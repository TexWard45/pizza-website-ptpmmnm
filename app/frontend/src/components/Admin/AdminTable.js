import { Box } from "@mui/material";

function AdminBody(props) {
    return(
        <Box 
            height="100%"
            sx={{ p: 2 }}>
            {props.children}
        </Box>
    );
}

export default AdminBody;