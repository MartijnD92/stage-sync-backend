import React from 'react';

export default function SearchBar() {
	return (
		<div className="search-bar">
			<input
				className="search-bar__input"
				type="text"
				name="search"
				id="search"
				placeholder="Search..."
			/>
			<button className="search-bar__btn" type="submit">
				search
			</button>
		</div>
	);
}
