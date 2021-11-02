import React, { Component } from 'react'
import MediaService from '../service/MediaService';
import { Typography } from '@mui/material';

import {  IconButton ,Container} from '@mui/material';
import { EditTwoTone, Delete, Add } from '@mui/icons-material';
import Fab from '@mui/material/Fab';
import AddIcon from '@mui/icons-material/Add';

import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';

import { createTheme, ThemeProvider } from '@mui/material/styles';

import CssBaseline from '@mui/material/CssBaseline';


const theme = createTheme();
class DecksComponent extends Component {
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

    covertDate(d){
        var date= new Date(d);
        return date.toLocaleString();
        
    }
    render() {
        return (
            <div>
 <ThemeProvider theme={theme}>
                    <Container component="main" maxWidth="lg">
                        <CssBaseline />

                <Typography variant="h4"
                    component="span"
                    sx={{ display: 'flex', my: '15px', mx: '5px' }}
                >Scheduled Tweets</Typography>
                <List sx={{ width: '100%', maxWidth: 600, bgcolor: 'background.paper' }}>


                    {this.state.decks.map(row => (

                        <ListItem>
                            <ListItemText
                                primary={row.title}>
                            </ListItemText>

                            <ListItemText
                                secondary={this.covertDate(row.scheduledDate)}>
                            </ListItemText>
                            <IconButton
                                onClick={() => this.expand(row.id)} color="primary"
                            ><Add /></IconButton>
                            <IconButton
                                onClick={() => this.edit(row.id)} color="primary"
                                variant='contained'   ><EditTwoTone /></IconButton>
                            <IconButton onClick={() => this.delete(row.id)} color="secondary" variant='contained' >
                                <Delete />
                            </IconButton>


                            <Divider />
                        </ListItem>


                    ))}
                </List>

                <Fab color="primary" aria-label="add" component="span"
                    sx={{
                        position: 'absolute',
                        bottom: 10,
                        right: 20,
                    }} >
                    <AddIcon onClick={() => this.add()} />
                </Fab>
                </Container>
                </ThemeProvider>


            </div>
        );


    }
}
export default DecksComponent;
