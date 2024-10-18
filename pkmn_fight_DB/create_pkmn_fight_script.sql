-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: pkmn_fight
-- Source Schemata: pkmn_fight
-- Created: Fri Oct 18 12:05:00 2024
-- Workbench Version: 8.0.38
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema pkmn_fight
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `pkmn_fight` ;
CREATE SCHEMA IF NOT EXISTS `pkmn_fight` ;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.move
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`move` (
  `move_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `damage` INT NOT NULL,
  `type_id` INT NOT NULL,
  PRIMARY KEY (`move_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_move_type1_idx` (`type_id` ASC) ,
  CONSTRAINT `fk_move_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `pkmn_fight`.`type` (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.moveset
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`moveset` (
  `move_id` INT NOT NULL,
  `pokedex_number` INT NOT NULL,
  PRIMARY KEY (`move_id`, `pokedex_number`),
  INDEX `fk_move_has_pokedex_pokedex1_idx` (`pokedex_number` ASC) ,
  INDEX `fk_move_has_pokedex_move1_idx` (`move_id` ASC) ,
  CONSTRAINT `fk_move_has_pokedex_move1`
    FOREIGN KEY (`move_id`)
    REFERENCES `pkmn_fight`.`move` (`move_id`),
  CONSTRAINT `fk_move_has_pokedex_pokedex1`
    FOREIGN KEY (`pokedex_number`)
    REFERENCES `pkmn_fight`.`pokedex` (`pokedex_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.pokedex
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`pokedex` (
  `pokedex_number` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `hp` INT UNSIGNED NOT NULL,
  `att` INT UNSIGNED NOT NULL,
  `def` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`pokedex_number`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.trainer
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`trainer` (
  `trainer_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`trainer_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.trainer_team
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`trainer_team` (
  `pokedex_number` INT NOT NULL,
  `trainer_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pokedex_number`, `trainer_id`),
  INDEX `fk_pokedex_has_trainer_trainer1_idx` (`trainer_id` ASC) ,
  INDEX `fk_pokedex_has_trainer_pokedex_idx` (`pokedex_number` ASC) ,
  CONSTRAINT `fk_pokedex_has_trainer_pokedex`
    FOREIGN KEY (`pokedex_number`)
    REFERENCES `pkmn_fight`.`pokedex` (`pokedex_number`),
  CONSTRAINT `fk_pokedex_has_trainer_trainer1`
    FOREIGN KEY (`trainer_id`)
    REFERENCES `pkmn_fight`.`trainer` (`trainer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.trainers_pkm_move
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`trainers_pkm_move` (
  `move_id` INT NOT NULL,
  `pokedex_number` INT NOT NULL,
  `trainer_id` INT NOT NULL,
  PRIMARY KEY (`move_id`, `pokedex_number`, `trainer_id`),
  INDEX `fk_move_has_traine_has_pkmn_traine_has_pkmn1_idx` (`pokedex_number` ASC, `trainer_id` ASC) ,
  INDEX `fk_move_has_traine_has_pkmn_move1_idx` (`move_id` ASC) ,
  CONSTRAINT `fk_move_has_traine_has_pkmn_move1`
    FOREIGN KEY (`move_id`)
    REFERENCES `pkmn_fight`.`move` (`move_id`),
  CONSTRAINT `fk_move_has_traine_has_pkmn_traine_has_pkmn1`
    FOREIGN KEY (`pokedex_number` , `trainer_id`)
    REFERENCES `pkmn_fight`.`trainer_team` (`pokedex_number` , `trainer_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.type
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.type_combination
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`type_combination` (
  `att_type_id` INT NOT NULL,
  `deftype__id` INT NOT NULL,
  `type_of_weakness_weakness_id` INT NOT NULL,
  PRIMARY KEY (`att_type_id`, `deftype__id`),
  INDEX `fk_type_has_type_type2_idx` (`deftype__id` ASC) ,
  INDEX `fk_type_has_type_type1_idx` (`att_type_id` ASC) ,
  INDEX `fk_type_combination_type_of_weakness1_idx` (`type_of_weakness_weakness_id` ASC) ,
  CONSTRAINT `fk_type_combination_type_of_weakness1`
    FOREIGN KEY (`type_of_weakness_weakness_id`)
    REFERENCES `pkmn_fight`.`type_of_weakness` (`weakness_id`),
  CONSTRAINT `fk_type_has_type_type1`
    FOREIGN KEY (`att_type_id`)
    REFERENCES `pkmn_fight`.`type` (`type_id`),
  CONSTRAINT `fk_type_has_type_type2`
    FOREIGN KEY (`deftype__id`)
    REFERENCES `pkmn_fight`.`type` (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.type_has_pokedex
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`type_has_pokedex` (
  `type_id` INT NOT NULL,
  `pokedex_number` INT NOT NULL,
  PRIMARY KEY (`type_id`, `pokedex_number`),
  INDEX `fk_type_has_pokedex_pokedex1_idx` (`pokedex_number` ASC) ,
  INDEX `fk_type_has_pokedex_type1_idx` (`type_id` ASC) ,
  CONSTRAINT `fk_type_has_pokedex_pokedex1`
    FOREIGN KEY (`pokedex_number`)
    REFERENCES `pkmn_fight`.`pokedex` (`pokedex_number`),
  CONSTRAINT `fk_type_has_pokedex_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `pkmn_fight`.`type` (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- ----------------------------------------------------------------------------
-- Table pkmn_fight.type_of_weakness
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `pkmn_fight`.`type_of_weakness` (
  `weakness_id` INT NOT NULL AUTO_INCREMENT,
  `type_of_weakness` ENUM('uneffective', 'not so effective', 'effective', 'super-effective') NOT NULL,
  `value` ENUM('0', '0.5', '1', '2') NOT NULL,
  PRIMARY KEY (`weakness_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
SET FOREIGN_KEY_CHECKS = 1;
