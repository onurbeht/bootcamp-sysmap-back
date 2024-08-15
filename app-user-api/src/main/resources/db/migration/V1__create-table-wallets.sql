create table wallets (
	id TEXT PRIMARY KEY NOT NULL,
	balance DOUBLE PRECISION NOT NULL,
	points INTEGER NOT NULL,
	last_update DATE NOT NULL,
	owner VARCHAR(255) NOT NULL UNIQUE
);