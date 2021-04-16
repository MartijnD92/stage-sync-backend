import React from 'react';
import Button from 'components/Button/Button';
import { ReactComponent as Spotlight } from 'assets/spotlight.svg';
import './css/Home.scss';

export default function Home() {
	return (
		<main>
			<div className="teaser">
				<div className="teaser--left">
					<h1 className="teaser__title">StageSync knows about your gigs</h1>
					<p className="teaser__subtitle">
						A smart booking system for booking agencies and artists that does
						all the work for you.
					</p>
					<Button type={'primary'} link={'/signup'}>
						Try it now
					</Button>
				</div>
				<div className="teaser--right">
					<Spotlight className="spotlight" />
				</div>
			</div>
		</main>
	);
}
