import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import OrderList from './OrderList';
import OrderDetail from './OrderDetail';
import CreateOrder from './CreateOrder';

const App = () => {
    return (
        <Router>
            <Switch>
                <Route path="/create" component={CreateOrder} />
                <Route path="/:id" component={OrderDetail} />
                <Route path="/" component={OrderList} />
            </Switch>
        </Router>
    );
};

export default App;