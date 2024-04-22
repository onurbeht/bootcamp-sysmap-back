CREATE TABLE wallets (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    balance FLOAT NOT NULL,
    points INT,
    last_update TEXT NOT NULL,
    owner TEXT UNIQUE NOT NULL
);