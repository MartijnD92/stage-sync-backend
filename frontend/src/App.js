import React from 'react';
import { Switch, Route } from 'react-router-dom';
import NavBar from 'components/NavBar/NavBar';
import Home from 'pages/Home/Home';
import Search from 'pages/Search/Search';
import './css/App.scss';

function App() {
	return (
		<>
			<header>
				<NavBar />
			</header>
			<Switch>
				<Route exact path="/">
					<Home />
				</Route>
				<Route path="/search">
					<Search />
				</Route>
			</Switch>
		</>
	);
}

export default App;
