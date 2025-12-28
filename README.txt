File management system that uses database.
For the database you will need this create statement:
CREATE TABLE Contact (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    phoneNumber TEXT NOT NULL,
    email TEXT NOT NULL,
    address TEXT NOT NULL,
    category TEXT NOT NULL
)
Filepath and database path not included for obvious reasons.