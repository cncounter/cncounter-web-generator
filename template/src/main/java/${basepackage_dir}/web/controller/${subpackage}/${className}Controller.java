<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
package ${basepackage}.mvc.controller.${subpackage};

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cncounter.common.util.StringNumberUtil;
import com.cncounter.common.vo.JSONMessage;
import com.cncounter.common.web.ControllerBase;
import ${basepackage}.model.${className};
import ${basepackage}.service.${className}Service;

/**
 * @version 1.0
 * @author 
 */
@Controller
@RequestMapping("/${classNameLower}")
public class ${className}Controller extends ControllerBase {
    
    @Resource
    private ${className}Service ${classNameLower}Service;
    
    @RequestMapping(value = "/list.json")
    @ResponseBody
    public JSONMessage<${className}> list(HttpServletRequest request) {
        // get params
        Map<String, Object> params = parseParamMapObject(request);
        //
        Integer count = ${classNameLower}Service.countBy(params);
        List<${className}> ${classNameLower}List = ${classNameLower}Service.listPage(params);
        //
        JSONMessage<${className}> jsonMessage = JSONMessage.success();
        jsonMessage.setTotal(count).setMessage("获取成功");
        jsonMessage.setData(${classNameLower}List);

        return jsonMessage;
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage<${className}> doAdd(HttpServletRequest request) {
        // get params
        Map<String, Object> params = parseParamMapObject(request);
        //
        ${className} ${classNameLower} = new ${className}();
        //
        BeanUtils.copyProperties(params, ${classNameLower});
        //
        Integer rows = ${classNameLower}Service.add(${classNameLower});

        //
        JSONMessage<${className}> jsonMessage = JSONMessage.success();
        if(rows < 1){
            jsonMessage = JSONMessage.failure().setMessage("操作失败");
        } else {
            ${className} data = ${classNameLower}Service.getById(${classNameLower}.getId());
            jsonMessage.setData(data);
        }
        return jsonMessage;
    }
    

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage<${className}> doEdit(HttpServletRequest request) {
        // get params
        Map<String, Object> params = parseParamMapObject(request);
        //
        ${className} ${classNameLower} = new ${className}();
        //
        BeanUtils.copyProperties(params, ${classNameLower});
        //
        Integer rows = ${classNameLower}Service.update(${classNameLower});

        //
        JSONMessage<${className}> jsonMessage = JSONMessage.success();
        if(rows < 1){
            jsonMessage = JSONMessage.failure().setMessage("操作失败");
        } else {
            ${className} data = ${classNameLower}Service.getById(${classNameLower}.getId());
            jsonMessage.setData(data);
        }
        return jsonMessage;
    }
    

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage delete(HttpServletRequest request) {
        // get params
        Map<String, Object> params = parseParamMapObject(request);
        //
        Integer id = 0;
        Object _id = params.get("id");
        if(null != _id && StringNumberUtil.isLong(_id.toString())){
            id = StringNumberUtil.parseInt(_id.toString(), 0);
        }
        //
        Integer rows = ${classNameLower}Service.delete(id);

        //
        JSONMessage jsonMessage = JSONMessage.success();
        if(rows < 1){
            jsonMessage = JSONMessage.failure().setMessage("操作失败");
        }
        return jsonMessage;
    }

}
