create TABLE IF NOT EXISTS `users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `surname`VARCHAR(45) NOT NULL,
    `age` INT NOT NULL,
    `email` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);
