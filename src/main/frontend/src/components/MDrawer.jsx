import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';

import Link from '@mui/material/Link';

import Menu from '@mui/icons-material/Menu';
import IconButton from '@mui/material/IconButton';
export default function MDrawer() {
  const [state, setState] = React.useState({
    left: false
  });

  const toggleDrawer = (anchor, open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }

    setState({ ...state, [anchor]: open });
  };

  const list = (anchor) => (
    <Box
      sx={{}}
      role="presentation"
      onClick={toggleDrawer(anchor, false)}
      onKeyDown={toggleDrawer(anchor, false)}
    >
      <List>

        <ListItem button >
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <Link href='add-deck'>
          <ListItemText primary=" Schedule" />
          </Link>
        </ListItem>

        
        <ListItem button >
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <Link href='all-deck'>
          <ListItemText primary="List " />
          </Link>
        </ListItem>
        <Divider/>
        <ListItem button >
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <Link href='config'>
          <ListItemText primary="Configuration" />
          </Link>
        </ListItem>

        <ListItem button >
          <ListItemIcon>
            <InboxIcon />
          </ListItemIcon>
          <Link href='help'>
          <ListItemText primary="Help" />
          </Link>
        </ListItem>
      </List>
    </Box>
  );

  return (
    <div>
      {['left'].map((anchor) => (
        <React.Fragment key={anchor}>

          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={toggleDrawer(anchor, true)}
          >
            <Menu />

          </IconButton>

          <Drawer
            anchor={anchor}
            open={state[anchor]}
            onClose={toggleDrawer(anchor, false)}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
}
