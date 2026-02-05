import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import OrderListPage from './OrderListPage';
import OrderDetailPage from './OrderDetailPage';

/**
 * Main application component that sets up routing.
 */
const App = () => {
    return (
        <Router>
            <Switch>
                <Route path='/' exact component={OrderListPage} />
                <Route path='/orders/:id' component={OrderDetailPage} />
            </Switch>
        </Router>
    );
};

export default App;