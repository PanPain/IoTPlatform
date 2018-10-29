$(function(){
    $.ajax({
        type:'get',
        url:"/devicemonitor/getDeviceLocationInfo",
        data:"",
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                var len=mfData.length;
                addMarker(mfData);
            }
        }
    });
})
function addMarker(mfData) {
    var map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(108.93, 34.27), 15);  // 设置中心点
    map.enableScrollWheelZoom(true);
    // 创建图标对象
    var myIcon = new BMap.Icon("../../assets/images/markers.png", new BMap.Size(23, 25),);
    //循环建立标注点
    for(var i=0, pointsLen =mfData.length; i<pointsLen; i++) {
        var point = new BMap.Point(mfData[i].device.deviceLongitude, mfData[i].device.deviceLatitude); //将标注点转化成地图上的点
        if(mfData[i].status==true)
        {
            var marker = new BMap.Marker(point); //将点转化成标注点
        }
        else{
            var marker = new BMap.Marker(point, {icon: myIcon}); //将点转化成标注点
        }
        map.addOverlay(marker);  //将标注点添加到地图上
        //添加监听事件
        (function() {
            var thePoint = mfData[i];
            marker.addEventListener("click",
                function() {
                    showInfo(this,thePoint);
                });
        })();
    }
}
function showInfo(thisMarker,point) {
    //获取点的信息
    var sContent =
        '<ul style="margin:0 0 5px 0;padding:0.2em 0">'
        +'<li style="line-height: 26px;font-size: 15px;">'
        +'<span style="width: 70px;display: inline-block;">标识符：</span>' + point.device.deviceIdentifier + '</li>'
        +'<li style="line-height: 26px;font-size: 15px;">'
        +'<span style="width: 70px;display: inline-block;">产品ID：</span>' + point.device.fkProductId + '</li>'
    var infoWindow = new BMap.InfoWindow(sContent); //创建信息窗口对象
    thisMarker.openInfoWindow(infoWindow); //图片加载完后重绘infoWindow
}