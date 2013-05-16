DELIMITER $$
DROP PROCEDURE IF EXISTS get_rands$$
CREATE PROCEDURE getrands(IN tname varchar(32),IN cnt INT)
BEGIN
SET @sqlcmd = CONCAT('CREATE TEMPORARY TABLE ', tname, '( rand_id INT )');
PREPARE stmt FROM @sqlcmd;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
loop_me: LOOP
    IF cnt < 1 THEN
      LEAVE loop_me;
    END IF;

    set @
    INSERT INTO tname
       SELECT r1.id
         FROM questions_car AS r1 JOIN
              (SELECT (RAND() *
                            (SELECT MAX(id)
                               FROM questions_car)) AS id)
               AS r2
        WHERE r1.id >= r2.id
        ORDER BY r1.id ASC
        LIMIT 1;

    SET cnt = cnt - 1;
  END LOOP loop_me;
END$$
DELIMITER ;