import React, { Component } from 'react'
import MediaService from '../service/MediaService';
import { Add, Delete, Create, Upload } from '@mui/icons-material';
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




class ConfigUpdate extends Component {
    constructor(props) {
        super(props);
        this.state = {
            key: '',
            secretkey: '',
            accessToken: '',
            accessTokenSecret: '',
            projectId: '',
            databaseURL: '',
            bucket: '',
            storage: null,
            firebase: null

        }
        this.initFirebase = this.initFirebase.bind(this);
        this.initTwitter = this.initTwitter.bind(this);
        this.initStorage = this.initStorage.bind(this);
        this.onChange = this.onChange.bind(this)
        this.onFirebaseFileChange = this.onFirebaseFileChange.bind(this)
        this.onStorageFileChange = this.onStorageFileChange.bind(this)
        
    }

    onFirebaseFileChange = event => {
    
        // Update the state
        console.log(event.target.files[0])
        this.setState({ firebase: event.target.files[0] });
      
      };

      onStorageFileChange = event => {
    
        // Update the state
        this.setState({ storage: event.target.files[0] });
      
      };
    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }


    initFirebase = (e) => {
        //Add Desciption with images link and deck id
        //uploadFilesAndCreateElement

        MediaService.initFirebase(this.state.firebase, this.state.databaseURL)



    }

    initStorage = (e) => {
        MediaService.initStorage(this.state.storage, this.state.projectId, this.state.bucket)


    }
    initTwitter = (e) => {
        //Add Desciption with images link and deck id
        //uploadFilesAndCreateElement
        var twitter = {

            key: this.state.key,
            secretkey: this.state.secretkey,
            accessToken: this.state.accessToken,
            accessTokenSecret: this.state.accessTokenSecret

        }
        var response = MediaService.initTwitter(twitter)

    }

    componentDidMount() {


    }


    render() {
        return (
            <div>
                <ThemeProvider theme={theme}>
                    <Container component="main" maxWidth="sm">
                        <CssBaseline />
                        <div className='config'>
                            <div className='title'>
                                <Typography variant="h4">Configure Your Applicaton</Typography>
                            </div>

                        </div>
                        <form>
                            <Grid container direction="row" spacing={1}>
                                <Typography variant="h5" style={{ mx: 20, mh: 20 }} >Firebase Config </Typography>

                                <Grid container spacing={2} style={{ mx: 2 }} >
                                    <Grid item sm={12} >
                                        <TextField
                                            id="outlined-textarea"
                                            label="Database URL"
                                            placeholder="https://[YOUR_GOOGLE_APP_DATABASE].firebaseio.com"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="databaseURL" onChange={this.onChange} />




                                        <Box sx={{ flexDirection: 'row-reverse' }} style={{ padding: 10 }} >
                                            <Grid container justifyContent="flex-end" spacing={2}  style={{padding: 20}}  >
                                                <label htmlFor="icon-button-file"  >
                                                    <Upload variant="outlined" color="primary" aria-label="upload picture" />

                                                </label>
                                                <input accept="*.json"
                                                    id="icon-button-file"
                                                    type="file" name={this.state.firebase} 
                                                    
                                                    onChange={this.onFirebaseFileChange}
                                                />
                                               
                                               <Button variant='outlined' color="primary" onClick={this.initFirebase} >Configure Firestore</Button>

                                            </Grid>
                                        </Box>
                                    </Grid>
                                </Grid>
                            </Grid>

                        </form>



                        <form>
                            <Grid container direction="row" spacing={1}>
                                <Typography variant="h5" style={{ mx: 20, mh: 20 }} >Configure Google Storage </Typography>

                                <Grid container spacing={2} style={{ mx: 2 }} >
                                    <Grid item sm={12} >
                                        <TextField
                                            id="outlined-textarea"
                                            label="Database Project Id"
                                            placeholder="Your Storage Project ID"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="projectId" onChange={this.onChange} />


                                        <TextField
                                            id="outlined-textarea"
                                            label="Storage Bucket"
                                            placeholder="[project id].appspot.com or staging.[project id].appspot.com"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="bucket" onChange={this.onChange} />


                                        <Box sx={{ flexDirection: 'row-reverse' }} style={{ padding: 10 }} >
                                            <Grid container justifyContent="flex-end" spacing={2}   style={{padding: 20}}  >
                                                <label htmlFor="icon-button-file"  >
                                                    <Upload variant="outlined" color="primary" aria-label="upload picture" />

                                                </label>
                                                <input accept="*.json"
                                                    id="icon-button-file"

                                                    type="file" name={this.state.storage} 
                                                   onChange ={this.onStorageFileChange}
                                                />
                                                
                                                <Button variant='outlined' color="primary" onClick={this.initStorage} > Configure Storage</Button>

                                            </Grid>
                                        </Box>
                                    </Grid>
                                </Grid>
                            </Grid>

                        </form>

                        <form>
                            <Grid container direction="row" spacing={1}>
                                <Typography variant="h5" style={{ mx: 20, mh: 20 }} >Twitter Config </Typography>

                                <Grid container spacing={2} style={{ mx: 2 }} >
                                    <Grid item sm={12} >
                                        <TextField
                                            id="outlined-textarea"
                                            label="key"
                                            placeholder="Twitter App Key"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="key" onChange={this.onChange} />

                                        <TextField
                                            id="outlined-textarea"
                                            label="Twitter Secret Key"
                                            placeholder="secretkey"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="secretkey" onChange={this.onChange} />

                                        <TextField
                                            id="outlined-textarea"
                                            label="Access Token"
                                            placeholder="accessToken"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="accessToken" onChange={this.onChange} />

                                        <TextField
                                            id="outlined-textarea"
                                            label="Access Token Secret"
                                            placeholder="accessTokenSecret"
                                            maxLength='280' fullWidth
                                            variant="outlined"

                                            name="accessTokenSecret" onChange={this.onChange} />





                                        <Box sx={{ flexDirection: 'row-reverse' }} style={{ padding: 10 }} >
                                            <Grid container justifyContent="flex-end" spacing={2}  style={{padding: 20}}  >


                                                <Button variant='outlined' color="primary" onClick={this.initTwitter} >Configure Twitter</Button>

                                            </Grid>
                                        </Box>
                                    </Grid>
                                </Grid>
                            </Grid>

                        </form>


                    </Container>
                </ThemeProvider>
            </div>


        );
    }



}

export default ConfigUpdate;