CREATE TABLE `store`
(
    `id`   int          NOT NULL AUTO_INCREMENT,
    `logo` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `brand`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `country` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `guitar`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `image`      varchar(255) NOT NULL,
    `model_name`       varchar(255) NOT NULL,
    `brand_id`    int   DEFAULT NULL,
    `price`     float  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_guitar_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
);

CREATE TABLE `guitar_store`
(
    `guitar_id` int NOT NULL,
    `store_id`  int NOT NULL,
    PRIMARY KEY (`guitar_id`, `store_id`),
    CONSTRAINT `fk_g` FOREIGN KEY (`guitar_id`) REFERENCES `guitar` (`id`),
    CONSTRAINT `fk_s` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
);

INSERT INTO `store`(`id`, `logo`, `name`)
VALUES ('1', 'https://example.com/logo_gc.png', 'Guitar Center'),
       ('2', 'https://example.com/logo_thomann.png', 'Thomann'),
       ('3', 'https://example.com/logo_ksantypa.png', 'Ksantypa Music');

INSERT INTO `brand`(`id`, `name`, `country`)
VALUES ('1', 'Fender', 'USA'),
       ('2', 'Gibson', 'USA'),
       ('3', 'Ibanez', 'Japan');

INSERT INTO `guitar`(`id`, `image`, `model_name`, `brand_id`, price)
VALUES ('1', 'https://images.com/strat.jpg','Stratocaster', '1', 1000.0),
       ('2', 'https://images.com/tele.jpg', 'Telecaster', '1', 2000.3),
       ('3', 'https://images.com/jazzmaster.jpg', 'Jazzmaster', '1', 1500.0);

INSERT INTO `guitar`(`id`, `image`, `model_name`, `brand_id`, price)
VALUES ('4', 'https://images.com/lp.jpg','Les Paul Standard', '2', 1000.0),
       ('5', 'https://images.com/sg.jpg', 'SG Modern', '2', 2000.3);

INSERT INTO `guitar`(`id`, `image`, `model_name`, `brand_id`, price)
VALUES ('6', 'https://images.com/rg.jpg', 'RG550 Genesis', '3', 2000.3),
       ('7', 'https://images.com/jem.jpg','JEM7V Steve Vai', '3', 1000.0);

INSERT INTO `guitar_store`(`guitar_id`, `store_id`)
VALUES ('1', '1'), ('1', '3'),
       ('2', '3'),
       ('3', '1'), ('3', '2'),
       ('4', '1'), ('4', '3'),
       ('5', '2'), ('5', '3'),
       ('6', '1'),
       ('7', '2');

CREATE TABLE user
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE role
(
    id       int primary key auto_increment,
    username VARCHAR(255),
    role     VARCHAR(255)
);

INSERT INTO user(username, password)
VALUES ('dbuser1', 'dbuser1'),
       ('dbuser2', 'dbuser2'),
       ('dbuser3', 'dbuser3');

INSERT INTO role(username, role)
VALUES ('dbuser1', 'USER_ADMIN'),
       ('dbuser2', 'BRAND_ADMIN'),
       ('dbuser3', 'GUITAR_ADMIN');