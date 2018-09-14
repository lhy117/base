package ${package.Controller};

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment}控制器
 * </p>
 * @author ${author}
 * @date ${.now}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {

	@Reference
	private I${entity}Service ${entity?uncap_first}Service;
	
	/**
	 * 分页查询
	 * @param ${entity?uncap_first} 查询对象
	 * @return
	 */
	@PostMapping(value = "/listPage")
	public Page<${entity}> listPage(@RequestBody ${entity} ${entity?uncap_first}) {
		return ${entity?uncap_first}Service.selectPage(new Page<${entity}>(1,10), new EntityWrapper<${entity}>(${entity?uncap_first}));
	}
	
	/**
	 * 保存或者更新
	 * @param ${entity?uncap_first} 保存或者更新对象
	 * @return
	 */
	@PostMapping(value = "/save")
	public boolean save(@RequestBody ${entity} ${entity?uncap_first}) {
		return ${entity?uncap_first}Service.insertOrUpdate(${entity?uncap_first});
	}
}
</#if>
</#if>
