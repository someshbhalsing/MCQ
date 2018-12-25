CREATE SCHEMA `m-pulse` ;


CREATE TABLE `m-pulse`.`participant` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `pname` VARCHAR(45) NOT NULL,
  `pemail` VARCHAR(45) NOT NULL,
  `ppassword` VARCHAR(45) NOT NULL,
  `event1` INT NOT NULL,
  `event2` INT NOT NULL,
  `event3` INT NOT NULL,
  `event4` INT NOT NULL,
  `event5` INT NOT NULL,
  PRIMARY KEY (`pid`));


CREATE TABLE `m-pulse`.`questions` (
  `qid` INT NOT NULL AUTO_INCREMENT,
  `event_id` INT NOT NULL,
  `question_text` VARCHAR(400) NOT NULL,
  `option1` VARCHAR(400) NOT NULL,
  `option2` VARCHAR(400) NOT NULL,
  `option3` VARCHAR(400) NOT NULL,
  `option4` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`qid`));


CREATE TABLE `m-pulse`.`attempt` (
  `pid` INT NOT NULL,
  `qid` INT NOT NULL,
  `answer_id` INT NULL DEFAULT 0,
  PRIMARY KEY (`pid`, `qid`),
  INDEX `qid_idx` (`qid` ASC),
  CONSTRAINT `pid`
    FOREIGN KEY (`pid`)
    REFERENCES `m-pulse`.`participant` (`pid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `qid`
    FOREIGN KEY (`qid`)
    REFERENCES `m-pulse`.`questions` (`qid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
