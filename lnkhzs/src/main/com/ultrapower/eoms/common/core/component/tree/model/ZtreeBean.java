package com.ultrapower.eoms.common.core.component.tree.model;

/**
 * 树节点-节点属性
 * @author zhuzhaohui E-mail:zhuzhaohui@ultrapower.com.cn
 * @version May 17, 2010 1:54:32 PM
 */
public class ZtreeBean {

    private String id;// 1,
    private String pId;// 0,
    private String name;// "展开、折叠 自定义图标不同",
    private boolean open;// true,
    private String iconSkin;// "pIcon01"
    
    private boolean leaf;
    
    private Long count;
    
    private boolean isParent;
    
    private String testid;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

	public String getTestid() {
		return testid;
	}

	public void setTestid(String testid) {
		this.testid = testid;
	}
    
}
