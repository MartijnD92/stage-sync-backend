import React from 'react';
import DefaultProfile from 'assets/default-profile.jpg';
import './css/ProfilePicture.scss';

export default function ProfilePicture({ defaultPicture }) {
	return (
		<>{defaultPicture && <img src={DefaultProfile} alt="default profile" />}</>
	);
}
