package com.panjw.msgVo;

/**
 * @author panjw
 * @date 2021/6/5 20:30
 */
public class UserVo {
    private String userName;

    private Long age;

    private String timeMillions;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getTimeMillions() {
        return timeMillions;
    }

    public UserVo setTimeMillions(String timeMillions) {
        this.timeMillions = timeMillions;
        return this;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", timeMillions='" + timeMillions + '\'' +
                '}';
    }
}
