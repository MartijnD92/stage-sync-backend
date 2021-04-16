import React from 'react';
import './css/SideMenu.css';

export default function SideMenu() {
	return (
		<div className="sidemenu">
			<button className="sidemenu__btn sidemenu__dasbhoard-btn">
				Dashboard
			</button>
			<button className="sidemenu__btn sidemenu__profile-btn">Profile</button>
			<button className="sidemenu__btn sidemenu__settings-btn">Settings</button>
		</div>
	);
}
