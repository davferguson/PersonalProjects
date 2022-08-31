-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

DROP USER IF EXISTS server_owner;
DROP USER IF EXISTS appuser;

CREATE USER server_owner
WITH PASSWORD 'serverowner';

CREATE USER appuser
WITH PASSWORD 'standardappuser';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO server_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO server_owner;

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO appuser;
