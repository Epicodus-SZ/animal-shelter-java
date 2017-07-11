## How to backup the project database

1. From Bash type '$ pg_dump shelter > db_backup'


## How to restore the project database

1. Run Postgres = 'Postgres'
2. Go into psql = 'psql'
3. Create your database = 'CREATE DATABASE shelter;'
4. Exit psql = '\q'
5. Load your database schema from the backup = 'psql shelter < db_backup'
6. Go into psql = 'psql'
7. Create the test DB = 'CREATE DATABASE shelter_test WITH TEMPLATE shelter;'
