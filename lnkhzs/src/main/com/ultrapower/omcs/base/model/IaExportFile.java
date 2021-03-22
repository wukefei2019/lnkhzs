package com.ultrapower.omcs.base.model;

import com.ultrapower.omcs.base.model.IOmcsModel;
import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * Created on 2017年2月18日
 * <p>Title      : 辽宁运维成本管理平台_[子系统统名]_[模块名]</p>
 * <p>Description: [null_实体]</p>
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
@Entity
@Table(name = "IA_EXPORT_FILE")
public class IaExportFile implements IOmcsModel{
    /**
     *null_字段 
     */
    private String pid;

    /**
     *null_字段 
     */
    private String loginname;

    /**
     *null_字段 
     */
    private String path;

    /**
     *null_字段 
     */
    private Long exporttime;

    /**
     *null_字段 
     */
    private String filename;

    /**
     *null_字段 
     */
    private String exportsql;

    /**
     * [null_Get方法]
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "assigned")
    @Column(name = "PID", unique=true, nullable=false, insertable=true, updatable=true, length=50 )
    public String getPid() {
        return pid;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "LOGINNAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=64 )
    public String getLoginname() {
        return loginname;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "PATH", unique=false ,nullable=true, insertable=true, updatable=true ,length=64 )
    public String getPath() {
        return path;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "EXPORTTIME", unique=false ,nullable=true, insertable=true, updatable=true ,length=22 )
    public Long getExporttime() {
        return exporttime;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setExporttime(Long exporttime) {
        this.exporttime = exporttime;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "FILENAME", unique=false ,nullable=true, insertable=true, updatable=true ,length=256 )
    public String getFilename() {
        return filename;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * [null_Get方法]
     */
    @Column(name = "EXPORTSQL", unique=false ,nullable=true, insertable=true, updatable=true ,length=256 )
    public String getExportsql() {
        return exportsql;
    }

    /**
    * [null_Set方法]
    * @param args
    */
    public void setExportsql(String exportsql) {
        this.exportsql = exportsql;
    }

}
