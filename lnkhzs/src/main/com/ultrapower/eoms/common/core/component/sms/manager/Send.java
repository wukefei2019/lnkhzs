package com.ultrapower.eoms.common.core.component.sms.manager;

import com.ultrapower.eoms.RecordLog;
import com.ultrapower.eoms.common.core.component.sms.model.Smsmonitor;
import com.ultrapower.eoms.common.core.util.StringUtils;
import java.util.List;

public class Send extends Thread
{
  private int maxSendDegree = 3;//最大发送次数(如果调用网关失败,最大再发送n-1次)

  SendSmsForSqlServer sendsm = new SendSmsForSqlServer();
  private ScanSmsmonitor scanSmsmonitor = new ScanSmsmonitor();

  public void run()
  {
    sendSm();
  }

  public void sendSm()
  {
    List smList = this.scanSmsmonitor.getWaitingSendSm();
    int smListLen = 0;
    if (smList != null)
      smListLen = smList.size();
    RecordLog.printLog("扫描短信监控表,获取到待发的短信条数:" + smListLen, 3);
    for (int i = 0; i < smListLen; i++) {
      String serialNo = getGUID(System.currentTimeMillis() + i);
      Smsmonitor smsmonitor = (Smsmonitor)smList.get(i);
      if ((smsmonitor.getGatewaycode() != null) && (!smsmonitor.getGatewaycode().equals("")))
      {
        serialNo = smsmonitor.getGatewaycode();
      }
      String result = this.sendsm.SendSm(serialNo, StringUtils.checkNullString(smsmonitor.getGoal()), StringUtils.checkNullString(smsmonitor.getContent()));
      if ("true".equals(result)) {
        RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",手机号码：" + smsmonitor.getGoal() + ",发送成功！", 3);
        boolean updateflag = this.scanSmsmonitor.updateSm(StringUtils.checkNullString(smsmonitor.getPid()), 1, serialNo);
        if (!updateflag)
          RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",更新短信监控表状态---失败！", 1);
      } else {
        long firstsendtime = System.currentTimeMillis() / 1000L;
        for (int d = 1; d < this.maxSendDegree; d++) {
          RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",尝试进行第" + (d + 1) + "发送", 1);
          String resultd = this.sendsm.SendSm(serialNo, StringUtils.checkNullString(smsmonitor.getGoal()), StringUtils.checkNullString(smsmonitor.getContent()));
          if ("true".equals(resultd)) {
            RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",手机号码：" + smsmonitor.getGoal() + ",尝试进行第" + (d + 1) + "发送成功！", 1);
            boolean updateflag = this.scanSmsmonitor.updateSm(StringUtils.checkNullString(smsmonitor.getPid()), 1, d + 1, firstsendtime, "", "", serialNo);
            if (updateflag) break;
            RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",更新短信监控表状态---失败！", 1);
            break;
          }
          if (d + 1 == this.maxSendDegree) {
            RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",手机号码：" + smsmonitor.getGoal() + ",尝试进行第" + (d + 1) + "发送失败！", 1);
            boolean updateflag = this.scanSmsmonitor.updateSm(StringUtils.checkNullString(smsmonitor.getPid()), 2, d + 1, firstsendtime, "", "", serialNo);
            if (!updateflag)
              RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",更新短信监控表状态---失败！", 1);
          } else {
            RecordLog.printLog("短信:" + StringUtils.checkNullString(smsmonitor.getPid()) + ",尝试进行第" + (d + 1) + "发送失败！", 1);
          }
        }
      }
    }
  }

  public String getGUID(long nowtime)
  {
    return String.valueOf(nowtime).substring(3, String.valueOf(nowtime).length());
  }
}