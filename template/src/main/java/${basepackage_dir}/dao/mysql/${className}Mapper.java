/*
 * 文 件 名:  ${table.className}Mapper.java
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
package ${basepackage}.dao.mysql;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ${basepackage}.model.${className};

@Repository
public interface ${className}Mapper {
    
    ${className} getById(Integer id);
    
    int countBy(Map<String, Object> params);

    List<${className}> listPage(Map<String, Object> params);
    
    int insert(${className} ${classNameLower});
    
    int update(${className} ${classNameLower});
    
    int deleteById(Integer id);
}