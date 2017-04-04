<?php
include_once 'dbconfig.php';
if (isset($_POST['btn-save'])) {
    // variables for input data
    $name = $_POST['name'];
    $address = $_POST['address'];
    $owner = $_POST['owner'];
    $mobile = $_POST['mobile'];
    $sr_id = $_POST['sr_id'];
    // variables for input data

    // sql query for inserting data into database
    $sql_query = "INSERT INTO tbl_outlet(name,address,owner,mobile,sr_id,token) VALUES('$name','$address','$owner','$mobile','$sr_id',uuid_short())";
    // sql query for inserting data into database

    // sql query execution function
    if (mysql_query($sql_query)) {
        ?>
        <script type="text/javascript">
            // alert('Data Are Inserted Successfully ');
            window.location.href = 'outlet.php';
        </script>
        <?php
    } else {
        ?>
        <script type="text/javascript">
            //alert('error occured while inserting your data');
        </script>
        <?php
    }
    // sql query execution function
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Smart Sales System</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
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
                        <td align="center"><a href="outlet.php">back to outlet page</a></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="name" placeholder="neme" required/></td>
                    </tr>

                    <tr>
                        <td><input type="text" name="address" placeholder="address" required/></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="owner" placeholder="owner" required/></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="mobile" placeholder="mobile" required/></td>
                    </tr>
                    <tr>
                        <td>
                            <select type="text" name="sr_id" placeholder="sr_id">
                                <?php
                                $sql_query = "SELECT id,full_name FROM tbl_user";
                                $result_set = mysql_query($sql_query);
                                while ($row = mysql_fetch_row($result_set)) {
                                    ?>
                                    <option value="<?php echo $row[0]; ?>"><?php echo $row[1]; ?></option>
                                    <?php
                                }
                                ?>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" name="btn-save"><strong>SAVE</strong></button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</center>
</body>
</html>