<?php
include_once 'dbconfig.php';

// delete condition

// delete condition
?>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>Google Maps Multiple Markers</title>
    <script src="http://maps.google.com/maps/api/js?sensor=false"
            type="text/javascript"></script>
</head>
<body>
<div id="map" style="width: 1000px; height: 1000px;"></div>

<script type="text/javascript">
    var locations = [
        <?php
        $sql_query="SELECT times_stamp,lat,lon,id FROM `tbl_sr_location` where sr_id=16 and date='2017-02-19'";
        $result_set=mysql_query($sql_query);
        while($row=mysql_fetch_row($result_set))
        {
        ?>
        ['<?php echo $row[0]; ?>', <?php echo $row[1]; ?>, <?php echo $row[2]; ?>, <?php echo $row[3]; ?>],

        <?php
        }
        ?>

    ];

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 10,
        center: new google.maps.LatLng(23.7456653, 90.3503647),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < locations.length; i++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[i][1], locations[i][2]),
            map: map
        });

        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
                infowindow.setContent(locations[i][0]);
                infowindow.open(map, marker);
            }
        })(marker, i));
    }


    var geocoder;var map;var marker;function initialize()
    {var latlng=new google.maps.LatLng(-33.897,150.099);
        var myOptions={zoom:9,center:latlng,mapTypeId:google.maps.MapTypeId.TERRAIN};
        map=new google.maps.Map(document.getElementById("map"),myOptions);
        var rendererOptions={map:map};directionsDisplay=new google.maps.DirectionsRenderer(rendererOptions);
        var point1=new google.maps.LatLng(-33.8975098545041,151.09962701797485);
        var point2=new google.maps.LatLng(-33.8584421519279,151.0693073272705);
        var point3=new google.maps.LatLng(-33.87312358690301,151.99952697753906);
        var point4=new google.maps.LatLng(-33.84525521656404,151.0421848297119);
        var wps=[{location:point1},{location:point2},{location:point4}];
        var org=new google.maps.LatLng(-33.89192157947345,151.13604068756104);
        var dest=new google.maps.LatLng(-33.69727974097957,150.29047966003418);
        var request={origin:org,destination:dest,waypoints:wps,travelMode:google.maps.DirectionsTravelMode.DRIVING};
        directionsService=new google.maps.DirectionsService();directionsService.route(request,function(response,status){if(status==google.maps.DirectionsStatus.OK){directionsDisplay.setDirections(response);}
    else
        alert('failed to get directions');});}
</script>
</body>
</html>