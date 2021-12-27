-- MySQL Script generated by MySQL Workbench
-- Thứ bảy, 25 Tháng 12 Năm 2021 16:30:25 +07
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema eco_bike_rental
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema eco_bike_rental
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eco_bike_rental` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `eco_bike_rental` ;

-- -----------------------------------------------------
-- Table `eco_bike_rental`.`bike_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`bike_category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `description` VARCHAR(256) NULL DEFAULT NULL,
  `price` BIGINT(20) NOT NULL,
  `image` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `category_id_UNIQUE` (`category_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT(11) NOT NULL,
  `created_at` VARCHAR(256) NOT NULL,
  `updated_at` VARCHAR(256) NOT NULL,
  `is_renting` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`bike_rent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`bike_rent` (
  `rent_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `bike_id` VARCHAR(45) NOT NULL,
  `rent_time` VARCHAR(256) NOT NULL,
  `is_deposited` INT(11) NOT NULL,
  `dock_id` INT(11) NOT NULL,
  PRIMARY KEY (`rent_id`),
  UNIQUE INDEX `rent_id_UNIQUE` (`rent_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `dock_id` (`dock_id` ASC) VISIBLE,
  INDEX `bike_id` (`bike_id` ASC) VISIBLE,
  CONSTRAINT `bike_rent_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `eco_bike_rental`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`bike_return`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`bike_return` (
  `return_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rent_id` INT(11) NOT NULL,
  `return_time` VARCHAR(256) NOT NULL,
  `dock_id` INT(11) NOT NULL,
  `is_paid` INT(11) NOT NULL,
  PRIMARY KEY (`return_id`),
  UNIQUE INDEX `return_id_UNIQUE` (`return_id` ASC) VISIBLE,
  INDEX `rent_id` (`rent_id` ASC) VISIBLE,
  CONSTRAINT `bike_return_ibfk_1`
    FOREIGN KEY (`rent_id`)
    REFERENCES `eco_bike_rental`.`bike_rent` (`rent_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`card_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`card_info` (
  `card_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `card_number` VARCHAR(45) NOT NULL,
  `created_at` VARCHAR(256) NOT NULL,
  `updated_at` VARCHAR(256) NOT NULL,
  `bank` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`card_id`),
  UNIQUE INDEX `card_id_UNIQUE` (`card_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `card_info_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `eco_bike_rental`.`user` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`deposit_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`deposit_transaction` (
  `deposit_transaction_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rent_id` INT(11) NOT NULL,
  `money_amount` BIGINT(20) NOT NULL,
  `created_at` VARCHAR(256) NOT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NOT NULL,
  `deposit_by` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`deposit_transaction_id`),
  UNIQUE INDEX `deposit_transaction_id_UNIQUE` (`deposit_transaction_id` ASC) VISIBLE,
  INDEX `rent_id` (`rent_id` ASC) VISIBLE,
  CONSTRAINT `deposit_transaction_ibfk_1`
    FOREIGN KEY (`rent_id`)
    REFERENCES `eco_bike_rental`.`bike_rent` (`rent_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`dock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`dock` (
  `dock_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `address` VARCHAR(256) NOT NULL,
  `description` VARCHAR(256) NULL DEFAULT NULL,
  `created_at` VARCHAR(256) NOT NULL,
  `updated_at` VARCHAR(256) NOT NULL,
  `province` VARCHAR(256) NULL DEFAULT NULL,
  `image` VARCHAR(256) NOT NULL,
  `point_number` INT(11) NOT NULL,
  PRIMARY KEY (`dock_id`),
  UNIQUE INDEX `dock_id_UNIQUE` (`dock_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`dock_has_bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`dock_has_bike` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `dock_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  `bike_id` INT(11) NOT NULL,
  `description` VARCHAR(256) NULL DEFAULT NULL,
  `status` FLOAT NOT NULL,
  `active` INT(11) NOT NULL,
  `point` INT(11) NOT NULL,
  `barcode` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `dock_id` (`dock_id` ASC) VISIBLE,
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  INDEX `bike_id` (`bike_id` ASC) VISIBLE,
  CONSTRAINT `dock_has_bike_ibfk_1`
    FOREIGN KEY (`dock_id`)
    REFERENCES `eco_bike_rental`.`dock` (`dock_id`),
  CONSTRAINT `dock_has_bike_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `eco_bike_rental`.`bike_category` (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `eco_bike_rental`.`payment_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `eco_bike_rental`.`payment_transaction` (
  `payment_transaction_id` INT(11) NOT NULL AUTO_INCREMENT,
  `rent_id` INT(11) NOT NULL,
  `time` BIGINT(20) NOT NULL,
  `payment` BIGINT(20) NOT NULL,
  `deposit_transaction_id` INT(11) NOT NULL,
  `returned_money` BIGINT(20) NOT NULL,
  `created_time` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`payment_transaction_id`),
  UNIQUE INDEX `payment_transaction_id_UNIQUE` (`payment_transaction_id` ASC) VISIBLE,
  INDEX `rent_id` (`rent_id` ASC) VISIBLE,
  INDEX `deposit_transaction_id` (`deposit_transaction_id` ASC) VISIBLE,
  CONSTRAINT `payment_transaction_ibfk_1`
    FOREIGN KEY (`rent_id`)
    REFERENCES `eco_bike_rental`.`bike_rent` (`rent_id`),
  CONSTRAINT `payment_transaction_ibfk_2`
    FOREIGN KEY (`deposit_transaction_id`)
    REFERENCES `eco_bike_rental`.`deposit_transaction` (`deposit_transaction_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;