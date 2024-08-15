CREATE table users (
	id TEXT PRIMARY KEY NOT NULL,
	username VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password TEXT NOT NULL,
	role VARCHAR(20) NOT NULL,
	wallet_id TEXT REFERENCES wallets(id)
);