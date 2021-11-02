import React, { Component } from 'react'
import MediaService from '../service/MediaService';
import { Button, TextField } from '@mui/material';
import Typography from '@material-ui/core/Typography';
import { DateTimePicker } from '@mui/lab';
import { Grid, Container } from '@mui/material';
import { KeyboardDateTimePicker as DTPICKer } from "@material-ui/pickers";

import { EditTwoTone, Delete, Add } from '@mui/icons-material';


import AdapterDateFns from '@mui/lab/AdapterDateFns';
import LocalizationProvider from '@mui/lab/LocalizationProvider';
import { createTheme, ThemeProvider } from '@mui/material/styles';

import CssBaseline from '@mui/material/CssBaseline';

const theme = createTheme();
class EditDeckComponent extends Component {


    constructor(props) {
        super(props);
        this.state = {
            deck: {},
            title: '',
            scheduledDate: new Date(),
            message: null
        }
        this.saveDeck = this.saveDeck.bind(this);
        this.reload = this.reload.bind(this);

    }
    componentDidMount() {
        this.reload();
    }


    reload() {
        var deckId = window.localStorage.getItem("deckId")
        MediaService.fetcDeckById(deckId)
            .then((res) => {
                this.setState({
                    deck: res.data,
                    title: res.data.title,
                    scheduledDate: res.data.scheduledDate
                })
            });

    }


    saveDeck = (e) => {
        e.preventDefault();
        let deck = {
            id: this.state.deck.id,
            title: this.state.title,
            scheduledDate: this.state.scheduledDate
        };
        MediaService.editDeck(deck)
            .then(res => {
                this.setState({ message: 'Deck edited successfully.' });
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
                        <Typography variant="h4" >Edit Scheduled Item</Typography>


                        <form style={formContainer}>


                            <Grid container spacing={2}>
                                <Grid item xs={12}>
                                    <TextField type="text"
                                        margin="normal"
                                        required
                                        fullWidth
                                        type="text"
                                        placeholder="Title "
                                        label="Title"
                                        variant="outlined"
                                        name="title" value={this.state.title} onChange={this.onChange} />
                                </Grid>
                                <Grid item xs={12}>
                                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                                        <DateTimePicker
                                            inputFormat="dd/MM/yyyy HH:mm"
                                            renderInput={(props) => <TextField {...props} />}
                                            label="Scheduled Date & Time (dd/MM/yyyy HH:mm)"
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
                                            sx={{ mt: 3, mb: 2 }} onClick={this.saveDeck} >Update</Button>
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

export default EditDeckComponent;