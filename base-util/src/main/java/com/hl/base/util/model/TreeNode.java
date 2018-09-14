package com.hl.base.util.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 树形结构
 * @author: liheyu
 * @date: 2017年9月27日 上午11:28:38
 * @version: V1.0
 */
@Data
public class TreeNode implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Integer type;
	private String pid;
	private String title;
	/** 下级节点 */
	private List<TreeNode> children;

	/** 
	 * 构造函数
	 * @param id
	 * @param code
	 * @param pid
	 * @param text 
	 */
	public TreeNode(String id, String code, String pid, String title) {
		this.id = id;
		this.code = code;
		this.pid = pid;
		this.title = title;
	}
	
	public TreeNode() {
		super();
	}
}
