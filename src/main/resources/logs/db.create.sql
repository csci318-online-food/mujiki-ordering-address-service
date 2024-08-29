CREATE TABLE address (
     id UUID PRIMARY KEY,
     user_id varchar(64),
     restaurant_id varchar(64),
     street VARCHAR(255),
     suburb VARCHAR(255),
     state VARCHAR(255),
     postcode VARCHAR(10),
     country VARCHAR(255),
     create_at timestamp,
     modify_at timestamp,
     modify_by varchar(64),
     create_by varchar(64)
);

