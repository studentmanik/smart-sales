<?php
include_once 'dbconfig.php';
if(isset($_POST['btn-save']))
{
    // variables for input data
    $full_name = $_POST['full_name'];
    $password = $_POST['password'];
    $user_name = $_POST['user_name'];
    // variables for input data

    // sql query for inserting data into database
    $sql_query = "INSERT INTO tbl_user(full_name,password,user_name) VALUES('$full_name','$password','$user_name')";
    // sql query for inserting data into database

    // sql query execution function
    if(mysql_query($sql_query))
    {
        ?>
        <script type="text/javascript">
            //alert('Data Are Inserted Successfully ');
            window.location.href='index.php';
        </script>
        <?php
    }
    else
    {
        ?>
        <script type="text/javascript">
           // alert('error occured while inserting your data');
        </script>
        <?php
    }
    // sql query execution function
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Smart Sales System</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>
<body>
<center>

    <div id="header">
        <div id="content">
            <label>Smart Sales System</label>
        </div>
    </div>
    <div id="body">
        <div id="content">
            <form method="post">
                <table align="center">
                    <tr>
                        <td align="center"><a href="index.php">back to main page</a></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="full_name" placeholder="full Name" required /></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="user_name" placeholder="User Name" required /></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="password" placeholder="Password" required /></td>
                    </tr>
                    <tr>
                        <td><button type="submit" name="btn-save"><strong>SAVE</strong></button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</center>
</body>
</html>