import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import React from "react";
import DecksComponent from './decks';
import AddDeckComponent from './AddDecks.jsx';
import ViewDeckComponent from './ViewDeck';
import EditDeckComponent from './EditDeck'
import ConfigUpdate from './ConfigUpdate';
import Home from './Home';
const AppRouter = () => {
    return(
        <div style={style}>
            <Router>
                    <Switch>
                    <Route path="/" exact component={Home} />
                    <Route path="/all-deck" exact component={DecksComponent} />
                    <Route path="/config" exact component={ConfigUpdate} />
                    
                    <Route path="/add-deck" exact component={AddDeckComponent} />
                    <Route path="/view-deck" exact component={ViewDeckComponent} />
                    <Route path="/edit-deck" exact component={EditDeckComponent} />
                    
                    
                        
                    </Switch>
            </Router>
        </div>
    )
}

const style={
    marginTop:'20px'
}

export default AppRouter;