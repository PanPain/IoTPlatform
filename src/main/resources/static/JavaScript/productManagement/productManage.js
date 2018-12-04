
//点击新增按钮
$('#productAdd').click(function (e) {
    var addR = $('#productAddModal1');
    console.log(addR);
    addR.css('display', 'block');
    addR.attr('class', 'modal fade in');
});
//点击新增弹框中的取消按钮和×按钮，关闭弹窗
$('.proDelAdd').click(function (e) {
    var addR = $('#productAddModal');
    var addR1 = $('#productAddModal1');
    console.log(addR);
    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
    addR1.css('display', 'none');
    addR1.attr('class', 'modal fade');
});
//点击新增产品弹框中的取消按钮和×按钮，关闭弹窗
$('.proDelAdd1').click(function (e) {
    var addR = $('#productAddModal1');
    console.log(addR);
    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
});
//点击新增产品按钮弹出第二个新增框
$('.proaddPro').click(function (e) {
    var addR = $('#productAddModal');
    console.log(addR);
    addR.css('display', 'block');
    addR.attr('class', 'modal fade in');
});

//属性标签页点击添加属性按钮
$("#addAttribute").click(function () {
    var newRow = $("#addAttributeEvent").clone();
    $("#append").append(newRow);
    $(".text:last").val("");
    $(".model-id1:last").val("");
    $(".model-desc1:last").val("");
    var judge = $("div[id='addAttributeEvent']").size();
    console.log(judge);
    if (judge >= 2) {
        $("#deleteAttribute").css("display", "inline");
    }

});
//属性标签页点击删除属性按钮
$("#deleteAttribute").click(function () {
    var judge = $('.addAttributeEvent').size();
    console.log(judge + "ddddd");
    $(".addAttributeEvent:last").remove();
    if (judge - 1 == 1) {
        $("#deleteAttribute").css("display", "none");
    }
})
//服务标签页点击添加服务按钮
$("#addService").click(function () {
    var appendEvent= $("#addAttributeEvent1").clone(true);
    $("#append1").append(appendEvent);
    var len = appendEvent.find(".template").length;

    if(len>1){
        appendEvent.find(".template").slice(0,len-1).remove();
    }
    appendEvent.find("#deleteButton").css("display", "none");

    appendEvent.find(".service").css("margin-top", "0px").css("margin-left", "0px");

    $(".model-name2:last").val("");
    $(".model-id2:last").val("");
    $(".param-name:last").val("");
    $(".param-id:last").val("");
    $(".model-desc2:last").val("");
    var judge = $("div[id='addAttributeEvent1']").size();
    console.log(judge);
    if (judge >= 2) {
        $("#deleteService").css("display", "inline");
    }
});
//服务标签页点击删除服务按钮
$("#deleteService").click(function () {
    var judge = $('.addAttributeEvent1').size();
    console.log(judge + "ddddd");
    $(".addAttributeEvent1:last").remove();
    if (judge - 1 == 1) {
        $("#deleteService").css("display", "none");
    }
})

//服务标签页点击添加服务参数按钮
$("#addParam").click(function () {
    // alert($(this).parent().find("#template"));
    var newRow =  $(this).parent().find("#template:first").clone();
    newRow.find("#service").css("margin-top", "12px").css("margin-left", "130px");

    newRow.find("#deleteButton").css("display", "block");

    newRow.find("#deleteButton").off().click(function (e) {
        $(this).parent().parent().remove();
    });
    $(this).parent().find("#copy").append(newRow);
    $(".param-name:last").val("");
    $(".param-id:last").val("");

});

//事件标签页点击添加服务参数
$("#addParam1").click(function () {
    var newRow =  $(this).parent().find("#template1:first").clone();
    newRow.find("#service1").css("margin-top", "12px").css("margin-left", "130px");

    newRow.find("#deleteButton1").css("display", "block");

    newRow.find("#deleteButton1").off().click(function (e) {
        $(this).parent().parent().remove();
    });
    $(this).parent().find("#copy1").append(newRow);
    $(".param-name1:last").val("");
    $(".param-id1:last").val("");


});
//添加单个产品
function addProduct(arr, index) {
    var str = '<tr id="myTr" ><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">' +
        '<input type="checkbox" class="ace chk" name="chk" data-id="' + arr.productId + '" data-index="' + index + '" data-name="' + arr.productName + '" data-manufac="' + arr.productManufacturer + '" data-desc="' + arr.productDesc + '" data-code="' + arr.productCode + '"/>' + '<span class="lbl"></span></label></td><td>' + index + '</td>';

    var str1 = str + '<td class="proname11 ">' + arr.productName + '</td>' + '<td class="promanu11 ">' + arr.productManufacturer + '</td>' + '<td class="procode11 ">' + arr.productCode + '</td>' + '<td class="prodesc11">' + arr.productDesc + '</td>';
    // var a=$("#myTr").data("id");
    str1 += '<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a id="proView" class="blue proView"  data-toggle="modal" onclick="view(this)" data-id="' + arr.productId + '"><i id="proView" class="ace-icon fa fa-search-plus bigger-130" ></i> </a><a id="proEdit" class="green proEdit" href="#modal-table" data-toggle="modal"><i class="ace-icon fa fa-pencil bigger-130"></i></a><a id="proDelRow" class="red proDelRow"  data-toggle="modal" data-id="'+ arr.productId +'" onclick="del(this)"><i class=" ace-icon fa fa-trash-o bigger-130"></i></a>&nbsp&nbsp<button id="op" class="btn btn-xs btn-success" style="block-inline"  value="开启" data-id="'+ arr.productId +'" onclick="op(this)">开启</button></div>';
    str1 += '<div class="hidden-md hidden-lg"><div class="inline pos-rel"> <button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto"><i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close"><li><a href="#modal-table" class="tooltip-info" data-rel="tooltip" title="View" data-toggle="modal"> <span class="blue"><i class="ace-icon fa fa-search-plus bigger-120"></i></span></a></li><li><a href="#modal-table" class="tooltip-success" data-rel="tooltip" title="Edit" data-toggle="modal"><span class="green"><i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a href="#modal-table" class="tooltip-error" data-rel="tooltip" title="Delete" data-toggle="modal"><span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div> </div></td></tr>';


    return str1;
}

//循环添加所有产品信息
function addAllProduct(arr) {
    var str = '';
    var dataArray = arr['data'];
    var meta = arr['meta'];
    var a = meta['success'];
    var dataArr = dataArray['list'];

    if (!meta['success']) {
        return -1;
    } else {
        var len = dataArr.length;
        var index = 1;
        for (var i = 0; i < len; i++, index++) {
            str += addProduct(dataArr[i], index);
        }
        return str;
    }
}
//获取到数据之后执行的回调函数
function updateTable(data) {
    var result = JSON.stringify(data);
    allProductTable = JSON.parse(result);
    //在执行之前先删除表格中的所有元素，重新进行动态添加
    var tby = $('#dynamic-table tbody').eq(0);
    var trs = tby.children('tr');
    var str = addAllProduct(allProductTable);
    if (str == -1) {
        console.log('数据请求失败！');
    } else {
        console.log('数据请求成功，正在更新表格！');
        tby.empty();
        tby.html(str);
    }
}

function op(obj){
    var id =$(obj).attr("data-id");
    var val = obj.innerHTML;

if(val =="开启"){
    var URL = '/receiver/'+id+'/action/start';
    $.ajax({
        type: 'POST',
        url: URL,
        // dataType: 'json', //指定服务器返回的数据类型(在不需要返回值的情况下，扔按模板格式，设置了dataType:"json",参数；这时候，ajax传值正确时，出现200返回成功状态下报错的特殊情况
        success: function (response) {
            alert("开启成功");
            obj.innerHTML = "关闭";
            obj.classList.remove("btn-success");
            obj.classList.add("btn-info");
        },
        error: function (errorThrown) {
            alert("开启失败");
            console.log(errorThrown);
        },
    });
   }
    else{
    var URL = '/receiver/'+id+'/action/stop';
    $.ajax({
        type: 'POST',
        url: URL,
        // dataType: 'json', //指定服务器返回的数据类型(在不需要返回值的情况下，扔按模板格式，设置了dataType:"json",参数；这时候，ajax传值正确时，出现200返回成功状态下报错的特殊情况
        success: function (response) {
            alert("关闭成功");
            obj.innerHTML = "开启";
            obj.classList.remove("btn-info");
            obj.classList.add("btn-success");
        },
        error: function (errorThrown) {
            alert("关闭失败");
            console.log(errorThrown);
        },
    });

}
}
// $("#op").click(function () {
//     alert("ss");
//     var id =$(this).attr("data-id");
//     alert(id);
// })

$(function () {
    //jQuery的入口函数
    //跨域请求服务端的数据，等待数据返回，更新页面的表格数据
    //1. 第一种方法：原生的ajax请求
    //getResources();
    //2. 使用jQuery的ajax跨域方式
    //getResourcesjQuery();
    //使用jQuery的ajax请求，用于获取所有的人员信息

    //拼接参数：获取所有的人员不需要传递参数
    // var params='';
    var URL = '/product/getAddedProducts'; //对应到接口的位置
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
// 新增页面点击确定按钮
$('.page-content').on('click', '#addProduct', function (e) {
    e.stopPropagation();
    //    alert(newRowNum)

    //属性
    var attrCount = $("#attrData").find(".model-desc1").length;
    var attrData = new Array();
    for (var i = 0; i < attrCount; i++) {
        var attr = {
            "modelType": 1,
            "modelName": $("#attrData").find(".text").eq(i).val(),
            "modelIdentifier": $("#attrData").find(".model-id1").eq(i).val(),
            "serviceDesc": $("#attrData").find(".model-desc1").eq(i).val(),
            "dataType": parseInt($("#attrData").find(".model-dataType1").eq(i).val()),
            "serviceParam": ""
        }

        attrData.push(attr);
    }
    //服务
    var serCount = $("#attrData1").find(".model-name2").length;
    // alert(serCount);
    var serData = new Array();
    for (var i = 0; i < serCount; i++) {
        var newRowNum = $("#attrData1").find(".service-param").eq(i).find(".param-name").length;
        var pname1 = new Array();
        var pid1 = new Array();
        var ptype1 = new Array();
        for (var j = 0; j < newRowNum; j++) {
            pname1[j] = $("#attrData1").find(".service-param").eq(i).find(".param-name").eq(j).val();
            pid1[j] = $("#attrData1").find(".service-param").eq(i).find(".param-id").eq(j).val();
            ptype1[j] = $("#attrData1").find(".service-param").eq(i).find(".param-type").eq(j).val();

        }
        servicePar1 = {
            "pname1": pname1,
            "pid1": pid1,
            "ptype1": ptype1,
        }
        servicePar1 = JSON.stringify(servicePar1);
        var ser = {
            "modelType": 2,
            "modelName": $("#attrData1").find(".model-name2").eq(i).val(),
            "modelIdentifier": $("#attrData1").find(".model-id2").eq(i).val(),
            "serviceDesc": $("#attrData1").find(".model-desc2").eq(i).val(),
            "dataType":0,
            "serviceParam": servicePar1
        }
        serData.push(ser);
    }
    // console.log(JSON.stringify(serData));
//model写成拼接数组的形式 拼接三个数组 分别是属性服务事件 先读取各自的数量 最后是这种形式
    var model = attrData.concat(serData);
    console.log(model);
    var newProductObject = {
        "dat": [{
            "productName": $("input#proName").val(),
            "productManufacturer": $("input#proManu").val(),
            "productCode": $("input#proType").val(),
            "productDesc": $("input#proDes").val(),
            "deviceModel": model
        }],
    }
    var newPro = newProductObject['dat'][0];
    console.log(newPro);
    console.log(typeof (newPro));

    //向后端传送数据保存
    $.ajax({
        type: "POST",
        url: "/product/addProduct",
        data: JSON.stringify(newPro),
        contentType: "application/json;charset=UTF-8",
        success: function (response) {

            if (response.meta.success) {
                alert(response.meta.message);
                location.reload();
            } else {}
        },
        error: function (XmlHttpRequest, textStatus, errThrown) {
            console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
        }
    });
});
//页面详情
function view(ob) {

    var a = $("#attriView").find(".modelNameV1").length;
    if(a>=1){
       $("#attriView").find(".addAttributeEventv").slice(1).remove();
    }
    var b = $("#serView").find(".modelNameV2").length;

    if(b>=1){
        $("#serView").find(".addAttributeEvent1v").slice(1).remove();
    }
    var c = $("#serView").find(".param-nameV2").length;

    if(c>=1){
        $("#serView").find(".template3").slice(1).remove();
    }
    $("#attriView").find(".modelNameV1").val('');
    $("#attriView").find(".modelIdV1").val('');
    $("#attriView").find(".modelTypeV1").val("");
    $("#attriView").find(".modelDescV1").val('');
    $("#attriView").find(".modelNameV2").val('');
    $("#attriView").find(".modelIdV2").val('');
    $("#attriView").find(".param-nameV2").val("");
    $("#attriView").find(".param-idV2").val('');
    $("#attriView").find(".param-typeV2").val('');
    $("#attriView").find(".param-modelDescV2").val('');
        var vproId = $(ob).attr("data-id");
        //向后端传送数据保存
        $.ajax({
            type: "POST",
            url: "/product/getProductInfo",
            data: "productId=" + vproId,
            success: function (response) {
                viewProduct(response);
            },
            error: function (XmlHttpRequest, textStatus, errThrown) {
                console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
            }
        });
        var addR = $('#productViewModal');
        console.log(addR);
        addR.css('display', 'block');
        addR.attr('class', 'modal fade in');

    return false;
};

function filterAttrData(data){
    return data.modelType == 1;
}
function filterSerData(data){
    return data.modelType == 2;
}
function viewProduct(response) {
    var result = JSON.stringify(response);
    allProductView = JSON.parse(result);
    console.log(allProductView);
    var data = allProductView['data'];
    var meta = allProductView['meta'];
    console.log(JSON.stringify(allProductView));
    if (!response.meta.success) {
        console.log('Exception:' + meta['message']);
        alert("获取详情失败！");
    } else {
        $("#proNameV").val(data['productName']);
        $("#proManuV").val(data['productManufactuter']);
        $("#proTypeV").val(data['productCode']);
        document.getElementById("proDescV").innerHTML = data['productDesc'];
        var model = data['deviceModel'];

        var attrv = new Array();
        attrv=JSON.parse(model).filter(filterAttrData);
        var servicev = new Array();
        servicev = JSON.parse(model).filter(filterSerData);
        // alert(JSON.stringify(attrv));
        var attrlength = attrv.length;
        var serlength = servicev.length;
        $("#attriView").find(".modelNameV1").eq(0).val(attrv[0]['modelName'])
        $("#attriView").find(".modelIdV1").eq(0).val(attrv[0]['modelIdentifier'])

        $("#attriView").find(".modelDescV1").eq(0).val(attrv[0]['serviceDesc']);
        var Code = attrv[0]['dataType'];
        switch (Code) {
            case 0:
                $("#attriView").find(".modelTypeV1").eq(0).val("空");
                break;
            case 1:
                $("#attriView").find(".modelTypeV1").eq(0).val("整数型");
                break;
            case 2:
                $("#attriView").find(".modelTypeV1").eq(0).val("浮点型");
                break;
            case 3:
                $("#attriView").find(".modelTypeV1").eq(0).val("枚举型");
                break;
            case 4:
                $("#attriView").find(".modelTypeV1").eq(0).val("布尔型");
                break;
            case 5:
                $("#attriView").find(".modelTypeV1").eq(0).val("字符串");
                break;
            case 6:
                $("#attriView").find(".modelTypeV1").eq(0).val("时间型");
                break;
        }
        for (var i = 1 ; i < attrlength; i++){
            var newRow = $("#addAttributeEventv").clone();
            $("#appendv").append(newRow);
            $("#attriView").find(".modelNameV1").eq(i).val(attrv[i]['modelName'])
            $("#attriView").find(".modelIdV1").eq(i).val(attrv[i]['modelIdentifier'])

            $("#attriView").find(".modelDescV1").eq(i).val(attrv[i]['serviceDesc']);
            var Code = attrv[i]['dataType'];
            switch (Code) {
                case 0:
                    $("#attriView").find(".modelTypeV1").eq(i).val("空");
                    break;
                case 1:
                    $("#attriView").find(".modelTypeV1").eq(i).val("整数型");
                    break;
                case 2:
                    $("#attriView").find(".modelTypeV1").eq(i).val("浮点型");
                    break;
                case 3:
                    $("#attriView").find(".modelTypeV1").eq(i).val("枚举型");
                    break;
                case 4:
                    $("#attriView").find(".modelTypeV1").eq(i).val("布尔型");
                    break;
                case 5:
                    $("#attriView").find(".modelTypeV1").eq(i).val("字符串");
                    break;
                case 6:
                    $("#attriView").find(".modelTypeV1").eq(i).val("时间型");
                    break;
            }
        }

        // $("#modelNameV2").val(servicev['modelName']);
        // $("#modelIdV2").val(servicev['modelIdentifier']);
        // $("#modelDescV2").val(servicev['serviceDesc']);

        // var param1 = servicev['serviceParam'];
        // param1 = JSON.parse(param1);
        // var pnameV1 = param1['pname1'];
        // var pid1 = param1['pid1']
        // var ptype1 = param1['ptype1']
        // console.log(pnameV1);
        // var paramNum = pnameV1.length;
        // writeSerParam(paramNum, pnameV1, pid1, ptype1);
        for (var i = 1 ; i < serlength; i++){
            var newRow = $("#addAttributeEvent1v").clone();
            $("#append1v").append(newRow);
        }
        for(var j = 0; j < serlength; j++){
            $("#serView").find(".modelNameV2").eq(j).val(servicev[j]['modelName']);
            $("#serView").find(".modelIdV2").eq(j).val(servicev[j]['modelIdentifier']);
            $("#serView").find(".modelDescV2").eq(j).val(servicev[j]['serviceDesc']);
        }
        for(var k = 0; k < serlength; k++){
            var param1 = JSON.parse(servicev[k].serviceParam);
            //   alert(JSON.stringify(param1));
            var pname1 = param1['pname1'];
            var pid1 = param1['pid1'];
            var ptype1 = param1['ptype1'];
            var serparlen = pname1.length;
            writeSerParam(serparlen, pname1, pid1, ptype1,k);
        }
    }
}

function writeSerParam(paramNum, pnameV1, pid1, ptype1,index) {
    for (var i = 0; i < paramNum - 1; i++) {
        var newRow = $("#template3").clone();
        newRow.find("#service3").css("margin-top", "12px").css("margin-left", "148px");
        $("#serView").find(".service-param3").eq(index).find(".copy3").append(newRow);

    }
    for (var j = 0; j < paramNum; j++) {
        $("#serView").find(".service-param3").eq(index).find(".param-nameV2").eq(j).val(pnameV1[j]);
        $("#serView").find(".service-param3").eq(index).find(".param-idV2").eq(j).val(pid1[j]);
        var Code1 = ptype1[j];

        switch (Code1) { //code1是String类型，case后值也应是String,而不是int
            case "0":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("空");
                break;
            }
            case "1":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("整数型");
                break;
            }
            case "2":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("浮点型");
                break;
            }
            case "3":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("枚举型");
                break;
            }
            case "4":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("布尔型");
                break;
            }
            case "5":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("字符串");
                break;
            }
            case "6":
            {
                $("#serView").find(".service-param3").eq(index).find(".param-typeV2").eq(j).val("时间型");
                break;
            }
        }
    }
}
//点击明细弹框中的取消按钮和×按钮，关闭弹窗
$('.proDelView').click(function (e) {
    var addR = $('#productViewModal');
    console.log(addR);
    addR.css('display', 'none');
    addR.attr('class', 'modal fade');
});
//全选
$("#usrcheckAll").click(function () {
    if ($("#usrcheckAll").is(":checked")) {
        $("input[type='checkbox']").prop("checked", true);
    } else {
        $("input[type='checkbox']").prop("checked", false);
    }
});
//删除产品
function del(obj) {
    //alert(this);
        var rsu = confirm('确定要删除该产品吗？');
        if (rsu) {
            var delId = $(obj).attr('data-id');
            $.ajax({
                type: "POST",
                url: "/product/deleteProduct",
                data: "productId=" + delId,
                success: function (response) {
                    if (response.meta.success) {
                        alert('删除成功');
                        //alert(this);
                        $($("td :checked").parent().parent().parent()).remove();
                        window.location.reload();
                    }
                }
            });
        } else {
            alert('撤销操作！');
        }

};