FROM mysql:latest
# Set the root user password (change 'your_password' to your desired password)
    ENV MYSQL_ROOT_PASSWORD admin@123

    # Set the default database name (change 'your_database' to your desired database name)
    ENV MYSQL_DATABASE customer-db

    # Copy SQL scripts or initialization files (if any) to the '/docker-entrypoint-initdb.d/' directory
    # Uncomment the line below and copy your files to the specified location
    # COPY ./your_script.sql /docker-entrypoint-initdb.d/

    # Expose the MySQL default port (optional)
    EXPOSE 3306

    # Start the MySQL server
    CMD ["mysqld"]