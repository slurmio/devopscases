-- just emulate hard work
SELECT clock_timestamp(), pg_sleep(30), clock_timestamp();

INSERT INTO "users"("login", "password", "roles")
VALUES
('root', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$VD2gbE9s9SvxSU3QnmeO9w$hosiwDgCWLdyZ6xrysnDg9fDE38frM65jxOj58ZkCXs', '{ROOT}'),
('admin', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$VD2gbE9s9SvxSU3QnmeO9w$hosiwDgCWLdyZ6xrysnDg9fDE38frM65jxOj58ZkCXs', '{ADMIN}'),
('user', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$VD2gbE9s9SvxSU3QnmeO9w$hosiwDgCWLdyZ6xrysnDg9fDE38frM65jxOj58ZkCXs', DEFAULT)
RETURNING *
;