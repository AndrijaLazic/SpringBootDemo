--liquibase formatted sql
USE
--changeset AndrijaLazic:new_stored_procedures
CREATE PROCEDURE sp_get_tasks_between_dates(
	IN dateStart DATE,
    IN dateEnd DATE,
    IN frequency VARCHAR(225),
    IN section VARCHAR(224)
)
BEGIN
    CREATE TEMPORARY TABLE temp_table AS
    SELECT * FROM task t where t.frequency = frequency AND t.section = section;
    SELECT t.* FROM temp_table t JOIN activity a ON a.task_id = t.id WHERE a.date BETWEEN dateStart AND dateEnd;
    DROP TABLE temp_table;
END;

CREATE PROCEDURE sp_get_todo_tasks_between_dates(
	IN dateStart DATE,
    IN dateEnd DATE,
    IN frequency VARCHAR(225),
    IN section VARCHAR(224)
)
    BEGIN
    CREATE TEMPORARY TABLE temp_selected_tasks AS
    SELECT * FROM task t where t.frequency = frequency AND t.section = section;
    CREATE TEMPORARY TABLE temp_selected_activities AS
    SELECT * FROM activity a WHERE a.date BETWEEN dateStart AND dateEnd;

    SELECT t.* FROM temp_selected_tasks t LEFT JOIN temp_selected_activities a ON t.id = a.task_id WHERE a.id IS null;


    DROP TABLE temp_selected_tasks;
    DROP TABLE temp_selected_activities;
END;