package com.platform.vo;

public class StudentVo {

      private String number;//卡号
      private int remindDays; //剩余天数
      private int remindTimes; //剩余次数
      private String schoolId;//学校ID
      private String beginDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRemindDays() {
        return remindDays;
    }

    public void setRemindDays(int remindDays) {
        this.remindDays = remindDays;
    }

    public int getRemindTimes() {
        return remindTimes;
    }

    public void setRemindTimes(int remindTimes) {
        this.remindTimes = remindTimes;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
