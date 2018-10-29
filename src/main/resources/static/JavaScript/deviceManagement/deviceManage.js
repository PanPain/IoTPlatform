//点击新增按钮
$('#devAdd').click(function (e) {
    var addR = $('#deviceAddModal');
    console.log(addR);
    addR.css('display', 'block');
    addR.attr('class', 'modal fade in');
});
//点击新增弹框中的取消按钮和×按钮，关闭弹窗
$('.devDelAdd').click(function (e) {
    var addR = $('#deviceAddModal');

    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
});
//点击明细弹框中的取消按钮和×按钮，关闭弹窗
$('.devDelView').click(function (e) {
    var addR = $('#deviceViewModal');

    addR.css('display', 'none');
    addR.attr('class', 'modal fade');

});
//点击编辑弹框中的取消按钮和×按钮，关闭弹窗
$('.devdelEdit').click(function (e) {
    var addR = $('#deviceEditModal');

    addR.css('display', 'none');
    addR.attr('class', 'modal fade');

});

//查看所有设备
//添加单个设备
function addDevice(arr, index) {
    var str = '<tr id="myTr" ><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">' +
        '<input type="checkbox" class="ace chk" name="chk" data-id="' + arr.productId + '" data-index="' + index + '" data-devId="' + arr.deviceId + '" data-identifier="' + arr.deviceIdentifier + '" data-longitude="' + arr.deviceLongitude + '" data-latitude="' + arr.deviceLatitude +'" data-deviceData="' + arr.deviceData + '" data-timeStamp="' + arr.timeStamp +'"/>' + '<span class="lbl"></span></label></td><td>' + index + '</td>';

    var str1 = str + '<td class="deviceId11 ">' + arr.deviceIdentifier + '</td>' + '<td class="longitude11 ">' + arr.deviceLongitude + '</td>' + '<td class="latitude11 ">' + arr.deviceLatitude + '</td>' ;
    // var a=$("#myTr").data("id");
    str1 += '<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a id="devView" class="devView blue"  data-toggle="modal" onclick="devView()">' +
        '<i class="ace-icon fa fa-search-plus bigger-130"></i></a><a id="devEdit" class="green devEdit"  data-toggle="modal" onclick="devEdit()">' +
        '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a id="devDelRow" class="devDelRow red"  data-toggle="modal" onclick="devDelRow()">' +
        '<i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
    str1 += '<div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">' +
        '<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">' +
        '<li><a href="#modal-table" class="tooltip-info" data-rel="tooltip" title="View" data-toggle="modal"><span class="editRow blue">' +
        '<i class="ace-icon fa fa-search-plus bigger-120"></i></span> </a></li><li>' +
        '<a href="#modal-table" class="tooltip-success" data-rel="tooltip" title="Edit" data-toggle="modal"><span class="green">' +
        '<i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a href="#modal-table" class="tooltip-error" data-rel="tooltip" title="Delete" data-toggle="modal">' +
        '<span class="delRow red "><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td></tr>';


    return str1;
}

//循环添加所有设备信息
function addAllDevice(arr) {
    var str = '';
    var data = arr['data'];
    var meta = arr['meta'];
    var a = meta['success'];


    if (!meta['success']) {
        return -1;
    } else {
        var len = data.length;
        var index = 1;
        for (var i = 0; i < len; i++, index++) {
            str += addDevice(data[i], index);
        }
        return str;
    }
}




//获取到数据之后执行的回调函数
function updateTable(data) {
    var result = JSON.stringify(data);
    allDeviceTable = JSON.parse(result);
    //在执行之前先删除表格中的所有元素，重新进行动态添加
    var tby = $('#dynamic-table2 tbody').eq(0);
    var trs = tby.children('tr');
    var str = addAllDevice(allDeviceTable);
    if (str == -1) {
        alert('数据请求失败！');
    } else {
        console.log('数据请求成功，正在更新表格！');
        tby.empty();
        tby.html(str);
        $.ajax({
            url:  "http://localhost:8080/product/getAddedProducts",
            type: 'GET',
            data: {
            },
            async: false,   //如果不加，无法实现数据传值
            // dataType: 'json',
            success: function (response) {
                var select = $("#select")
                select.empty();
                var result = JSON.stringify(response);
                allProduct = JSON.parse(result);
                var arr = allProduct['data'];
                var arrList = arr['list'];
                var length = arrList.length;
                var strr =null;
                for(var i=0; i<length; i++){
                    var Id = arrList[i]['productId'];
                    var name = arrList[i]['productName'];
                    //            addSelect(Id,name);//将ID和NAME添加到下拉框中

                    strr += "<option value='" + Id + "'>" + name + "</option>";

                }
                $("#select").html(strr);
                document.getElementById("select").selectedIndex = -1;

                str = null;

            },
            error: function () {
                alert("出现错误");
            }
        });
    }
}

$(function () {
    //jQuery的入口函数
    //跨域请求服务端的数据，等待数据返回，更新页面的表格数据
    //1. 第一种方法：原生的ajax请求
    //getResources();
    //2. 使用jQuery的ajax跨域方式
    //getResourcesjQuery();
    //使用jQuery的ajax请求，用于获取所有的人员信息


    // var params='';
    var URL = 'http://localhost:8080/device/searchDevices'; //对应到接口的位置
    $.ajax({
        type: 'GET',
        url: URL,
        // dataType: 'json', //指定服务器返回的数据类型(在不需要返回值的情况下，扔按模板格式，设置了dataType:"json",参数；这时候，ajax传值正确时，出现200返回成功状态下报错的特殊情况)
        data: {},
        success: function (response) {
            console.log('成功调用！');
            console.log(JSON.stringify(response));
            updateTable(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        },
    });
});

//删除设备
function devDelRow(){
    //alert(this);
    var checks = document.getElementsByName("chk");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }
    if (n < 1) {
        alert('请选择要删除的产品！');
        return false;
    } else {

        var rsu = confirm('确定要删除该产品吗？');
        if (rsu) {
            var delId = $("td :checked").attr('data-devId');

            $.ajax({
                type: "GET",
                url: "http://localhost:8080/device/deleteDevice",
                data:"deviceId="+ delId,

                // contentType: "application/json;charset=UTF-8",
                success: function (response) {
                    if (response.meta.success) {
                        alert('删除成功');
                        //alert(this);
                        $($("td :checked").parent().parent().parent()).remove();
                        window.location.reload();
                    }
                }
            });
            // alert('删除成功！');
        } else {
            alert('撤销操作！');
        }
    }
};
////查看产品列表
//$('.selectPro').click(function (e) {
//
//$.ajax({
//    url:  "http://localhost:8080/product/getAddedProducts",
//    type: 'GET',
//    data: {
//    },
//    async: false,   //如果不加，无法实现数据传值
//    // dataType: 'json',
//    success: function (response) {
//        var select = $("#select")
//        select.empty();
//        var result = JSON.stringify(response);
//        allProduct = JSON.parse(result);
//        var arr = allProduct['data'];
//        var arrList = arr['list'];
//        var length = arrList.length;
//        var str =null;
//        for(var i=0; i<length; i++){
//            var Id = arrList[i]['productId'];
//            var name = arrList[i]['productName'];
////            addSelect(Id,name);//将ID和NAME添加到下拉框中
////var x = document.getElementById("select");
////x.selectedIndex = -1;
//        str += "<option value='" + Id + "'>" + name + "</option>";
//
//        }
//         $("#select").html(str);
//
//         str = null;
//         alert("ddddd");
//    },
//    error: function () {
//        alert("出现错误");
//    }
//});
//})
//function addSelect(id,name){
//  $("#select").append("<option value='" + id + "'>" + name + "</option>");
//
//}
//点击下拉框中的选项，显示相应产品和产品下设备信息
$("#select").change(function(){
    var queryId = $("#select").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/product/getProductInfo",
        data: "productId="+queryId  ,
//            contentType: "application/json;charset=UTF-8",
        success: function (response) {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/device/getDevByProduct",
                data:  "productId="+ queryId,

//                             contentType: "application/json;charset=UTF-8",
                success: function (response) {

                    showDeviceById(response);
                }
                //        error: function (XmlHttpRequest) {
                //           alert("44");
                //               console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
                //           }
            });
            showProductById(response);
        },
        error: function (XmlHttpRequest, textStatus, errThrown) {
            console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
        }
    });

//    $.ajax({
//        type: "GET",
//        url: "http://192.168.0.230:8080/device/getDevByProduct",
//        data:"productId =" + queryId,
//
//         contentType: "application/json;charset=UTF-8",
//        success: function (response) {
//        alert("33");
//          showDeviceById(response);
//        }
////        error: function (XmlHttpRequest) {
////           alert("44");
////               console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
////           }
//    });
})
//根据productId查产品
function showProductById(response){
    var result = JSON.stringify(response);
    allProductView = JSON.parse(result);
    var data = allProductView['data'];

    var pname = data['productName'];
    var pcode =  data['productCode'];
    var pmanu =  data['productManufactuter'];
    var tby = $('#dynamic-table1 tbody').eq(0);
    tby.empty();
    var str = '<tr id="myTr" data-id="' + data['productId'] + '" >' + '<td class="proName1 ">' + pname + '</td>' + '<td class="proManu1 ">' + pmanu + '</td>' + '<td class="proCode1 ">' + pcode + '</td></tr>' ;
    tby.html(str);
}
//根据productId查设备
function showDeviceById(response){
    var result = JSON.stringify(response);
    allDeviceView = JSON.parse(result);
    var data = allDeviceView['data'];
    // var deviceId = data['deviceIdentifier'];
    // var deviceLong = data['deviceLongitude'];
    // var deviceLati = data['deviceLatitude'];
    var str = '';
    var meta = allDeviceView['meta'];
    if (!meta['success']) {
        return -1;
    } else {
        var len = data.length;
        var index = 1;
        for (var i = 0; i < len; i++, index++) {
            str += addDev(data[i], index);
        }
        var tby = $('#dynamic-table2 tbody').eq(0);
        tby.empty();
        tby.html(str);
    }
}
//根据产品Id返回设备列表
function addDev(arr,index){
    var str = '<tr id="myTr" ><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">' +
        '<input type="checkbox" class="ace chk" name="chk" data-id="' + arr.productId + '" data-index="' + index + '" data-devId="' + arr.deviceId + '" data-identifier="' + arr.deviceIdentifier + '" data-longitude="' + arr.deviceLongitude + '" data-latitude="' + arr.deviceLatitude +'" data-deviceData="' + arr.deviceData + '" data-timeStamp="' + arr.timeStamp +'"/>' + '<span class="lbl"></span></label></td><td>' + index + '</td>';

    var str1 = str + '<td class="deviceId11 ">' + arr.deviceIdentifier + '</td>' + '<td class="longitude11 ">' + arr.deviceLongitude + '</td>' + '<td class="latitude11 ">' + arr.deviceLatitude + '</td>' ;
    // var a=$("#myTr").data("id");
    str1 += '<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a id="devView" class="devView blue"  data-toggle="modal" onclick="devView()">' +
        '<i class="ace-icon fa fa-search-plus bigger-130"></i></a><a id="devEdit" class="green devEdit"  data-toggle="modal" onclick="devEdit()">' +
        '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a id="devDelRow" class="devDelRow red"  data-toggle="modal" onclick="devDelRow()">' +
        '<i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
    str1 += '<div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">' +
        '<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">' +
        '<li><a href="#modal-table" class="tooltip-info" data-rel="tooltip" title="View" data-toggle="modal"><span class="editRow blue">' +
        '<i class="ace-icon fa fa-search-plus bigger-120"></i></span> </a></li><li>' +
        '<a href="#modal-table" class="tooltip-success" data-rel="tooltip" title="Edit" data-toggle="modal"><span class="green">' +
        '<i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a href="#modal-table" class="tooltip-error" data-rel="tooltip" title="Delete" data-toggle="modal">' +
        '<span class="delRow red "><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td></tr>';


    return str1;
}
//新增设备页面点击确定按钮
// 新增页面点击确定按钮
function addDev1(){


    // alert($("input#account").val());
    // alert((document.getElementById("officename")).value);
    // if (!($("input#usraccount").val()) || (!$("input#usrpassword").val()) || !(document.getElementById("usrofficename")).value || !(document.getElementById("usrrole")).value)
    // // if(!($("#NewNumber").val()))
    // {
    //     alert("除用户姓名和电话号码外，其他各项为必填项");
    // } else {


    var proID = $("#myTr").data("id");
    var myDate = new Date();
    var time = myDate.getTime();
    var newDevObject = {

        "dat": [{
            "deviceIdentifier": $("input#devIdenti").val(),
            "deviceLongitude": $("input#addLong").val(),
            "deviceLatitude": $("input#addLati").val(),
            "productId":  proID,
            "timeStamp": time
        }],
    }
    var newDev = newDevObject['dat'][0];
    console.log(newDev);
    console.log(typeof(newDev));

    //向后端传送数据保存
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/device/addDevice",

        data: JSON.stringify(newDev),
        contentType: "application/json;charset=UTF-8",
        async: false,
        success: function (response) {

            if (response.meta.success) {
                alert(response.meta.message);
                location.reload();
            } else {
                alert("11111111111" + response.meta.message + "qqqqq" + response.meta.code + response.meta.success);
            }
        },
        error: function (XmlHttpRequest, textStatus, errThrown) {
            console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
        }
    });

};

//查看设备详情
function devView() {
    var checks = document.getElementsByName("chk");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }

    if (n < 1) {
        alert('请选择要查看的数据！');
        return false;
    } else if (n == 1) {

        var vdevId = $("td :checked").attr("data-devId");


        //向后端传送数据保存
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/device/getDevDetail",
            data:  "deviceId="+ vdevId,

//            contentType: "application/json;charset=UTF-8",
            success: function (response) {
                viewDevice(response);
            },
            error: function (XmlHttpRequest, textStatus, errThrown) {
                console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
            }
        });

    } else {
        alert('只能选择一条数据！');
        return false;
    };


    //		return false;

    return false;
};

function viewDevice(response){
    var result = JSON.stringify(response);
    var deviceView = JSON.parse(result);
    console.log(deviceView);


    var data = deviceView['data'];
    var meta = deviceView['meta'];
    console.log(JSON.stringify(deviceView));
    if (!response.meta.success) {
        console.log('Exception:' + meta['message']);
        alert("获取详情失败！");
    } else {

        $("#devIdV").val(data['deviceIdentifier']);
        $("#devLongV").val(data['deviceLongitude']);
        $("#devLatiV").val(data['deviceLatitude']);
        $("#devDataV").val(data['deviceData']);
        var timeStamp1 = data['timeStamp']
        var time= timestampToTime(timeStamp1);
        $("#devTimeV").val(time);
        var addR = $('#deviceViewModal');
        console.log(addR);
        addR.css('display', 'block');
        addR.attr('class', 'modal fade in');
    }
}


//时间戳转换为时间格式
function add0(m){return m<10?'0'+ m:m }
function timestampToTime(timestamp)
{
//shijianchuo是整数，否则要parseInt转换
    var time = new Date(timestamp);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

//点击编辑按钮
function devEdit(){
    var checks = document.getElementsByName("chk");
    n = 0;
    for (i = 0; i < checks.length; i++) {
        if (checks[i].checked)
            n++;
    }

    if (n < 1) {
        alert('请选择要查看的数据！');
        return false;
    } else if (n == 1) {

        productId = $("td :checked").attr('data-id');
        deviceId = $("td :checked").attr('data-devId');
        var devIdE = $("td :checked").attr('data-identifier');
        var devLongE = $("td :checked").attr('data-longitude');
        var devLatiE = $("td :checked").attr('data-latitude');

        $("#devIdE").val(devIdE);
        $("#devLongE").val(devLongE);
        $("#devLatiE").val(devLatiE);
        var addR = $('#deviceEditModal');
        console.log(addR);
        addR.css('display', 'block');
        addR.attr('class', 'modal fade in');
    } else {
        alert('只能选择一条数据！');
        return false;
    };


    //		return false;

    return false;
};
//点击编辑框的确定按钮
function devEditDel() {
    var devIdE = $("#devIdE").val();
    var devLongE = $("#devLongE").val();
    var devLatiE = $("#devLatiE").val();

    // alert($("input#ename").val());
    // if (!document.getElementById("usreoffice").value || !document.getElementById("usrerole").value)
    // // if(!($("#NewNumber").val()))
    // {
    //     alert("这些项为必填项");
    // } else {
    var editDevObject = {
        "dat": [{
            "productId": productId,
            "deviceId": deviceId,
            "deviceIdentifier": devIdE,
            "deviceLongitude": devLongE,
            "deviceLatitude": devLatiE,

        }],
    }
    // alert(document.getElementById("usrerole").value);
    var editDev1 = editDevObject['dat'][0];

    // alert("usdycbjcdghjsd"+editUser1);
    //向后端传送数据保存
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/device/updateDevice",
        data: JSON.stringify(editDev1),
        contentType: "application/json;charset=UTF-8",
        success: function (response) {
            if (response.meta.success) {
                alert(response.meta.message);
                location.reload();
            } else {
                alert(response.meta.message);
            }
        },
        error: function (XmlHttpRequest, textStatus, errThrown) {

            console.log(XmlHttpRequest.status);
        }
    });
    // }

}