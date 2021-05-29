
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_MODERATOR');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

-- ADMIN
INSERT INTO app_user(id, first_name, last_name, username, email, password)
VALUES(100, 'admin', 'admin', 'admin', 'admin@stagesync.nl', '$2a$10$ExlSiTek2JcjonpJp4Ujg.gj8O9pdCLPS09jsHAp2XT1VX2H1/kt.');
INSERT INTO user_role(user_id, role_id) VALUES(100, 1);
INSERT INTO user_role(user_id, role_id) VALUES(100, 3);

-- MODERATOR
INSERT INTO app_user(id, first_name, last_name, username, email, password, profile_picture)
VALUES(101, 'mod', 'mod', 'mod', 'mod@stagesync.nl', '$2a$10$ExlSiTek2JcjonpJp4Ujg.gj8O9pdCLPS09jsHAp2XT1VX2H1/kt.', 'marty.JPG');
INSERT INTO user_role(user_id, role_id) VALUES(101, 1);
INSERT INTO user_role(user_id, role_id) VALUES(101, 2);


-- Artist and gigs for ADMIN
INSERT INTO artist(name, price, has_sound_engineer, genre) VALUES ('The Hillbilly Moonshiners', 1500, true, 'Bluegrass');
INSERT INTO artist_user(artist_id, user_id) VALUES(1, 100);

INSERT INTO gig(name, artist_name, room, venue, location, date, invoice_status, fee, duration, confirmation_status, has_passed, tickets_total, tickets_sold)
VALUES ('Zwarte Cross', 'The Hillbilly Moonshiners', 'Mega Tent', 'De Schans', 'Lichtenvoorde', '2021-06-7 19:00', 'NOT_SENT', 1500, 90, 'CONFIRMED', false, 8000, 5000);
INSERT INTO gig_artist(artist_id, gig_id) VALUES(1, 1);
INSERT INTO gig(name, artist_name, room, venue, location, date, invoice_status, fee, duration, confirmation_status, has_passed, tickets_total, tickets_sold)
VALUES ('Zwarte Cross', 'The Hillbilly Moonshiners', 'Mega Tent', 'De Schans', 'Lichtenvoorde', '2020-10-30 19:00', 'SENT', 1500, 90, 'CANCELLED', false, 8000, 5000);
INSERT INTO gig_artist(artist_id, gig_id) VALUES(1, 2);
INSERT INTO gig(name, artist_name, room, venue, location, date, invoice_status, fee, duration, confirmation_status, has_passed, tickets_total, tickets_sold)
VALUES ('Zwarte Cross', 'The Hillbilly Moonshiners', 'Mega Tent', 'De Schans', 'Lichtenvoorde', '2022-07-31 19:00', 'NOT_SENT', 1500, 90, 'PENDING', false, 8000, 5000);
INSERT INTO gig_artist(artist_id, gig_id) VALUES(1, 3);
INSERT INTO gig(name, artist_name, room, venue, location, date, invoice_status, fee, duration, confirmation_status, has_passed, tickets_total, tickets_sold)
VALUES ('Zwarte Cross', 'The Hillbilly Moonshiners', 'Mega Tent', 'De Schans', 'Lichtenvoorde', '2021-08-30 19:00', 'NOT_SENT', 1500, 90, 'CONFIRMED', false, 8000, 5000);
INSERT INTO gig_artist(artist_id, gig_id) VALUES(1, 4);

-- Artist and gigs for MODERATOR
INSERT INTO artist(name, price, has_sound_engineer, genre) VALUES ('The Beatles', 1000000, true, 'Pop');
INSERT INTO artist_user(artist_id, user_id) VALUES(2, 101);

INSERT INTO gig(name, artist_name, venue, location, room, date, invoice_status, fee, duration, confirmation_status, has_passed, tickets_total, tickets_sold)
VALUES ('Live at Wembley', 'The Beatles', 'Wembley Stadium', 'London', 'Empire Pool', '1965-04-11 19:00', 'SENT', 100000, 90, 'CONFIRMED', true, 12500, 12500);
INSERT INTO gig_artist(artist_id, gig_id) VALUES(2, 5);