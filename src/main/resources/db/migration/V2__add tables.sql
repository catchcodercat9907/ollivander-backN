--
-- Table structure for table `category`
--
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `parent_id` int(11) DEFAULT NULL,
                            `title` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
                            `metaTitle` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `slug` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                            `content` longtext COLLATE utf8mb4_unicode_ci,
                            PRIMARY KEY (`id`),
                            KEY `idx_category_parent` (`parent_id`),
                            CONSTRAINT `fk_category_parent` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `tag`
--
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `title` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `metaTitle` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                       `slug` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `content` longtext COLLATE utf8mb4_unicode_ci,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `product`
--
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `account_id` int(11) NOT NULL,
                           `title` varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `metaTitle` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `slug` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `summary` tinytext COLLATE utf8mb4_unicode_ci,
                           `type` smallint(6) NOT NULL DEFAULT '0',
                           `sku` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                           `price` float NOT NULL DEFAULT '0',
                           `discount` float NOT NULL DEFAULT '0',
                           `quantity` smallint(6) NOT NULL DEFAULT '0',
                           `shop` tinyint(1) NOT NULL DEFAULT '0',
                           `createdAt` datetime NOT NULL,
                           `updatedAt` datetime DEFAULT NULL,
                           `publishedAt` datetime DEFAULT NULL,
                           `startsAt` datetime DEFAULT NULL,
                           `endsAt` datetime DEFAULT NULL,
                           `content` longtext COLLATE utf8mb4_unicode_ci,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uq_slug` (`slug`),
                           KEY `idx_product_account` (`account_id`),
                           CONSTRAINT `fk_product_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `product_category`
--
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
                                    `product_id` int(11) NOT NULL,
                                    `category_id` int(11) NOT NULL,
                                    PRIMARY KEY (`product_id`,`category_id`),
                                    KEY `idx_pc_category` (`category_id`),
                                    KEY `idx_pc_product` (`product_id`),
                                    CONSTRAINT `fk_pc_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
                                    CONSTRAINT `fk_pc_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `product_meta`
--
DROP TABLE IF EXISTS `product_meta`;
CREATE TABLE `product_meta` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `product_id` int(11) NOT NULL,
                                `key` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                                `content` longtext COLLATE utf8mb4_unicode_ci,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uq_product_meta` (`product_id`,`key`),
                                KEY `idx_meta_product` (`product_id`),
                                CONSTRAINT `fk_meta_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `product_review`
--
DROP TABLE IF EXISTS `product_review`;
CREATE TABLE `product_review` (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `product_id` int(11) NOT NULL,
                                  `parent_id` int(11) DEFAULT NULL,
                                  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                  `rating` smallint(6) NOT NULL DEFAULT '0',
                                  `published` tinyint(1) NOT NULL DEFAULT '0',
                                  `createdAt` datetime NOT NULL,
                                  `publishedAt` datetime DEFAULT NULL,
                                  `content` longtext COLLATE utf8mb4_unicode_ci,
                                  PRIMARY KEY (`id`),
                                  KEY `idx_review_product` (`product_id`),
                                  KEY `idx_review_parent` (`parent_id`),
                                  CONSTRAINT `fk_review_parent` FOREIGN KEY (`parent_id`) REFERENCES `product_review` (`id`),
                                  CONSTRAINT `fk_review_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `product_tag`
--
DROP TABLE IF EXISTS `product_tag`;
CREATE TABLE `product_tag` (
                               `product_id` int(11) NOT NULL,
                               `tag_id` int(11) NOT NULL,
                               PRIMARY KEY (`product_id`,`tag_id`),
                               KEY `idx_pt_tag` (`tag_id`),
                               KEY `idx_pt_product` (`product_id`),
                               CONSTRAINT `fk_pt_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
                               CONSTRAINT `fk_pt_tag` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `cart	`
--
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `account_id` int(11) DEFAULT NULL,
                        `session_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `status` smallint(6) NOT NULL DEFAULT '0',
                        `createdAt` datetime NOT NULL,
                        `updatedAt` datetime DEFAULT NULL,
                        `content` longtext COLLATE utf8mb4_unicode_ci,
                        PRIMARY KEY (`id`),
                        KEY `idx_cart_account` (`account_id`),
                        CONSTRAINT `fk_cart_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `cart_item`
--
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE `cart_item` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `product_id` int(11) NOT NULL,
                             `cart_id` int(11) NOT NULL,
                             `sku` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                             `price` float NOT NULL DEFAULT '0',
                             `discount` float NOT NULL DEFAULT '0',
                             `quantity` smallint(6) NOT NULL DEFAULT '0',
                             `active` tinyint(1) NOT NULL DEFAULT '0',
                             `createdAt` datetime NOT NULL,
                             `updatedAt` datetime DEFAULT NULL,
                             `content` longtext COLLATE utf8mb4_unicode_ci,
                             PRIMARY KEY (`id`),
                             KEY `idx_cart_item_product` (`product_id`),
                             KEY `idx_cart_item_cart` (`cart_id`),
                             CONSTRAINT `fk_cart_item_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
                             CONSTRAINT `fk_cart_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `orders`
--
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `account_id` int(11) DEFAULT NULL,
                         `session_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `status` smallint(6) NOT NULL DEFAULT '0',
                         `subTotal` float NOT NULL DEFAULT '0',
                         `itemDiscount` float NOT NULL DEFAULT '0',
                         `tax` float NOT NULL DEFAULT '0',
                         `shipping` float NOT NULL DEFAULT '0',
                         `total` float NOT NULL DEFAULT '0',
                         `promo` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `discount` float NOT NULL DEFAULT '0',
                         `grandTotal` float NOT NULL DEFAULT '0',
                         `createdAt` datetime NOT NULL,
                         `updatedAt` datetime DEFAULT NULL,
                         `content` longtext COLLATE utf8mb4_unicode_ci,
                         PRIMARY KEY (`id`),
                         KEY `idx_orders_account` (`account_id`),
                         CONSTRAINT `fk_orders_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `orders_item`
--
DROP TABLE IF EXISTS `orders_item`;
CREATE TABLE `orders_item` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `product_id` int(11) NOT NULL,
                              `orders_id` int(11) NOT NULL,
                              `sku` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `price` float NOT NULL DEFAULT '0',
                              `discount` float NOT NULL DEFAULT '0',
                              `quantity` smallint(6) NOT NULL DEFAULT '0',
                              `createdAt` datetime NOT NULL,
                              `updatedAt` datetime DEFAULT NULL,
                              `content` longtext COLLATE utf8mb4_unicode_ci,
                              PRIMARY KEY (`id`),
                              KEY `idx_orders_item_product` (`product_id`),
                              KEY `idx_orders_item_orders` (`orders_id`),
                              CONSTRAINT `fk_orders_item_orders` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
                              CONSTRAINT `fk_orders_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `transaction`
--
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `account_id` int(11) NOT NULL,
                               `orders_id` int(11) NOT NULL,
                               `code` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `type` smallint(6) NOT NULL DEFAULT '0',
                               `mode` smallint(6) NOT NULL DEFAULT '0',
                               `status` smallint(6) NOT NULL DEFAULT '0',
                               `createdAt` datetime NOT NULL,
                               `updatedAt` datetime DEFAULT NULL,
                               `content` longtext COLLATE utf8mb4_unicode_ci,
                               PRIMARY KEY (`id`),
                               KEY `idx_transaction_account` (`account_id`),
                               KEY `idx_transaction_orders` (`orders_id`),
                               CONSTRAINT `fk_transaction_orders` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
                               CONSTRAINT `fk_transaction_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `account_info`
--
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `account_id` int(11) NOT NULL,
                                `firstName` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `middleName` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `lastName` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `mobile` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `line1` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `line2` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `city` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `province` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `country` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `registeredAt` datetime NOT NULL,
                                `intro` tinytext COLLATE utf8mb4_unicode_ci,
                                `profile` longtext COLLATE utf8mb4_unicode_ci,
                                PRIMARY KEY (`id`),
                                KEY `idx_account_info_account` (`account_id`),
                                UNIQUE KEY `uq_mobile` (`mobile`),
                                UNIQUE KEY `uq_email` (`email`),
                                CONSTRAINT `fk_account_info_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
