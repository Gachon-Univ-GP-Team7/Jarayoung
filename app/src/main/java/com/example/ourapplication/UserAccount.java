package com.example.ourapplication;
//사용자 계정 정보 모델 클래스
public class UserAccount {
    private String idToken; //firebase Uid(고유 토큰 정보)
    private String emailId;
    private String password;
    //추가적 정보 더 넣어도 됨.

    //(중요)firebase에서는 빈 생성자를 만들어줘야 데이터베이스를 할 때 오류가 안남.
    public UserAccount() {
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
