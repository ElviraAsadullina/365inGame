insert into ingame365.users (name, username, password)
values ('John Doe', 'johndoe@gmail.com',
        '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Mike Smith', 'mikesmith@yahoo.com',
        '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m'),
       ('Jane Dowson', 'janed@gmail.com',
        '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Tom Hanks', 'tomh@gmail.com',
        '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Demi Moore', 'demim@gmail.com',
        '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Ivan Great', 'ivang@gmail.com',
        '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W');

insert into ingame365.tasks (title, description, status, expiration_date)
values ('Buy cheese', null, 'TODO', '2024-01-29 12:00:00'),
       ('Do homework', 'Math, Physics, Literature',
        'IN_PROGRESS', '2024-01-31 00:00:00'),
       ('Clean rooms', null, 'DONE', null),
       ('Call Mike', 'Ask about meeting', 'TODO',
        '2023-02-01 00:00:00');

insert into ingame365.users_tasks (task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into ingame365.users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER'),
       (3, 'ROLE_PLAYER'),
       (4, 'ROLE_PARENT'),
       (5, 'ROLE_COACH'),
       (6, 'ROLE_DOCTOR');

insert into ingame365.teams (name, created_at)
values ('Спартак', '2022-01-29 12:00:00'),
       ('Локомотив', '2022-01-29 12:00:00'),
       ('Зенит', '2022-01-29 12:00:00');

insert into ingame365.users_teams (user_id, team_id)
values (1, 1),
       (1, 2),
       (2, 2),
       (3, 1),
       (4, 1),
       (5, 3),
       (6, 1);
;