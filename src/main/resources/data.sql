INSERT INTO profiles (username, first_name, last_name, dob, age, email, frequency, about_me, level)
VALUES ('karel', 'karel', 'appel', '25-04-1921', 101, 'karelappel@gmail.com','ONCE_A_MONTH', 'niks te melden', 'BEGINNER'),
       ('vincent', 'vincent', 'gogh', '30-03-1853', 169, 'vincentvangogh@gmail.com','ONCE_A_WEEK', 'mijn oor is kapot', 'PROFICIENT');

INSERT INTO users (username, password, enabled, user_profile)
    VALUES ('karel', '$2y$10$ZGU6vt6yoRqDvao7Nelxe.9x7mH4sunzgJSFVIEFO/i5Kci6x8ta6',  true, 'karel'),
           ('vincent', '$2y$10$o6uZsxhnP2//MTSAICYUGuthde9LioKWaEKR7OTd17w6nf3q.2zpu',  true, 'vincent');

INSERT INTO authorities(name)
VALUES ('ROLE_BUDDY'),
       ('ROLE_ADMIN'),
       ('ROLE_STUDENT');

INSERT INTO user_authorities (user_username, authority_id)
    VALUES ('karel', 'ROLE_STUDENT'),
           ('vincent', 'ROLE_ADMIN');

INSERT INTO requests (id, got_accepted, got_declined, got_canceled, message, sender_username, receiver_username)
VALUES (1, false, false, false, 'test1', 'karel', 'vincent'),
       (2, false, false, false, 'test2', 'vincent', 'karel');

INSERT INTO messages (title, content, for_buddy, for_student, approved)
    VALUES('titel', 'content', true, true, true),
           ('titel2', 'content2', true, false, true),
           ('titel3', 'content3', false, true, true),
           ('titel4', 'content4', true, true, false);


