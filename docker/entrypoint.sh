#!/bin/bash
#su - dbadmin
# Install HDFS connectors
/opt/vertica/bin/admintools -t start_db -d docker
/opt/vertica/bin/vsql -f /vertica_setup.sql
/bin/bash
