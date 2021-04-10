-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema spendingdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `spendingdb` ;

-- -----------------------------------------------------
-- Schema spendingdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spendingdb` DEFAULT CHARACTER SET utf8 ;
USE `spendingdb` ;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `explanation` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `purchase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `purchase` ;

CREATE TABLE IF NOT EXISTS `purchase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(15,2) NOT NULL DEFAULT 0.0,
  `notes` VARCHAR(200) NULL,
  `category_id` INT NOT NULL,
  `datetime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_purchase_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_purchase_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
-- DROP USER IF EXISTS spendinguser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'spendinguser'@'localhost' IDENTIFIED BY 'spendinguser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'spendinguser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `spendingdb`;
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (1, 'Groceries Essential', 'Groceries and other essental food purchased at the lowest price in your area');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (2, 'In-Restaurant Food and Drink', 'Dining in, separate from carryout because of dine-in taxes, tipping and overpriced alcohol');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (3, 'Booze and Inhalable Herbs', 'Entertainment substances/herbs/chemicals bought at a fair market price');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (4, 'Transportation Essential', 'Car payments, fuel, maintenance and/or rideshare related to work, medical and other critical commutes');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (5, 'Transportation Non-Essential', 'Transportation costs not linked to income such as rideshare to/from the bar, performance upgrades, second vehicle, owning a car when it is not really needed, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (6, 'Restaurant Takeout/Delivery', 'Carry-out orders, Doordash/UberEats, pizza delivery, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (7, 'Rent/Mortage/Home Repairs', 'Primary residence cost');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (8, 'Utilities, Essential', 'Water, sewer, trash, heat, electricity, insurance, internet (if related to income)');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (9, 'Subscriptions, Non-Essential', 'Gym Membership, Amazon Prime, Netflix, broadband internet when cellular tether is available, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (10, 'Clothing, Essential', 'Minimum needed clothing for occupation, staying warm during winter, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (11, 'Clothing, Discretionary', 'Clothing purchases for people making a living wage often falls in this category.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (12, 'Electronics, Discretionary', 'New phone when not really needed, gaming computer upgrade, headphones, hobbyist gadgets, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (13, 'Misc, Essential', 'Equipment necessary for work, travel costs for work, hospital visit, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (14, 'Misc, Discretionary', 'Unnecessary home upgrades, travel costs for vacation, hobby equipment, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (15, 'Pet Costs, Essential', 'Food, vet bills, heartworm meds, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (16, 'Pet Costs, Discretionary', 'Toys, treats, clothing, etc.');
INSERT INTO `category` (`id`, `name`, `explanation`) VALUES (17, 'Household Products, Essential', 'Toilet Paper, Cleaning Products, etc.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `purchase`
-- -----------------------------------------------------
START TRANSACTION;
USE `spendingdb`;
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (1, 129.99, 'groceries', 1, '2020-05-24 22:53:30');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (2, 20, 'gas', 4, '2020-05-24 22:54:33');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (3, 9.99, 'six-pack IPA', 3, '2020-05-25 01:10:47');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (4, 245, 'Car payment', 4, '2020-05-25 02:19:23');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (5, 650, 'Rent', 7, '2020-05-25 03:36:50');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (6, 16.53, 'Doordash Chipotle', 6, '2020-05-25 04:01:32');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (7, 65.50, 'Broadband internet', 9, '2020-05-25 04:06:21');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (8, 80, 'My utility split', 8, '2020-05-26 03:14:15');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (9, 16, 'Uber to downtown', 5, '2020-05-26 03:44:10');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (10, 84.35, 'Food and drink at BrewHahaus', 2, '2020-05-26 04:14:29');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (11, 24, 'Lyft home from downtown', 5, '2020-05-26 04:46:23');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (12, 28.34, 'Cat food and flea collar', 15, '2020-05-26 05:47:12');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (13, 39.87, 'Cool cat backpack with window', 16, '2020-05-26 17:16:07');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (14, 9.32, 'Walgreens meds', 13, '2020-05-26 19:21:44');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (15, 9.99, 'Spotify subscription', 9, '2020-05-26 20:01:09');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (16, 14.99, 'Amazon Prime', 9, '2020-05-26 20:27:02');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (17, 35, 'Gym pass', 9, '2020-05-26 21:13:46');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (18, 89.54, 'Groceries', 1, '2014-05-26 22:38:10');
INSERT INTO `purchase` (`id`, `amount`, `notes`, `category_id`, `datetime`) VALUES (19, 34.99, 'Oil change', 4, '2014-05-26 23:22:55');

COMMIT;
