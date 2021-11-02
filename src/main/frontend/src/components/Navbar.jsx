import React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import MDrawer from './MDrawer';

import M from '../images/M.png'

const style = {
    flexGrow: 1
}
const NavBar = () => {
    return (
        <div>
            <Box sx={{ flexGrow: 1 }}>
                <AppBar position="static" 
        color="default"   elevation={2}>
                    <Toolbar>
                       <MDrawer/>
                        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                            Media Deck
                        </Typography>

                    </Toolbar>
                  
                </AppBar>
            </Box>
              
        </div>
    )
}

export default NavBar;