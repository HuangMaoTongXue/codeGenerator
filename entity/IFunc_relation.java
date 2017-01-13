import java.math.BigInteger;
import java.sql.Timestamp;

public interface IFunc_relation {
    public Timestamp getCreatetime();

    public void setCreatetime(Timestamp createtime);

    public String getSuperCls();

    public void setSuperCls(String superCls);

    public String getProject();

    public void setProject(String project);

    public BigInteger getIDX();

    public void setIDX(BigInteger IDX);

    public String getCallMtd();

    public void setCallMtd(String callMtd);

    public Timestamp getUpdatetime();

    public void setUpdatetime(Timestamp updatetime);

    public String getInterfaces();

    public void setInterfaces(String interfaces);

    public String getCoder();

    public void setCoder(String coder);

    public String getMethod();

    public void setMethod(String method);

    public String getCallCls();

    public void setCallCls(String callCls);

    public String getClsName();

    public void setClsName(String clsName);

    public String getJarName();

    public void setJarName(String jarName);
}
