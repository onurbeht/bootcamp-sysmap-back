create table albums (
	id TEXT PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	id_spotify TEXT NOT NULL,
	artist TEXT[] NOT NULL,
	image_url TEXT[] NOT NULL,
	value DOUBLE PRECISION NOT NULL,
	user_id TEXT REFERENCES users(id)
);