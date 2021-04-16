import React from 'react';
import { Link } from 'react-router-dom';
import { ReactComponent as LogoIcon } from 'assets/logo.svg';
import './css/Logo.scss';

export default function Logo({ extended }) {
	return (
		<Link to="/">
			{extended ? (
				<div className="logo logo--extended">
					<LogoIcon alt="StageSync logo" className="logo__img" />
					<span className="logo__text">StageSync</span>
				</div>
			) : (
				<div className="logo logo--small">
					<LogoIcon alt="StageSync logo" className="logo__img" />
				</div>
			)}
		</Link>
	);
}
