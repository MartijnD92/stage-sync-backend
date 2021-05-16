
INSERT INTO users (username, password, enabled) VALUES ('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);
INSERT INTO users (username, password, enabled) VALUES ('martijn', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO authorities (username, authority) VALUES ('martijn', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('martijn', 'ROLE_ADMIN');

INSERT INTO artist (name, price, has_sound_engineer, genre) VALUES ('The Hillbilly Moonshiners', 1500, true, 'Bluegrass');
INSERT INTO artist (name, price, has_sound_engineer, genre) VALUES ('The Beatles', 5000000, true, 'Pop');

INSERT INTO gig (name, venue, location, date, invoice_status, fee, duration, is_confirmed, has_passed, artist_id)
VALUES ('Zwarte Cross', 'De Schans', 'Lichtenvoorde', '2021-08-30 19:00', 'NOT_SENT', 1500, 90, true, false, 1);

