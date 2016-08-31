
\echo creating schema
create schema unit_test;

\echo creating user
create user dbuser identified by 'dbus3r'  SEARCH_PATH unit_test;

\echo creating role
create role visualization;

\echo grant usage, create on schema
grant usage,create on schema unit_test to dbuser, visualization;
