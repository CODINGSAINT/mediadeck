import React, { Component } from 'react'
import MediaService from '../service/MediaService';
import { Typography, Grid } from '@mui/material';
import { IconButton, Container } from '@mui/material';
import { EditTwoTone, Delete, Add } from '@mui/icons-material';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';
import { Button } from '@mui/material';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import { Box } from '@mui/system';

import { createTheme, ThemeProvider } from '@mui/material/styles';

import CssBaseline from '@mui/material/CssBaseline';

import TweetStrom from '../images/twitterstorm.svg'
import Clock from '../images/clock.svg'
import CloudStorage from '../images/cloudStorage.svg'
import SchedulePost from '../images/SchedulePost.svg'

import M from '../images/M.png'

import Programmer from '../images/programmer.svg'
const theme = createTheme();
class Home extends Component {
    constructor(props) {
        super(props)
        this.state = {
            decks: [],
            message: null
        }
        this.delete = this.delete.bind(this);
        this.edit = this.edit.bind(this);
        this.add = this.add.bind(this);
        this.reload = this.reload.bind(this);
        this.expand = this.expand.bind(this);
        this.covertDate = this.covertDate.bind(this);


    }


    componentDidMount() {
        this.reload();
    }

    reload() {
        MediaService.fetchAll()
            .then((res) => {
                this.setState({ decks: res.data })
            });
    }

    delete(id) {
        MediaService.delete(id)
            .then(res => {
                this.setState({ message: 'Deck deleted successfully.' });
                this.setState({ decks: this.state.decks.filter(deck => deck.id !== id) });
            })
    }

    edit(id) {
        window.localStorage.setItem("deckId", id)
        this.props.history.push('/edit-deck')
    }

    add() {
        window.localStorage.removeItem("deckId")
        this.props.history.push('/add-deck')
    }
    expand(id) {
        window.localStorage.setItem("deckId", id)
        this.props.history.push('/view-deck')
    }

    covertDate(d) {
        var date = new Date(d);
        return date.toLocaleString();

    }
    render() {
        return (
            <div>
                <ThemeProvider theme={theme}>

                    <Box sx={{ justifyContent: 'center' }}>
                        <Container component="main" maxWidth="xl">
                            <CssBaseline />
                            <Grid container spacing={2} sx={{ justifyContent: 'center' }}>
                                <Grid item xs={12} sx={{ justifyContent: 'center', textAlign: 'center' }} >
                                    <Typography variant="h3"
                                        component="span">
                                               
                                            Mediadeck - Schedule Tweetstorms</Typography>
                                </Grid>
                                <Grid item xs={8}>
                                <img src={M} alt="Tweetstorm" height='100' />
                                    <Typography variant="h5"
                                        component="span">Mediadeck - It's a DIY application
                                        to schedule the Tweeter threads free
                                        . Mediadeck is the freedom from paying for scheduling threads on Tweeter.
                                        While it's in Beta and only Tweeter is supported now, There are plans to support other platform </Typography>
                                    <Grid container style={{ paddingTop: 10 }}>
                                        <Grid item xs={12}>
                                            <Button variant='contained' >Start Scheduling </Button>
                                        </Grid>
                                    </Grid>
                                </Grid>
                                <Grid item xs={4}>
                                    <img src={TweetStrom} alt="Tweetstorm" height='200' />
                                </Grid>
                            </Grid>
                            <Divider sx={{ padding: 10 }} />


                            <Grid container spacing={2} sx={{ justifyContent: 'center', padding: 10, textAlign: 'center' }}>
                                <Grid item xs={12} sx={{ justifyContent: 'center', textAlign: 'center' }} >
                                    <Typography variant="h3"
                                        component="span">Start Your Own Scheduler in 3 Steps</Typography>
                                </Grid >
                                <Grid item xs={8}>
                                    <Typography variant="h5"
                                        component="span">
                                        Setup your Twitter App
                                    </Typography>

                                </Grid>
                                <Grid item xs={4}>
                                    <img src={Programmer} alt="Tweetstorm" height='100' />
                                </Grid>
                                <Grid item xs={12}>
                                    <Button  > Setup Now </Button>
                                </Grid>
                           

                                <Grid item xs={4}>
                                    <img src={CloudStorage} alt="Tweetstorm" height='100' />
                                </Grid>
                                <Grid item xs={8}>
                                    <Typography variant="h5"
                                        component="span">
                                        Connect your google firestore and cloud storage
                                    </Typography>
                                    <Grid container style={{ paddingTop: 10 }}>
                                        <Grid item xs={12}>
                                            <Button  > Connect  </Button>
                                        </Grid>
                                    </Grid>
                                </Grid>
                           

                               <Grid item xs={8}>
                                    <Typography variant="h5"
                                        component="span">
                                        Connect to Mediadeck and Schedule
                                    </Typography>
                                    <Grid container style={{ paddingTop: 10 }}>
                                        <Grid item xs={12}>
                                            <Button  > Schedule </Button>
                                        </Grid>
                                    </Grid>
                                </Grid>

                                <Grid item xs={4} justify="center">
                                    <img src={SchedulePost} alt="Tweetstorm" height='100' />
                                </Grid>

                            </Grid>
                        </Container>
                    </Box>
                </ThemeProvider>


            </div>
        );


    }
}
export default Home;
