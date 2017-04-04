<?php
include_once 'dbconfig.php';
if(isset($_GET['edit_id']))
{
    $sql_query="SELECT * FROM tbl_user WHERE id=".$_GET['edit_id'];
    $result_set=mysql_query($sql_query);
    $fetched_row=mysql_fetch_array($result_set);
}
if(isset($_POST['btn-update']))
{
    // variables for input data
    $full_name = $_POST['full_name'];
    $password = $_POST['password'];
    $user_name = $_POST['user_name'];
    // variables for input data

    // sql query for update data into database
    $sql_query = "UPDATE tbl_user SET full_name='$full_name',password='$password',user_name='$user_name' WHERE id=".$_GET['edit_id'];
    // sql query for update data into database

    // sql query execution function
    if(mysql_query($sql_query))
    {
        ?>
        <script type="text/javascript">
           // alert('Data Are Updated Successfully');
            window.location.href='index.php';
        </script>
        <?php
    }
    else
    {
        ?>
        <script type="text/javascript">
          //  alert('error occured while updating data');
        </script>
        <?php
    }
    // sql query execution function
}
if(isset($_POST['btn-cancel']))
{
    header("Location: index.php");
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
                        <td><input type="text" name="full_name" placeholder="Full Name" value="<?php echo $fetched_row['full_name']; ?>" required /></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="password" placeholder="Password" value="<?php echo $fetched_row['password']; ?>" required /></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="user_name" placeholder="User Name" value="<?php echo $fetched_row['user_name']; ?>" required /></td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" name="btn-update"><strong>UPDATE</strong></button>
                            <button type="submit" name="btn-cancel"><strong>Cancel</strong></button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</center>
</body>
</html>