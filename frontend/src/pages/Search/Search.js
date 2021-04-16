// import axios from 'axios';
import NavBar from 'components/NavBar/NavBar';
import SideMenu from 'components/SideMenu/SideMenu';
import SearchBar from 'components/SearchBar/SearchBar';
import ResultsList from 'components/ResultsList/ResultsList';

function App() {
	// async function getArtists() {
	//   try {
	//     const response = await axios.get('http://localhost:8080/artists');
	//     console.log(response.data[0].name);
	//     return response.data[0].name;

	//   } catch (error) {
	//     console.error(error);
	//   }
	// }
	// getArtists();

	return (
		<div className="container">
			<aside>
				<SideMenu />
			</aside>
			<main>
				<ResultsList results={'test'} />
			</main>
		</div>
	);
}

export default App;
