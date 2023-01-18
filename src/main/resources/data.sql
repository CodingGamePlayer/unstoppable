CREATE TABLE `mybatis_db`.`article` (
                                        `art_id` INT NOT NULL,
                                        `art_title` VARCHAR(45) NOT NULL,
                                        `art_body` VARCHAR(45) NOT NULL,
                                        `art_regdate` VARCHAR(45) NOT NULL,
                                        `art_moddate` VARCHAR(45) NOT NULL,
                                        `userid` VARCHAR(45) NOT NULL,
                                        PRIMARY KEY (`art_id`))
    ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
