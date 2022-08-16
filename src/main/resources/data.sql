INSERT INTO profiles (username, active, first_name, last_name, dob, age, email, role, frequency, about_me, level)
VALUES ('karel', true, 'karel', 'appel', '25-04-1921', 101, 'karelappel@gmail.com', 'ROLE_STUDENT', 'ONCE_A_MONTH',
        'niks te melden', 'BEGINNER'),
       ('vincent', true, 'vincent', 'gogh', '30-03-1853', 169, 'vincentvangogh@gmail.com', 'ROLE_ADMIN', 'ONCE_A_WEEK',
        'mijn oor is kapot', 'PROFICIENT'),
       ('japie', false, 'japie', 'krekel', '25-04-1981', 10, 'japiekrekel@gmail.com', 'ROLE_STUDENT', 'EVERY_DAY',
        'about japie', 'ELEMENTARY'),
       ('boris', false, 'boris', 'johnson', '25-04-1521', 11, 'borisjohnson@gmail.com', 'ROLE_BUDDY', 'FEW_TIMES_A_MONTH',
        'english president blabla', 'INTERMEDIATE'),
       ('joep', false, 'joep', 'meloen', '25-04-1951', 14, 'joepmeloen@gmail.com', 'ROLE_BUDDY', 'FEW_TIMES_A_WEEK',
        'joep meloen hier', 'UPPER_INTERMEDIATE');

INSERT INTO users (username, password, enabled, user_profile)
VALUES ('karel', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6', true, 'karel'),
       ('vincent', '$2y$10$o6uZsxhnP2//MTSAICYUGuthde9LioKWaEKR7OTd17w6nf3q.2zpu', true, 'vincent'),
       ('japie', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6', true, 'japie'),
       ('boris', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6', true, 'boris'),
       ('joep', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6', true, 'joep');

INSERT INTO authorities(name)
VALUES ('ROLE_BUDDY'),
       ('ROLE_ADMIN'),
       ('ROLE_STUDENT');

INSERT INTO user_authorities (user_username, authority_id)
VALUES ('karel', 'ROLE_STUDENT'),
       ('vincent', 'ROLE_ADMIN'),
       ('japie', 'ROLE_STUDENT'),
       ('boris', 'ROLE_BUDDY'),
       ('joep', 'ROLE_BUDDY');

INSERT INTO requests (message, sender_username, receiver_username, status)
VALUES ('test1', 'karel', 'vincent', 'PENDING'),
       ('test2', 'vincent', 'karel', 'PENDING'),
       ('test3', 'japie', 'karel', 'PENDING'),
       ('test4', 'joep', 'karel', 'PENDING'),
       ('testr5', 'karel', 'boris', 'CANCELLED'),
       ('test4r', 'joep', 'karel', 'ACCEPTED'),
       ('test4r', 'joep', 'karel', 'DECLINED'),
       ('test4r', 'joep', 'karel', 'DECLINED'),
       ('test4r', 'joep', 'karel', 'ACCEPTED'),
       ('test1', 'karel', 'vincent', 'PENDING'),
       ('test1', 'karel', 'vincent', 'PENDING'),
       ('test1', 'karel', 'vincent', 'PENDING'),
       ('test1', 'karel', 'vincent', 'PENDING'),
       ('test1', 'karel', 'vincent', 'PENDING'),
       ('test1', 'karel', 'vincent', 'PENDING'),
       ('test1', 'karel', 'vincent', 'PENDING');

INSERT INTO messages (title, content, for_buddy, for_student, approved)
VALUES ('titel', 'content', true, true, true),
       ('titel2', 'content2', true, false, true),
       ('titel3', 'content3', false, true, true),
       ('titel4', 'content4', true, true, false),
       ('titel5', 'content5', true, true, false),
       ('titel6', 'content6', true, true, false),
       ('titel7', 'content7', true, true, false),
       ('titel8', 'content8', true, true, false);


