import { TextField } from "@mui/material";
import { Box } from "@mui/system";
import { DateTimePicker, DesktopDatePicker, LocalizationProvider, TimePicker } from "@mui/x-date-pickers";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import React from "react";

function CustomInput(props) {
    const fullWidth = props.fullWidth != null ? props.fullWidth : true;
    const type = props.type != null ? props.type : 'text';
    const onInput = props.onInput != null ? props.onInput : () => {};
    const onChange = props.onChange != null ? props.onChange : () => {};

    if (type === 'date') {
        return(
            <LocalizationProvider 
                dateAdapter={AdapterDayjs}>
                <DesktopDatePicker
                    id={props.name}
                    label={props.label}
                    inputFormat="MM/DD/YYYY"
                    value={props.value}
                    onChange={onChange}
                    onInput={onInput}
                    fullWidth={fullWidth}
                    renderInput={(params) => <TextField {...params} fullWidth={fullWidth} />}
                    />
                <Box mb={1}></Box>
            </LocalizationProvider>
        );
    }

    if (type === 'time') {
        return(
            <LocalizationProvider 
                dateAdapter={AdapterDayjs}>
                <TimePicker
                    id={props.name}
                    label={props.label}
                    value={props.value}
                    onChange={onChange}
                    onInput={onInput}
                    fullWidth={fullWidth}
                    renderInput={(params) => <TextField {...params} fullWidth={fullWidth} />}
                    />
                <Box mb={1}></Box>
            </LocalizationProvider>
        );
    }

    if (type === 'datetime') {
        return(
            <LocalizationProvider 
                dateAdapter={AdapterDayjs}>
                <DateTimePicker
                    id={props.name}
                    label={props.label}
                    value={props.value}
                    onChange={onChange}
                    onInput={onInput}
                    fullWidth={fullWidth}
                    renderInput={(params) => <TextField {...params} fullWidth={fullWidth} />}
                    />
                <Box mb={1}></Box>
            </LocalizationProvider>
        );
    }

    return(
        <React.Fragment>
            <TextField 
                id={props.name} 
                label={props.label} 
                variant="outlined" 
                value={props.value}
                onChange={onChange}
                onInput={onInput}
                fullWidth={fullWidth}/>
            <Box mb={1}></Box>
        </React.Fragment>
    );
}

export default CustomInput;