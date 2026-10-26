CREATE DATABASE orders;
USE orders;

CREATE TABLE drivers
(
	id VARCHAR(36) NOT NULL,
	name VARCHAR(50) NOT NULL,
    license_number VARCHAR(20) NOT NULL,
    active TINYINT(1) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    CONSTRAINT pk_id_driver PRIMARY KEY(id)
)


CREATE TABLE orders
(
	id VARCHAR(36) NOT NULL,
	status VARCHAR(20) NOT NULL,
    origin VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    id_driver VARCHAR(36) NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    constraint pk_id_order PRIMARY KEY(id),
    CONSTRAINT fk_id_driver FOREIGN KEY (id_driver) REFERENCES drivers(id)
)
