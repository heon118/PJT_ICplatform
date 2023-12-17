-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Players`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Players`;

CREATE TABLE IF NOT EXISTS `mydb`.`Players` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `birth` DATETIME NULL,
  `sports` VARCHAR(45) NOT NULL,
  `sports_detail` VARCHAR(45) NULL,
  `gold` INT NOT NULL DEFAULT 0,
  `silver` INT NOT NULL DEFAULT 0,
  `bronze` INT NOT NULL DEFAULT 0,
  `intro` VARCHAR(100) NULL DEFAULT '',
  `msg_cnt` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `playerId_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Events`;

CREATE TABLE IF NOT EXISTS `mydb`.`Events` (
  `event_id` INT NOT NULL,
  `Players_id` INT NOT NULL,
  `event_date` DATETIME NOT NULL,
  `content` VARCHAR(100) NOT NULL,
  `result` VARCHAR(100) NULL,
  `expect` INT NULL DEFAULT '0',
  `medal_chance` TINYINT NULL DEFAULT '0',
  PRIMARY KEY (`event_id`, `Players_id`),
  INDEX `fk_Events_Players1_idx` (`Players_id` ASC) VISIBLE,
  CONSTRAINT `fk_Events_Players1`
    FOREIGN KEY (`Players_id`)
    REFERENCES `mydb`.`Players` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Posts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Posts`;

CREATE TABLE IF NOT EXISTS `mydb`.`Posts` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `players_id` INT NOT NULL,
  `post_name` VARCHAR(150) NOT NULL,
  `post_path` VARCHAR(255) NULL,
  `file_size` LONG NULL,
  `upload_date` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`post_id`),
  UNIQUE INDEX `imgId_UNIQUE` (`post_id` ASC) VISIBLE,
  INDEX `fk_Images_Team_idx` (`players_id` ASC) VISIBLE,
  CONSTRAINT `fk_Images_Team`
    FOREIGN KEY (`players_id`)
    REFERENCES `mydb`.`Players` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`FanMsgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`FanMsgs`;

CREATE TABLE IF NOT EXISTS `mydb`.`FanMsgs` (
  `fanmsg_id` INT NOT NULL AUTO_INCREMENT,
  `players_id` INT NOT NULL,
  `nickname` VARCHAR(45) NULL,
  `content` VARCHAR(100) NULL,
  `upload_date` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`fanmsg_id`),
  UNIQUE INDEX `msgId_UNIQUE` (`fanmsg_id` ASC) VISIBLE,
  INDEX `fk_msgs_Team1_idx` (`players_id` ASC) VISIBLE,
  CONSTRAINT `fk_msgs_Team1`
    FOREIGN KEY (`players_id`)
    REFERENCES `mydb`.`Players` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Videos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Videos`;

CREATE TABLE IF NOT EXISTS `mydb`.`Videos` (
  `video_id` VARCHAR(100) NOT NULL,
  `video_title` VARCHAR(200) NOT NULL,
  `category` VARCHAR(45) NULL,
  `upload_date` DATETIME NOT NULL DEFAULT NOW(),
  `video_cnt` INT UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`video_id`),
  UNIQUE INDEX `videoId_UNIQUE` (`video_id` ASC) VISIBLE)
ENGINE = InnoDB;

ALTER TABLE `mydb`.`Videos` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `mydb`.`PlayerMsgs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PlayerMsgs`;

CREATE TABLE IF NOT EXISTS `mydb`.`PlayerMsgs` (
  `msg_id` INT NOT NULL AUTO_INCREMENT,
  `Players_id` INT NOT NULL,
  `content` VARCHAR(100) NULL,
  `upload_date` DATETIME NULL DEFAULT NOW(),
  PRIMARY KEY (`msg_id`),
  UNIQUE INDEX `msgId_UNIQUE` (`msg_id` ASC) VISIBLE,
  INDEX `fk_PlayerMsgs_Players1_idx` (`Players_id` ASC) VISIBLE,
  CONSTRAINT `fk_PlayerMsgs_Players1`
    FOREIGN KEY (`Players_id`)
    REFERENCES `mydb`.`Players` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Coutries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Countries`;

CREATE TABLE IF NOT EXISTS `mydb`.`Countries` (
  `name` VARCHAR(45) NOT NULL,
  `gold` INT NULL DEFAULT 0,
  `silver` INT NULL DEFAULT 0,
  `bronze` INT NULL DEFAULT 0,
  `total_medal` INT NULL,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
