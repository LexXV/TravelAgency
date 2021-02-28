-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema travel_agency_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `travel_agency_db` ;

-- -----------------------------------------------------
-- Schema travel_agency_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `travel_agency_db` DEFAULT CHARACTER SET utf8 ;
USE `travel_agency_db` ;

-- -----------------------------------------------------
-- Table `travel_agency_db`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`city` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE INDEX `city_UNIQUE` (`city` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`order_status` (
  `order_status_id` INT NOT NULL DEFAULT 1,
  `order_status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`order_status_id`),
  UNIQUE INDEX `order_status_UNIQUE` (`order_status` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`tour_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`tour_status` (
  `tour_status_id` INT NOT NULL DEFAULT 1,
  `tour_status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tour_status_id`),
  UNIQUE INDEX `tour_status_UNIQUE` (`tour_status` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`tour_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`tour_type` (
  `tour_type_id` INT NOT NULL AUTO_INCREMENT,
  `tour_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tour_type_id`),
  UNIQUE INDEX `tour_type_UNIQUE` (`tour_type` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`transport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`transport` (
  `transport_id` INT NOT NULL AUTO_INCREMENT,
  `transport` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`transport_id`),
  UNIQUE INDEX `transport_UNIQUE` (`transport` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`hotel` (
  `hotel_id` INT NOT NULL AUTO_INCREMENT,
  `hotel` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE INDEX `hotel_UNIQUE` (`hotel` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`tourist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`tourist` (
  `tourist_id` INT NOT NULL AUTO_INCREMENT,
  `tourist` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tourist_id`),
  UNIQUE INDEX `tourist_UNIQUE` (`tourist` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`tour_discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`tour_discount` (
  `tour_discount_id` INT NOT NULL DEFAULT 4,
  `tour_discount_size` DOUBLE NOT NULL DEFAULT 2.5,
  PRIMARY KEY (`tour_discount_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`tour`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`tour` (
  `tour_id` INT NOT NULL AUTO_INCREMENT,
  `tour_name` VARCHAR(45) NOT NULL,
  `tour_cost` DOUBLE NOT NULL,
  `tour_departure_date` DATE NOT NULL,
  `tour_days` INT NOT NULL,
  `tour_places` INT NOT NULL,
  `tour_type_id` INT NOT NULL,
  `tour_city_id` INT NOT NULL,
  `tour_departure_city_id` INT NOT NULL,
  `tour_transport_id` INT NOT NULL,
  `tour_hotel_id` INT NOT NULL,
  `tour_tourist_id` INT NOT NULL,
  `tour_status_id` INT NOT NULL DEFAULT 1,
  `tour_discount_id` INT NOT NULL DEFAULT 4,
  PRIMARY KEY (`tour_id`),
  INDEX `fk_tour_city1_idx` (`tour_city_id` ASC) VISIBLE,
  INDEX `fk_tour_tour_type1_idx` (`tour_type_id` ASC) VISIBLE,
  INDEX `fk_tour_transport1_idx` (`tour_transport_id` ASC) VISIBLE,
  INDEX `fk_tour_tour_status1_idx` (`tour_status_id` ASC) VISIBLE,
  INDEX `fk_tour_hotel1_idx` (`tour_hotel_id` ASC) VISIBLE,
  INDEX `fk_tour_tourist1_idx` (`tour_tourist_id` ASC) VISIBLE,
  INDEX `fk_tour_tour_discount1_idx` (`tour_discount_id` ASC) VISIBLE,
  CONSTRAINT `fk_tour_city`
    FOREIGN KEY (`tour_city_id`)
    REFERENCES `travel_agency_db`.`city` (`city_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_tour_status`
    FOREIGN KEY (`tour_status_id`)
    REFERENCES `travel_agency_db`.`tour_status` (`tour_status_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_tour_type`
    FOREIGN KEY (`tour_type_id`)
    REFERENCES `travel_agency_db`.`tour_type` (`tour_type_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_transport`
    FOREIGN KEY (`tour_transport_id`)
    REFERENCES `travel_agency_db`.`transport` (`transport_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_hotel`
    FOREIGN KEY (`tour_hotel_id`)
    REFERENCES `travel_agency_db`.`hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_tourist`
    FOREIGN KEY (`tour_tourist_id`)
    REFERENCES `travel_agency_db`.`tourist` (`tourist_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tour_tour_discount`
    FOREIGN KEY (`tour_discount_id`)
    REFERENCES `travel_agency_db`.`tour_discount` (`tour_discount_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`user_discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`user_discount` (
  `user_discount_id` INT NOT NULL DEFAULT 4,
  `user_discount_size` DOUBLE NOT NULL DEFAULT 0.98,
  PRIMARY KEY (`user_discount_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`user_role` (
  `user_role_id` INT NOT NULL DEFAULT 1,
  `user_role` VARCHAR(45) NOT NULL DEFAULT 'CLIENT',
  PRIMARY KEY (`user_role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_login` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `user_surname` VARCHAR(45) NOT NULL,
  `user_discount_id` INT NOT NULL DEFAULT 4,
  `user_cash` DOUBLE NOT NULL DEFAULT 1.0,
  `user_phone` VARCHAR(45) NULL,
  `user_role_id` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_login_UNIQUE` (`user_login` ASC) VISIBLE,
  INDEX `fk_user_user_discount1_idx` (`user_discount_id` ASC) VISIBLE,
  INDEX `fk_user_user_role1_idx` (`user_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_discount`
    FOREIGN KEY (`user_discount_id`)
    REFERENCES `travel_agency_db`.`user_discount` (`user_discount_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `travel_agency_db`.`user_role` (`user_role_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `travel_agency_db`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `travel_agency_db`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `tour_id` INT NOT NULL,
  `order_status_id` INT NOT NULL DEFAULT 1,
  `tour_number` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC) VISIBLE,
  INDEX `fk_order_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_order_tour1_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_order_order_status1_idx` (`order_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_order_status`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `travel_agency_db`.`order_status` (`order_status_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_tour`
    FOREIGN KEY (`tour_id`)
    REFERENCES `travel_agency_db`.`tour` (`tour_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `travel_agency_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`city`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`city` (`city_id`, `city`) VALUES (DEFAULT, 'Kharkov');
INSERT INTO `travel_agency_db`.`city` (`city_id`, `city`) VALUES (DEFAULT, 'Krakov');
INSERT INTO `travel_agency_db`.`city` (`city_id`, `city`) VALUES (DEFAULT, 'Kiev');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`order_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`order_status` (`order_status_id`, `order_status`) VALUES (1, 'ACTIVE');
INSERT INTO `travel_agency_db`.`order_status` (`order_status_id`, `order_status`) VALUES (2, 'BOUGHT');
INSERT INTO `travel_agency_db`.`order_status` (`order_status_id`, `order_status`) VALUES (3, 'CANCELED');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`tour_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`tour_status` (`tour_status_id`, `tour_status`) VALUES (1, 'ACTUAL');
INSERT INTO `travel_agency_db`.`tour_status` (`tour_status_id`, `tour_status`) VALUES (2, 'HOT');
INSERT INTO `travel_agency_db`.`tour_status` (`tour_status_id`, `tour_status`) VALUES (3, 'ARCHIVAL');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`tour_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`tour_type` (`tour_type_id`, `tour_type`) VALUES (DEFAULT, 'Excursion');
INSERT INTO `travel_agency_db`.`tour_type` (`tour_type_id`, `tour_type`) VALUES (DEFAULT, 'Shopping');
INSERT INTO `travel_agency_db`.`tour_type` (`tour_type_id`, `tour_type`) VALUES (DEFAULT, 'Rest');
INSERT INTO `travel_agency_db`.`tour_type` (`tour_type_id`, `tour_type`) VALUES (DEFAULT, 'Romantic Dating');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`transport`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`transport` (`transport_id`, `transport`) VALUES (DEFAULT, 'Bus');
INSERT INTO `travel_agency_db`.`transport` (`transport_id`, `transport`) VALUES (DEFAULT, 'Train');
INSERT INTO `travel_agency_db`.`transport` (`transport_id`, `transport`) VALUES (DEFAULT, 'Ship');
INSERT INTO `travel_agency_db`.`transport` (`transport_id`, `transport`) VALUES (DEFAULT, 'Plane');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`hotel`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`hotel` (`hotel_id`, `hotel`) VALUES (DEFAULT, '1*');
INSERT INTO `travel_agency_db`.`hotel` (`hotel_id`, `hotel`) VALUES (DEFAULT, '2*');
INSERT INTO `travel_agency_db`.`hotel` (`hotel_id`, `hotel`) VALUES (DEFAULT, '3*');
INSERT INTO `travel_agency_db`.`hotel` (`hotel_id`, `hotel`) VALUES (DEFAULT, '4*');
INSERT INTO `travel_agency_db`.`hotel` (`hotel_id`, `hotel`) VALUES (DEFAULT, '5*');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`tourist`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`tourist` (`tourist_id`, `tourist`) VALUES (DEFAULT, '1 tourist');
INSERT INTO `travel_agency_db`.`tourist` (`tourist_id`, `tourist`) VALUES (DEFAULT, '2 tourists');
INSERT INTO `travel_agency_db`.`tourist` (`tourist_id`, `tourist`) VALUES (DEFAULT, '3 tourists');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`tour_discount`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`tour_discount` (`tour_discount_id`, `tour_discount_size`) VALUES (1, 0.875);
INSERT INTO `travel_agency_db`.`tour_discount` (`tour_discount_id`, `tour_discount_size`) VALUES (2, 0.9);
INSERT INTO `travel_agency_db`.`tour_discount` (`tour_discount_id`, `tour_discount_size`) VALUES (3, 0.95);
INSERT INTO `travel_agency_db`.`tour_discount` (`tour_discount_id`, `tour_discount_size`) VALUES (4, 0.975);

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`tour`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`tour` (`tour_id`, `tour_name`, `tour_cost`, `tour_departure_date`, `tour_days`, `tour_places`, `tour_type_id`, `tour_city_id`, `tour_departure_city_id`, `tour_transport_id`, `tour_hotel_id`, `tour_tourist_id`, `tour_status_id`, `tour_discount_id`) VALUES (DEFAULT, 'Wonderful history', 9000.0, '2021-03-03', 3, 15, 1, 2, 1, 1, 4, 1, 1, 3);
INSERT INTO `travel_agency_db`.`tour` (`tour_id`, `tour_name`, `tour_cost`, `tour_departure_date`, `tour_days`, `tour_places`, `tour_type_id`, `tour_city_id`, `tour_departure_city_id`, `tour_transport_id`, `tour_hotel_id`, `tour_tourist_id`, `tour_status_id`, `tour_discount_id`) VALUES (DEFAULT, 'Weekend shopping', 7500.0, '2021-03-06', 2, 13, 2, 2, 3, 1, 3, 1, 1, 1);
INSERT INTO `travel_agency_db`.`tour` (`tour_id`, `tour_name`, `tour_cost`, `tour_departure_date`, `tour_days`, `tour_places`, `tour_type_id`, `tour_city_id`, `tour_departure_city_id`, `tour_transport_id`, `tour_hotel_id`, `tour_tourist_id`, `tour_status_id`, `tour_discount_id`) VALUES (DEFAULT, 'Romantic story', 10000.0, '2021-03-04', 4, 10, 4, 2, 1, 2, 5, 2, 1, 4);
INSERT INTO `travel_agency_db`.`tour` (`tour_id`, `tour_name`, `tour_cost`, `tour_departure_date`, `tour_days`, `tour_places`, `tour_type_id`, `tour_city_id`, `tour_departure_city_id`, `tour_transport_id`, `tour_hotel_id`, `tour_tourist_id`, `tour_status_id`, `tour_discount_id`) VALUES (DEFAULT, 'Great fishing', 2000, '2021-02-23', 1, 8, 3, 2, 1, 1, 4, 1, 3, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`user_discount`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`user_discount` (`user_discount_id`, `user_discount_size`) VALUES (1, 0.85);
INSERT INTO `travel_agency_db`.`user_discount` (`user_discount_id`, `user_discount_size`) VALUES (2, 0.9);
INSERT INTO `travel_agency_db`.`user_discount` (`user_discount_id`, `user_discount_size`) VALUES (3, 0.95);
INSERT INTO `travel_agency_db`.`user_discount` (`user_discount_id`, `user_discount_size`) VALUES (4, 0.98);

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`user_role` (`user_role_id`, `user_role`) VALUES (1, 'CLIENT');
INSERT INTO `travel_agency_db`.`user_role` (`user_role_id`, `user_role`) VALUES (2, 'ADMIN');
INSERT INTO `travel_agency_db`.`user_role` (`user_role_id`, `user_role`) VALUES (3, 'BLOCKED');
INSERT INTO `travel_agency_db`.`user_role` (`user_role_id`, `user_role`) VALUES (4, 'MANAGER');

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`user` (`user_id`, `user_login`, `user_password`, `user_name`, `user_surname`, `user_discount_id`, `user_cash`, `user_phone`, `user_role_id`) VALUES (DEFAULT, 'admin', 'admin1', 'Admin', 'Admin', DEFAULT, DEFAULT, '0661111111', 2);
INSERT INTO `travel_agency_db`.`user` (`user_id`, `user_login`, `user_password`, `user_name`, `user_surname`, `user_discount_id`, `user_cash`, `user_phone`, `user_role_id`) VALUES (DEFAULT, 'tf300t', '54354l', 'Alex', 'Voloshin', DEFAULT, 10000.0, '0501111111', DEFAULT);
INSERT INTO `travel_agency_db`.`user` (`user_id`, `user_login`, `user_password`, `user_name`, `user_surname`, `user_discount_id`, `user_cash`, `user_phone`, `user_role_id`) VALUES (DEFAULT, 'ghf', '345ghf', 'Igor', 'Vishnyakov', DEFAULT, DEFAULT, '0671111111', 4);
INSERT INTO `travel_agency_db`.`user` (`user_id`, `user_login`, `user_password`, `user_name`, `user_surname`, `user_discount_id`, `user_cash`, `user_phone`, `user_role_id`) VALUES (DEFAULT, 'admin2', 'admin2', 'Админ', 'Админ', DEFAULT, DEFAULT, '0577111111', 2);
INSERT INTO `travel_agency_db`.`user` (`user_id`, `user_login`, `user_password`, `user_name`, `user_surname`, `user_discount_id`, `user_cash`, `user_phone`, `user_role_id`) VALUES (DEFAULT, 'user', 'user11', 'User', 'User', DEFAULT, DEFAULT, '0911111111', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `travel_agency_db`.`order`
-- -----------------------------------------------------
START TRANSACTION;
USE `travel_agency_db`;
INSERT INTO `travel_agency_db`.`order` (`order_id`, `user_id`, `tour_id`, `order_status_id`, `tour_number`, `price`) VALUES (DEFAULT, 2, 1, DEFAULT, 1, 8820.0);
INSERT INTO `travel_agency_db`.`order` (`order_id`, `user_id`, `tour_id`, `order_status_id`, `tour_number`, `price`) VALUES (DEFAULT, 2, 1, 2, 1, 8820.0);

COMMIT;

