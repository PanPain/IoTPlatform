var index=0;
function addDevice(arr){
    index++;
    if(arr.status==false){
        var status1="离线"
    }
    else{
        var status1="在线"
    }
    var str='<tr><td>'+index+'</td>';
    str+='<td >'+arr.device.deviceIdentifier+'</td>'+'<td >'+arr.product.productName+'<td >'+arr.product.productManufactuter+'</td>'+'<td >'+status1+'</td></tr>';
    return str;
}
$(function(){
    // index=0;
    var tby=$('#dynamic-table tbody').eq(0);
    $.ajax({
        type:'get',
        url:"/devicemonitor/getAllDeviceInfo",
        data:"",
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                //console.log('后端返回了数据：'+mfData);
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    console.log(mfData[i])
                    strr+=addDevice(mfData[i]);
                }
                tby.empty();
                tby.html(strr);
            }
        }
        //  )
    });
})
//点击地图点位按钮
$('#map').click(function(e){
    var addR=$('#modal-table');
    console.log(addR);
    addR.css('display','block');
    addR.attr('class','modal fade in');
    $("#districtName").change(function(){
        $(this).attr("selected",true);
        var disN = $('#districtName>option:selected').attr('data-rno');
        $("input#districtNo").val(disN);
    });
})
//点击统计图表按钮
$('#chart').click(function(e){
    var addR=$('#modal-table2');
    console.log(addR);
    addR.css('display','block');
    addR.attr('class','modal fade in');
    //ajax请求获取数据(待补充)
    $.ajax({
        type:'get',
        url:"/devicemonitor/getDeviceStatusInfo",
        data:"",
        // contentType: "application/json;charset=UTF-8",
        // dataType:'jsonp',       //指定服务器返回的数据类型
        // jsonp:'callbackFunc',   //指定回调函数的参数名
        // jsonpCallback:'updateTable',
        success: function(response){
            if(response.meta.success){
                var arr2=[];
                var mfData1 = response.data;
                arr2[0]=mfData1.numOfOnline;
                arr2[1]=mfData1.numOfOffline;
                initPieView2(arr2,'pieChart2');
            }
        }
    });
})
//饼状图初始化
function initPieView2(arr,domEle){
    var str="#"+domEle;
    $(str).html("");
    var chart = new twaver.charts.PieChart();
    box = chart.getDataBox();
    createData('在线', arr[0], "red");
    createData('离线', arr[1], "gray");
    chart.setType("circle");
    chart.formatValueText = function(value, data){
        return data.getName() + ':'+parseInt(value/chart.getSum()*100) + '%';
    };
    var chartDom = chart.getView();
    var dom=$(str);
    var width=dom.css('width');
    var height=dom.css('height');
    chartDom.style.width = width;
    chartDom.style.height = height;
    $(str).append(chartDom);
}
//饼状图渲染
function createData(name, value, color){
    var data = new twaver.Element();
    data.setStyle("chart.value", value);
    data.setStyle("chart.color", color);
    data.setStyle("chart.value.color", "black");
    data.setStyle("chart.value.font", 'heivetica');
    data.setName(name);
    box.add(data);
}
//点击统计图表弹框中的×按钮，关闭弹窗
$('.delAdd').click(function(e){
    var addR=$('#modal-table2');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//点击编辑弹框中的取消按钮和×按钮，关闭弹窗
$('.delEdit').click(function(e){
    var addR=$('#modal-table');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
})