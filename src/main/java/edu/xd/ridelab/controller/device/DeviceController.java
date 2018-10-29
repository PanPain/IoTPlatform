package edu.xd.ridelab.controller.device;

import edu.xd.ridelab.controller.response.ResponseResult;
import edu.xd.ridelab.service.device.DeviceService;
import edu.xd.ridelab.vo.DeviceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zjh
 * @Date 2018/08/13,20:32
 */
@Api(value = "设备管理模块")
@Controller
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "查看所有设备", notes = "查看所有设备信息")
    @RequestMapping(value="/searchDevices", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult searchDevices(@ApiParam(value = "当前页数，可不填") @RequestParam(defaultValue = "1") int curPage, @ApiParam(value = "偏移量，可不填") @RequestParam(defaultValue = "10") int pageNum) {

        int offset = (curPage - 1) * pageNum;
        ResponseResult responseResult = new ResponseResult();
        List<DeviceVO> deviceVOList = null;

        try{
            deviceVOList = deviceService.getAllDevice(offset, pageNum);
            responseResult.setData(deviceVOList);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());
        } catch (Exception e) {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.GET_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_FAILURE.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @ApiOperation(value = "添加设备", notes = "添加一台设备信息")
    @RequestMapping(value = "/addDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addDevice(@ApiParam(value = "设备VO")@RequestBody DeviceVO deviceVO) {

        ResponseResult responseResult = new ResponseResult();
        try{
            int result = deviceService.addDevice(deviceVO);
            if(result == 0){
                responseResult.setSuccess(false);
                responseResult.setCode(DeviceCode.ADD_DEVICE_FAILURE.getCode());
                responseResult.setMessage(DeviceCode.ADD_DEVICE_FAILURE.getMessage());
            }
            else{
                responseResult.setData(result);
                responseResult.setSuccess(true);
                responseResult.setCode(DeviceCode.ADD_DEVICE_SUCCESS.getCode());
                responseResult.setMessage(DeviceCode.ADD_DEVICE_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.ADD_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.ADD_DEVICE_FAILURE.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @ApiOperation(value="编辑设备", notes="编辑一台设备的信息")
    @RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateDevice(@ApiParam(value = "设备VO") @RequestBody DeviceVO deviceVO) {

        ResponseResult responseResult = new ResponseResult();
        try{
            int result = deviceService.updateDevice(deviceVO);
            if(result == 0){
                responseResult.setSuccess(false);
                responseResult.setCode(DeviceCode.UPDATE_DEVICE_FAILURE.getCode());
                responseResult.setMessage(DeviceCode.UPDATE_DEVICE_FAILURE.getMessage());
            }
            else {
                responseResult.setData(result);
                responseResult.setSuccess(true);
                responseResult.setCode(DeviceCode.UPDATE_DEVICE_SUCCESS.getCode());
                responseResult.setMessage(DeviceCode.UPDATE_DEVICE_SUCCESS.getMessage());
            }

        } catch (Exception e) {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.UPDATE_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.UPDATE_DEVICE_FAILURE.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @ApiOperation(value="删除设备", notes="根据设备ID删除设备")
    @RequestMapping(value = "/deleteDevice", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult deleteDevice(@ApiParam(value = "设备ID") @RequestParam Long deviceId) {

        ResponseResult responseResult = new ResponseResult();
        try{
            int result = deviceService.deleteDevice(deviceId);
            if(result == 0){
                responseResult.setSuccess(false);
                responseResult.setCode(DeviceCode.DELETE_DEVICE_FAILURE.getCode());
                responseResult.setMessage(DeviceCode.DELETE_DEVICE_FAILURE.getMessage());
            }
            else{
                responseResult.setData(result);
                responseResult.setSuccess(true);
                responseResult.setCode(DeviceCode.DELETE_DEVICE_SUCCESS.getCode());
                responseResult.setMessage(DeviceCode.DELETE_DEVICE_SUCCESS.getMessage());
            }
        } catch (Exception e) {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.DELETE_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.DELETE_DEVICE_FAILURE.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @ApiOperation(value="查看设备详情", notes="根据设备ID查看设备详情")
    @RequestMapping(value = "/getDevDetail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getDeviceDetail(@ApiParam(value = "设备ID") @RequestParam Long deviceId) {

        ResponseResult responseResult = new ResponseResult();
        try{
            DeviceVO deviceVO = deviceService.getDeviceById(deviceId);

            responseResult.setData(deviceVO);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.GET_DEVICE_DETAIL_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_DETAIL_SUCCESS.getMessage());

        } catch (Exception e) {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.GET_DEVICE_DETAIL_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_DETAIL_FAILURE.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

    @ApiOperation(value="按产品ID查看设备", notes="选择一个产品ID，查看其对应的设备")
    @RequestMapping(value = "/getDevByProduct", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getDevByProduct(@ApiParam(value = "产品ID") @RequestParam Long productId) {

        ResponseResult responseResult = new ResponseResult();
        List<DeviceVO> deviceVOList = null;
        try{
            deviceVOList = deviceService.getDeviceByProductId(productId);

            responseResult.setData(deviceVOList);
            responseResult.setSuccess(true);
            responseResult.setCode(DeviceCode.GET_DEVICE_SUCCESS.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_SUCCESS.getMessage());

        } catch (Exception e) {
            responseResult.setSuccess(false);
            responseResult.setCode(DeviceCode.GET_DEVICE_FAILURE.getCode());
            responseResult.setMessage(DeviceCode.GET_DEVICE_FAILURE.getMessage());
            e.printStackTrace();
        }

        return responseResult;
    }

//    /**
//     * @Author FBY
//     * @Description
//     * @Date 10:17 2018/8/14
//     * @Param
//     * @Return
//     **/
//    @RequestMapping(value = "/getAllDeviceInfo",method = RequestMethod.GET)
//    public ResponseResult getAllDeviceInfo(@RequestParam(defaultValue = "1") int curPage,@RequestParam(defaultValue = "10") int tagNum){
//        ResponseResult responseResult = new ResponseResult();
//        int offset =  (curPage - 1) * tagNum;
//        List<DeviceVO> deviceVOLists = null;
//        List<DeviceModel> deviceModelLists = new ArrayList<>();
//        deviceVOLists = deviceService.getAllDevice(offset,tagNum);
//        DeviceModel deviceModel = null;
//        for(int i = 0; i < deviceVOLists.size(); i++){
//            if(deviceVOLists.get(i).getTimeStamp() != null){
//                deviceModel = new DeviceModel();
//                Timestamp d = new Timestamp(System.currentTimeMillis());
//                double time = (d.getTime() - deviceVOLists.get(i).getTimeStamp().getTime())/(1000*60);
//                if(time <= 5){
//                    deviceModel.setDevice(deviceVOLists.get(i));
//                    deviceModel.setStatus(true);
//                    deviceModelLists.add(deviceModel);
//                }
//                else{
//                    deviceModel.setDevice(deviceVOLists.get(i));
//                    deviceModel.setStatus(false);
//                    deviceModelLists.add(deviceModel);
//                }
//            }
//        }
//        if(deviceModelLists.size() != 0){
//            responseResult.setData(deviceModelLists);
//            responseResult.setSuccess(true);
//            responseResult.setCode(DeviceMonitorCode.GET_DEVICE_MONITOR_SUCCESS.getCode());
//            responseResult.setMessage(DeviceMonitorCode.GET_DEVICE_MONITOR_SUCCESS.getMessage());
//        }
//        else {
//            responseResult.setData(deviceModelLists);
//            responseResult.setSuccess(false);
//            responseResult.setCode(DeviceMonitorCode.NO_DEVICE_INFO.getCode());
//            responseResult.setMessage(DeviceMonitorCode.NO_DEVICE_INFO.getMessage());
//        }
//        return responseResult;
//    }
}
