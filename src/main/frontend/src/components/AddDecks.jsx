import React, { Component } from 'react'
import MediaService from '../service/MediaService';
import { Button, TextField } from '@mui/material';
import Typography from '@material-ui/core/Typography';
import { DateTimePicker } from '@mui/lab';
import { Grid, Container } from '@mui/material';

import { createTheme, ThemeProvider } from '@mui/material/styles';

import CssBaseline from '@mui/material/CssBaseline';


import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';

const theme = createTheme();
class AddDeckComponent extends Component {


    constructor(props) {
        super(props);
        this.state = {
            title: '',
            scheduledDate: new Date(),
            message: null
        }
        this.saveDeck = this.saveDeck.bind(this);
    }

    saveDeck = (e) => {
        e.preventDefault();
        let deck = {
            title: this.state.title,
            scheduledDate: this.state.scheduledDate
        };
        MediaService.addDeck(deck)
            .then(res => {
                this.setState({ message: 'User added successfully.' });
                this.props.history.push('/');
            });
    }

    //   const [value, setValue] = React.useState(new Date());

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    render() {
        return (
            <div>
                <ThemeProvider theme={theme}>
                    <Container component="main" maxWidth="xs">
                        <CssBaseline />
                        <Typography variant="h4" >Add New Scheduled Item</Typography>
                        <form style={formContainer}>


                            <Grid container >

                                <Grid item xs={12} spacing={2}>
                                    <TextField
                                        margin="normal"
                                        required
                                        fullWidth
                                        type="text"
                                        placeholder="Title "
                                        label="Title"
                                        variant="outlined"
                                        name="title" value={this.state.title} onChange={this.onChange}
                                        autoFocus
                                    />


                                    <Grid item xs={12} spacing={2}>
                                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                                            <DateTimePicker
                                                inputFormat="dd/MM/yyyy HH:mm"
                                                renderInput={(props) => <TextField {...props} />}
                                                label="Scheduled Date Time"
                                                value={this.state.scheduledDate}


                                                onChange={(newValue) => {
                                                    this.setState({
                                                        scheduledDate: newValue
                                                    })
                                                }}
                                            />
                                        </LocalizationProvider>


                                    </Grid>

                                    <Grid item xs={12}>
                                        <Button fullWidth
                                            variant="contained"
                                            sx={{ mt: 3, mb: 2 }} onClick={this.saveDeck} >Add</Button>
                                    </Grid>
                                </Grid>
                            </Grid>

                        </form>
                    </Container>
                </ThemeProvider>


            </div>
        )
    }
}
const formContainer = {
    display: 'flex',
    flexFlow: 'row wrap'
};

const style = {
    display: 'flex',
    justifyContent: 'center'

}

export default AddDeckComponent;