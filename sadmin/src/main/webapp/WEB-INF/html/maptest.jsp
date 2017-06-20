<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>异步加载地图</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
</head>
<body>
<div id="container"></div>
<script src="http://webapi.amap.com/maps?v=1.3&key=20e6306ee01f0079e61babcb9e660bed&callback=init"></script>
<script>
    var mapObj = new AMap.Map('container',{zoom:5});
    var markers = [];   //provinces见Demo引用的JS文件
    for(var i = 0; i < provinces.length; i += 1){
        marker = new AMap.Marker({
            position: [116.39,39.9],
            title: provinces[i].name
        });
        marker.setMap(mapObj);
    }
</script>
</body>
</html>