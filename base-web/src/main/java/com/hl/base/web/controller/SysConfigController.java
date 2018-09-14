package com.hl.base.web.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hl.base.facade.model.SysConfig;
import com.hl.base.facade.service.ISysConfigService;
import com.hl.base.util.constants.YesAndNoType;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 云调度-配置 前端控制器
 * </p>
 * @author liheyu
 * @date 2018-8-1 14:44:10
 */
@RestController
@Slf4j
@RequestMapping("/sysConfig")
@Api("系统配置相关API")
public class SysConfigController extends BaseController{

	@Reference
	private ISysConfigService sysConfigService;
	
	@PostMapping(value = "/listPage")
	public Page<SysConfig> listPage(@RequestBody SysConfig sysConfig) {
		return sysConfigService.selectPage(new Page<SysConfig>(1,10), new EntityWrapper<SysConfig>(sysConfig));
	}
	
	@GetMapping(value = "/queryById/{id}")
	public SysConfig queryById(@PathVariable(value="id") String id) {
		return sysConfigService.selectById(id);
	}
	
	@PostMapping(value = "/save")
	public boolean save(@RequestBody @Valid SysConfig sysConfig) {
		return sysConfigService.insertOrUpdate(sysConfig);
	}
	
	@PostMapping("/export")
	public void exportConfig(@RequestBody SysConfig config, HttpServletResponse response) {
		try {
			List<SysConfig> configs = sysConfigService.selectList(new EntityWrapper<>(config));
			ExportParams exportParams = new ExportParams("系统配置", "sheet1");
			Workbook workbook = ExcelExportUtil.exportExcel(exportParams, SysConfig.class, configs);
			if(workbook != null) {
				response.setCharacterEncoding("UTF-8");
				response.setHeader("content-Type", "application/vnd.ms-excel");
				response.setHeader("content-disposition", "attachment;filename=" + new String("系统配置.xlsx".getBytes("gbk"), "iso-8859-1"));
				workbook.write(response.getOutputStream());
			}
		} catch (Exception e) {
			log.error("导出配置信息出错！", e);
		}
	}
	
	@PostMapping(value = "/import")
	public List<SysConfig> importConfig(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            params.setDictHandler(dictHandler);
            params.setNeedVerfiy(true);
            ExcelImportResult<SysConfig> result = ExcelImportUtil.importExcelMore(file.getInputStream(), SysConfig.class, params);
            if(result.isVerfiyFail()) {
            	result.getFailList().forEach(fail -> System.out.println(fail));
            } else {
            	SysConfig config = new SysConfig();
            	result.getList().forEach(cfg -> {
            		config.setDataKey(cfg.getDataKey());
            		if(StringUtils.isNoneBlank(cfg.getOrgId())) {
						cfg.setOrgId(orgCache.getCache().getUnchecked(cfg.getOrgId()));
						config.setOrgId(orgCache.getCache().getUnchecked(cfg.getOrgId()));
					}
            		if(sysConfigService.selectCount(new EntityWrapper<SysConfig>(config)) > 0){
            			cfg.setErrorMsg("重复的配置项:" + cfg.getDataKey());
            			result.getFailList().add(cfg);
            			result.setVerfiyFail(true);
            		} else {
            			cfg.setCreateTime(new Date(System.currentTimeMillis()));
            			cfg.setStatus(YesAndNoType.YES.getValue());
            			//cfg.setCreateUser(SessionUtils.getLoginUserId());
            		}
            	});
            }
            if(!result.isVerfiyFail()) {
            	sysConfigService.insertOrUpdateAllColumnBatch(result.getList(), 20);
            }
    		return result.getFailList();
        } catch (Exception e) {
			log.error("配置导入失败", e);
		}
		return null;
	}
}
