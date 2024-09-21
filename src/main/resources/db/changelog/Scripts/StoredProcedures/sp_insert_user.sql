IF EXISTS (
    SELECT type_desc, type
    FROM sys.procedures WITH(NOLOCK)
    WHERE NAME = 'sp_insert_user'
      AND type = 'P'
)
DROP PROCEDURE [dbo].[sp_insert_user]
GO
CREATE PROCEDURE [dbo].[sp_insert_user]
    @Name [nvarchar](30),
    @Lastname [nvarchar](30),
    @Email [nvarchar](100),
    @PasswordHash [varbinary](max),
    @PasswordSalt [varbinary](max),
    @PhoneNumber [nvarchar](30),
    @WorkerType tinyint
AS
BEGIN
    INSERT INTO [dbo].Users
    (Name
    ,Lastname
    ,Email
    ,PasswordSalt
    ,PasswordHash
    ,PhoneNumber
    ,WorkerType,
     IsValid)
    VALUES
        (@Name
        ,@Lastname
        ,@Email
        ,@PasswordHash
        ,@PasswordSalt
        ,@PhoneNumber
        ,@WorkerType,
         0)
END
GO