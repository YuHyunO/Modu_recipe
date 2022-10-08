conn sys/java1234 as sysdba
drop user modu cascade;

alter session set "_oracle_script"=true;
create user modu identified by modu;
grant connect, resource, create view, unlimited tablespace to modu;
conn modu/modu;

show user;
