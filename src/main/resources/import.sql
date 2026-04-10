INSERT INTO `store`(`id`, `logo`, `name`) VALUES ('1', 'https://example.com/logo_gc.png', 'Guitar Center'), ('2', 'https://example.com/logo_thomann.png', 'Thomann'), ('3', 'https://example.com/logo_ksantypa.png', 'Ksantypa Music');

INSERT INTO `brand`(`id`, `name`, `country`) VALUES ('1', 'Fender', 'USA'), ('2', 'Gibson', 'USA'), ('3', 'Ibanez', 'Japan');

INSERT INTO `guitar`(`id`, `image`, `model_name`, `brand_id`, price) VALUES ('1', 'https://images.com/strat.jpg','Stratocaster', '1', 1000.0), ('2', 'https://images.com/tele.jpg', 'Telecaster', '1', 2000.3), ('3', 'https://images.com/jazzmaster.jpg', 'Jazzmaster', '1', 1500.0);

INSERT INTO `guitar`(`id`, `image`, `model_name`, `brand_id`, price) VALUES ('4', 'https://images.com/lp.jpg','Les Paul Standard', '2', 1000.0), ('5', 'https://images.com/sg.jpg', 'SG Modern', '2', 2000.3);

INSERT INTO `guitar`(`id`, `image`, `model_name`, `brand_id`, price) VALUES ('6', 'https://images.com/rg.jpg', 'RG550 Genesis', '3', 2000.3), ('7', 'https://images.com/jem.jpg','JEM7V Steve Vai', '3', 1000.0);

INSERT INTO `guitar_store`(`guitar_id`, `store_id`) VALUES ('1', '1'), ('1', '3'), ('2', '3'), ('3', '1'), ('3', '2'), ('4', '1'), ('4', '3'), ('5', '2'), ('5', '3'), ('6', '1'), ('7', '2');