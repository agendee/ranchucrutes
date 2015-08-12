/*exportando database*/
mysqldump  -proot -uroot ranchucrutes > filename.sql

/*importando database , antes precisa cria-lo*/
create database ranchucrutes;
mysql -uroot -proot < filename.sql