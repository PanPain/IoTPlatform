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
//服务标签页点击添加服务参数
$("#addParam").click(function () {
    var newRow = $("#template").clone();
    newRow.find("#service").css("margin-top", "12px").css("margin-left", "130px");

    newRow.find("#deleteButton").css("display", "block");


    newRow.find("#deleteButton").off().click(function (e) {
        $(this).parent().parent().remove();
    });
    $("#copy").append(newRow);


});
//事件标签页点击添加服务参数
$("#addParam1").click(function () {
    var newRow = $("#template1").clone();
    newRow.find("#service1").css("margin-top", "12px").css("margin-left", "130px");

    newRow.find("#deleteButton1").css("display", "block");

    newRow.find("#deleteButton1").off().click(function (e) {
        $(this).parent().parent().remove();
    });
    $("#copy1").append(newRow);


});
//添加单个产品
function addProduct(arr, index) {
    var str = '<tr id="myTr" ><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">' +
        '<input type="checkbox" class="ace chk" name="chk" data-id="' + arr.productId + '" data-index="' + index + '" data-name="' + arr.productName + '" data-manufac="' + arr.productManufacturer + '" data-desc="' + arr.productDesc + '" data-code="' + arr.productCode + '"/>' + '<span class="lbl"></span></label></td><td>' + index + '</td>';

    var str1 = str + '<td class="proname11 ">' + arr.productName + '</td>' + '<td class="promanu11 ">' + arr.productManufacturer + '</td>' + '<td class="procode11 ">' + arr.productCode + '</td>' + '<td class="prodesc11">' + arr.productDesc + '</td>';
    // var a=$("#myTr").data("id");
    str1 += '<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a id="proView" class="blue proView"  data-toggle="modal" onclick="edit()"><i id="proView" class="ace-icon fa fa-search-plus bigger-130"></i> </a><a id="proEdit" class="green proEdit" href="#modal-table" data-toggle="modal"><i class="ace-icon fa fa-pencil bigger-130"></i></a><a id="proDelRow" class="red proDelRow"  data-toggle="modal" onclick="del()"><i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
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
        alert('数据请求失败！');
    } else {
        console.log('数据请求成功，正在更新表格！');
        tby.empty();
        tby.html(str);
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
    var newRowNum = $("#service-param").find(".param-name").length;
//    alert(newRowNum);
    var newRowNum1 = $("#service-param1").find(".param-name1").length;
//    alert(newRowNum1);
    var pname1 = new Array();
    var pid1 = new Array();
    var ptype1 = new Array();
    var pname2 = new Array();
    var pid2 = new Array();
    var ptype2 = new Array();
    for (var i = 0; i < newRowNum; i++) {
        pname1[i] = $("#service-param").find(".param-name").eq(i).val();
        pid1[i] = $("#service-param").find(".param-id").eq(i).val();
        ptype1[i] = $("#service-param").find(".param-type").eq(i).val();
    }
    console.log(pname1);
    servicePar1 = {
        "pname1": pname1,
        "pid1": pid1,
        "ptype1": ptype1,
    }

    for (var i = 0; i < newRowNum1; i++) {
        pname2[i] = $("#service-param1").find(".param-name1").eq(i).val();
        pid2[i] = $("#service-param1").find(".param-id1").eq(i).val();
        ptype2[i] = $("#service-param1").find(".param-type1").eq(i).val();
    }
    console.log(pid2);
    console.log(ptype2);
    servicePar2 = {
        "pname2": pname2,
        "pid2": pid2,
        "ptype2": ptype2,
    }
    servicePar1 =JSON.stringify(servicePar1);
    servicePar2 =JSON.stringify(servicePar2);


    var model = [
        {
            "modelType": 1,
            "modelName": $("input#model-name1").val(),
            "modelIdentifier": $("input#model-id1").val(),
            "serviceDesc": $("textarea#model-desc1").val(),
            "dataType": $('#model-dataType1 option:selected').attr('value'),
            "serviceParam": ""
        },
        {
            "modelType": 2,
            "modelName": $("input#model-name2").val(),
            "modelIdentifier": $("input#model-id2").val(),
            "serviceDesc": $("textarea#model-desc2").val(),
            "dataType": 0,
            "serviceParam": servicePar1
        },
        {
            "modelType": 3,
            "modelName": $("input#model-name3").val(),
            "modelIdentifier": $("input#model-id3").val(),
            "serviceDesc": $("textarea#model-desc3").val(),
            "dataType": 0,
            "serviceParam": servicePar2
        }
    ]
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
    console.log(typeof(newPro));

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
            } else {
            }
        },
        error: function (XmlHttpRequest, textStatus, errThrown) {
            console.log(XmlHttpRequest.status + "uuyyuuyyuuyy");
        }
    });
});
//页面详情
function edit(){
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
        var vproId = $("td :checked").attr("data-id");
        //向后端传送数据保存
        $.ajax({
            type: "POST",
            url: "/product/getProductInfo",
            data:  "productId="+ vproId,
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
    } else {
        alert('只能选择一条数据！');
        return false;
    };
    return false;
};
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
        var model = JSON.parse(data['deviceModel']);
        var attrv = model[0];
        var servicev = model[1];
        var eventv = model[2];
        $("#modelNameV1").val(attrv['modelName']);
        $("#modelIdV1").val(attrv['modelIdentifier']);
        document.getElementById("modelDescV1").innerHTML = attrv['serviceDesc'];
        $("#modelNameV2").val(servicev['modelName']);
        $("#modelIdV2").val(servicev['modelIdentifier']);
        $("#modelDescV2").val(servicev['serviceDesc']);
        var Code = attrv['dataType'];
        switch (Code) {
            case 0:
                $("#modelTypeV1").val("空");
                break;
            case 1:
                $("#modelTypeV1").val("整数型");
                break;
            case 2:
                $("#modelTypeV1").val("浮点型");
                break;
            case 3:
                $("#modelTypeV1").val("枚举型");
                break;
            case 4:
                $("#modelTypeV1").val("布尔型");
                break;
            case 5:
                $("#modelTypeV1").val("字符串");
                break;
            case 6:
                $("#modelTypeV1").val("时间型");
                break;
        }
        var param1 = servicev['serviceParam'];
        param1 = JSON.parse(param1);
        var pnameV1 = param1['pname1'];
        var pid1 = param1['pid1']
        var ptype1 = param1['ptype1']
        console.log(pnameV1);
        var paramNum = pnameV1.length;
        writeSerParam(paramNum, pnameV1, pid1, ptype1);
    }
}
function writeSerParam(paramNum, pnameV1, pid1, ptype1) {
    for (var i = 0; i < paramNum-1; i++) {
        var newRow = $("#template3").clone();
        newRow.find("#service3").css("margin-top", "12px").css("margin-left", "148px");
        $("#copy3").append(newRow);
    }
    for (var j = 0; j < paramNum; j++) {
        $("#service-param3").find(".param-nameV2").eq(j).val(pnameV1[j]);
        $("#service-param3").find(".param-idV2").eq(j).val(pid1[j]);
        var Code1 = ptype1[j];

        switch(Code1) {//code1是String类型，case后值也应是String,而不是int
            case "0":{
                $("#service-param3").find(".param-typeV2").eq(j).val("空");
                break;}
            case "1":{
                $("#service-param3").find(".param-typeV2").eq(j).val("整数型");
                break;}
            case "2":{
                $("#service-param3").find(".param-typeV2").eq(j).val("浮点型");
                break;}
            case "3":{
                $("#service-param3").find(".param-typeV2").eq(j).val("枚举型");
                break;}
            case "4":{
                $("#service-param3").find(".param-typeV2").eq(j).val("布尔型");
                break;}
            case "5":{
                $("#service-param3").find(".param-typeV2").eq(j).val("字符串");
                break;}
            case "6":{
                $("#service-param3").find(".param-typeV2").eq(j).val("时间型");
                break;}
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
function del(){
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
            var delId = $("td :checked").attr('data-id');
            $.ajax({
                type: "POST",
                url: "/product/deleteProduct",
                data:"productId="+delId,
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
    }
};