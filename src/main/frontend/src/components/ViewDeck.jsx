import React, { Component } from 'react'
import MediaService from '../service/MediaService';
import { Add, Delete, Create, PhotoCamera } from '@mui/icons-material';
import { Typography, Grid, Container, Box } from '@mui/material';
import { TextareaAutosize, TextField, Button, IconButton } from '@mui/material';
import ImageList from '@mui/material/ImageList';
import ImageListItem from '@mui/material/ImageListItem';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { makeStyles } from '@material-ui/core';
import CssBaseline from '@mui/material/CssBaseline';
import Paper from '@mui/material/Paper';
import 'emoji-mart/css/emoji-mart.css'
import { Picker } from 'emoji-mart'

const theme = createTheme();


const useStyles = makeStyles(theme => ({
    root: {
        "& .MuiTextField-root": {
            margin: theme.spacing(1)
        }
    },
    textarea: {
        resize: "both"
    }
}));


class ViewDeckComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            deck: {},
            elements: [],
            desc: '',
            imageURLs: [],
            imgs: []
        }
        this.addItem = this.addItem.bind(this);
        this.editElement = this.editElement.bind(this);
        this.deleteElement = this.deleteElement.bind(this);
        this.onChange = this.onChange.bind(this)
        this._onChange = this._onChange.bind(this)
        this.addEmoji = this.addEmoji.bind(this)

    }
    addEmoji = (e) => {
        let emoji = e.native;
        this.setState({
            desc: this.state.desc + emoji

        });
    }
    _onChange = (event) => {
        this.setState({
            imgs: event.target.files
        })
    }
    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }



    addItem = (e) => {
        //Add Desciption with images link and deck id
        //uploadFilesAndCreateElement
        var element = {
            desc: this.state.desc,
            deckId: window.localStorage.getItem("deckId")
        }
        console.log("adding elements ", element)
        var response = MediaService.uploadFilesAndCreateElement(this.state.imgs, element)



    }

    componentDidMount() {
        this.reload();
    }

    reload() {
        var deckId = window.localStorage.getItem("deckId")
        MediaService.fetcDeckById(deckId)
            .then((res) => {
                this.setState({ deck: res.data })
            });
        MediaService.fetchItems(deckId).then((res) => {
            this.setState({ elements: res.data })
        });
    }
    editElement(id) {

    }
    deleteElement(id) {
        MediaService.deleteElement(id)
        window.location.reload();
    }

    render() {
        return (
            <div>
                <ThemeProvider theme={theme}>
                    <Container component="main" maxWidth="sm">
                        <CssBaseline />
                        <div className='deck'>
                            <div className='title'>
                                <Typography variant="h4">{this.state.deck.title}</Typography>
                            </div>
                            <div className='schedule'>
                                <div style={{ paddingBottom: 10 }} >
                                    @ {this.state.deck.scheduledDate}
                                </div>
                            </div>
                        </div>
                        <form>
                            <Grid container direction="row" spacing={1}>
                                <Typography variant="h5" style={{ mx: 20, mh: 20 }} >Add Tweets</Typography>

                                <Grid container spacing={2} style={{ mx: 2 }} >
                                    <Grid item sm={12} >
                                        <TextField
                                            id="outlined-textarea"
                                            label="Tweet / Details"
                                            placeholder="What's Happening ..?"
                                            multiline maxLength='280' fullWidth
                                            variant="outlined"
                                            inputProps={{ className: useStyles.textarea }}
                                            name="desc" onChange={this.onChange} />


                                        <Grid container spacing={2} >
                                            <Grid item sm={12}>
                                                {this.state.imgs && [...this.state.imgs].map((file) => (
                                                    <img src={URL.createObjectURL(file)} height='100px' />
                                                ))}
                                            </Grid>
                                        </Grid>

                                        <Box sx={{ flexDirection: 'row-reverse' }} style={{ padding: 10 }} >
                                            <Grid container justifyContent="flex-end" spacing={2}   >
                                                <label htmlFor="icon-button-file"  >
                                                    <PhotoCamera variant="outlined" color="primary" aria-label="upload picture" />

                                                </label>
                                                <input accept="image/*"
                                                    id="icon-button-file"
                                                    multiple
                                                    max='4'
                                                    type="file" name={this.state.imgs} style={{ display: 'none' }}
                                                    onChange={this._onChange}
                                                />
                                                <Add color="primary" onClick={this.addItem} />





                                            </Grid>
                                        </Box>
                                    </Grid>
                                </Grid>
                            </Grid>

                        </form>


                        <div className='elements'>
                            {this.state.elements.map(row => (

                                <div key={row.id} style={{ paddingBottom: 20 }} >
                                    <Paper elevation='3' style={{ padding: 10, maxWidth: 600 }}>
                                        <Grid container justifyContent="flex-end">
                                            <Delete color='error' onClick={() => this.deleteElement(row.id)} />


                                        </Grid>
                                        <Grid item sm={10}>

                                            <div style={{ whiteSpace: 'pre-wrap' }}>
                                                {row.desc}
                                            </div>


                                        </Grid>


                                        <Grid item sm={12}>

                                            <ImageList cols={3}>
                                                {row.mediaURLs && [...row.mediaURLs].map((fileId) => (
                                                    <ImageListItem key={fileId}>
                                                        <img
                                                            src={`${'http://localhost:8080/api/v1/download?img=' + fileId}&w=164&h=164&fit=crop&auto=format`}
                                                            srcSet={`${'http://localhost:8080/api/v1/download?img=' + fileId}&w=164&h=164&fit=crop&auto=format&dpr=2 2x`}

                                                            loading="lazy"
                                                        />
                                                    </ImageListItem>
                                                ))}
                                            </ImageList>



                                        </Grid>



                                    </Paper>

                                </div>
                            ))}
                        </div>

                    </Container>
                </ThemeProvider>
            </div>


        );
    }



}

export default ViewDeckComponent;