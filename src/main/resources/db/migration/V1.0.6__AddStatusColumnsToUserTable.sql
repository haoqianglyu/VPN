alter table users add column expired boolean default false;
alter table users add column locked boolean default false;
alter table users add column enable boolean default false;