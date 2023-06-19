DROP TABLE IF EXISTS bands, gigs, albums, bands_gigs;

CREATE TABLE IF NOT EXISTS bands (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS gigs (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS bands_gigs (
	band_id INT NOT NULL,
	gig_id INT NOT NULL,
	PRIMARY KEY(band_id, gig_id),
	CONSTRAINT fk_band FOREIGN KEY (band_id) REFERENCES bands (id) ON DELETE CASCADE,
	CONSTRAINT fk_gig FOREIGN KEY (gig_id) REFERENCES gigs (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS albums (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100),
	band_id INT NOT NULL,
	CONSTRAINT fk_band FOREIGN KEY (band_id) REFERENCES bands (id) ON DELETE CASCADE
	);
	
INSERT INTO bands (name) VALUES 
('Lamb of God'),
('Dark Tranquillity'),
('Dir en grey'),
('Social Distortion'),
('Parkway Drive');

INSERT INTO gigs (name) VALUES
('Summer Breeze'),
('Wacken Open Air'),
('Rock am Ring'),
('Download Festival'),
('Hammerfest');

INSERT INTO bands_gigs (band_id, gig_id) VALUES 
(1,1), (1,2), (1,3), (1,4), (1,5),
(2,1), (2,2), (2,3), (2,4), (2,5),
(3,1), (3,2), (3,3), (3,4), (3,5),
(4,1), (4,2), (4,3), (4,4), (4,5),
(5,1), (5,2), (5,3), (5,4), (5,5);

INSERT INTO albums (name, band_id) VALUES
('Wrath', 1), ('Sacrament', 1), ('Omens', 1),
('Moment', 2), ('Atoma', 2), ('Fiction', 2),
('Vulgar', 3), ('Uroboros', 3), ('Arche', 3),
('Sex, Love and Rock n Roll', 4), ('Hard Times and Nursery Rhymes', 4), ('White Light, White Heat, White Trash', 4),
('Darker Still', 5), ('IRE', 5), ('Reverence', 5);
