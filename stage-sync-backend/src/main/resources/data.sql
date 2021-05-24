
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_MODERATOR');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO app_user(first_name, last_name, username, email, password)
VALUES('admin', 'admin', 'martijn', 'admin@stagesync.nl', '$2a$10$ExlSiTek2JcjonpJp4Ujg.gj8O9pdCLPS09jsHAp2XT1VX2H1/kt.');
INSERT INTO user_role(user_id, role_id) VALUES(1, 1);
INSERT INTO user_role(user_id, role_id) VALUES(1, 3);

INSERT INTO artist(name, price, has_sound_engineer, genre) VALUES ('martijn', 1500, true, 'Bluegrass');
INSERT INTO artist_user(artist_id, user_id) VALUES(1, 1);

INSERT INTO gig(name, artist_name, venue, location, date, invoice_status, fee, duration, confirmation_status, has_passed, tickets_total, tickets_sold)
VALUES ('Zwarte Cross', 'The Hillbilly Moonshiners', 'De Schans', 'Lichtenvoorde', '2021-08-30 19:00', 'NOT_SENT', 1500, 90, 'CONFIRMED', false, 8000, 5000);
INSERT INTO gig_artist(artist_id, gig_id) VALUES(1, 1);

