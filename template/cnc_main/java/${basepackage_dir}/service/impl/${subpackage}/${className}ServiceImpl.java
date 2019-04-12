/*
 * 文 件 名:  ${table.className}ServiceImpl.java
 * 创 建 人:  
 * 创建时间:  <#if now??>${now?string('yyyy-MM-dd')}</#if>
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
package ${basepackage}.service.impl.${subpackage};

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import ${basepackage}.service.api.${subpackage}.${className}Service;
import ${basepackage}.model.${className};
import ${basepackage}.dao.mysql.${className}Mapper;

/**
 * <一句话功能简述>
 */
@Service
public class ${className}ServiceImpl implements ${className}Service {
	
    @Resource
    private ${className}Mapper ${classNameLower}Mapper;
    
	public int add(${className} ${classNameLower}) {
        int rows = ${classNameLower}Mapper.insert(${classNameLower});
        return rows;
	}

    
    public int update(${className} ${classNameLower}) {
        int rows = ${classNameLower}Mapper.update(${classNameLower});
        return rows;
    }
    
    
    public int delete(Integer id) {
        int rows = ${classNameLower}Mapper.deleteById(id);
        return rows;
    }
    
    
    public ${className} getById(Integer id) {
		${className} ${classNameLower} = ${classNameLower}Mapper.getById(id);
		//
        return ${classNameLower};
    }
	
	public Integer countBy(Map<String, Object> params){
		processPageParams(params);
		Integer rows = ${classNameLower}Mapper.countBy(params);
		return rows;
	}
	
	public List<${className}> listPage(Map<String, Object> params){
		processPageParams(params);
		List<${className}> lists = ${classNameLower}Mapper.listPage(params);
		
		return lists;
	}

	
	private static void processPageParams(Map<String, Object> params){
		// 此段代码可以迁移到工具类之中
		if(null == params){
			return;
		}
		Integer pageSize = 20;
		Integer page = 0;
		Object _pageSize = params.get("pageSize");
		Object _page = params.get("page");
		if(null != _pageSize){
			pageSize = (Integer)_pageSize;
		}
		if(null != _page){
			page = (Integer)_page;
		}
		//
		Integer start = page * pageSize;
		//
		params.put("_start", start);
		params.put("_pageSize", pageSize);
	}
    
}
