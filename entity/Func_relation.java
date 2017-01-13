import java.math.BigInteger;
import java.sql.Timestamp;

public class Func_relation {
    private Timestamp createtime;
    private String superCls;
    private String project;
    private BigInteger IDX;
    private String callMtd;
    private Timestamp updatetime;
    private String interfaces;
    private String coder;
    private String method;
    private String callCls;
    private String clsName;
    private String jarName;

    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getSuperCls() {
        return this.superCls;
    }

    public void setSuperCls(String superCls) {
        this.superCls = superCls;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public BigInteger getIDX() {
        return this.IDX;
    }

    public void setIDX(BigInteger IDX) {
        this.IDX = IDX;
    }

    public String getCallMtd() {
        return this.callMtd;
    }

    public void setCallMtd(String callMtd) {
        this.callMtd = callMtd;
    }

    public Timestamp getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public String getInterfaces() {
        return this.interfaces;
    }

    public void setInterfaces(String interfaces) {
        this.interfaces = interfaces;
    }

    public String getCoder() {
        return this.coder;
    }

    public void setCoder(String coder) {
        this.coder = coder;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCallCls() {
        return this.callCls;
    }

    public void setCallCls(String callCls) {
        this.callCls = callCls;
    }

    public String getClsName() {
        return this.clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getJarName() {
        return this.jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }
}
