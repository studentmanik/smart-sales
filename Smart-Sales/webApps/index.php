<?php
include_once 'dbconfig.php';

// delete condition
if(isset($_GET['delete_id']))
{
    $sql_query="DELETE FROM tbl_user WHERE id=".$_GET['delete_id'];
    mysql_query($sql_query);
    header("Location: $_SERVER[PHP_SELF]");
}
if(isset($_GET['reset_id']))
{
   // $sql_query="DELETE FROM tbl_user WHERE id=".$_GET['reset_id'];
   // mysql_query($sql_query);
    header("Location: $_SERVER[PHP_SELF]");
}
// delete condition
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Smart Sales System</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
    <script type="text/javascript">
        function edt_id(id)
        {
            if(confirm('Sure to edit ?'))
            {
                window.location.href='edit_data.php?edit_id='+id;
            }
        }
        function delete_id(id)
        {
            if(confirm('Sure to Delete ?'))
            {
                window.location.href='index.php?delete_id='+id;
            }
        }
        function view_so_id(id)
        {
                window.location.href='salesorder.php?sr_id='+id;

        } function reset_id(id)
        {
                window.location.href='index.php?reset_id='+id;

        }
    </script>
</head>
<body>
<center>

    <div id="header">
        <div id="content">
            <label>Smart Sales System</label>
        </div>
       <!-- <div>
            <a href="javascript:edt_id('<?php /*echo $row[0]; */?>')"><img src="b_edit.png" align="EDIT" />reset</a>
        </div>-->
    </div>

    <div id="body">
        <div id="content">
            <table align="center">
                <tr>
                    <th colspan="5"><a href="add_data.php">Add Sales Representative.</a></th>
                </tr>
                <tr>
                    <th colspan="5"><a href="sku.php">Add SKU.</a></th>
                </tr><tr>
                    <th colspan="5"><a href="outlet.php">Add Outlet.</a></th>
                </tr>
                <th>User Name</th>
                <th>Password</th>
                <th>Full Name</th>
                <th colspan="3">Operations</th>
                </tr>
                <?php
                $sql_query="SELECT * FROM tbl_user";
                $result_set=mysql_query($sql_query);
                while($row=mysql_fetch_row($result_set))
                {
                    ?>
                    <tr>
                        <td><?php echo $row[1]; ?></td>
                        <td><?php echo $row[2]; ?></td>
                        <td><?php echo $row[3]; ?></td>
                        <td align="center"><a href="javascript:edt_id('<?php echo $row[0]; ?>')"><img src="b_edit.png" align="EDIT" /></a></td>
                        <td align="center"><a href="javascript:delete_id('<?php echo $row[0]; ?>')"><img src="b_drop.png" align="DELETE" /></a></td>
                        <td align="center"><a href="javascript:view_so_id('<?php echo $row[0]; ?>')"><img src="b_order.png" align="DELETE" /></a></td>
                    </tr>
                    <?php
                }
                ?>
            </table>
        </div>
    </div>

</center>
</body>
</html>