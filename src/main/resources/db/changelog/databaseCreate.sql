IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'SpringDemoDatabase')
BEGIN
    CREATE DATABASE SpringDemoDatabase
END
GO
USE [SpringDemoDatabase]
GO
