create table FILE_SEARCH_HISTORY
(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  searchText VARCHAR(64) not null,
  filePath VARCHAR(64) not null,
  searchDate timestamp not null
);


