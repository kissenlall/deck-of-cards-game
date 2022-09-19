CREATE TABLE game (
    id UUID NOT NULL,
    shoe jsonb,
    players jsonb,
    status VARCHAR(20),
    PRIMARY KEY (id)
);