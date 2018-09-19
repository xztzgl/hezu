package com.tangquan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: wangfeng
 * Date: 17/12/11
 * Time: 下午5:22
 */
@Table(name="tq_session_manage")
public class SessionManage {

    @Id
    @Column(name = "uid", nullable = false)
    private int uid;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
