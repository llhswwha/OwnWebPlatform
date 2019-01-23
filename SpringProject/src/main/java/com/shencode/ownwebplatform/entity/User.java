package com.shencode.ownwebplatform.entity;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="t_user")
public class User implements BaseEntity<String> {

    @Id
    // @GeneratedValue(strategy = GenerationType.) int 自增
    @Column(name = "loginName")
    private String loginName;
    @Column(name = "name")
    private String name;
    @Column(name = "pw")
    private String pw;
    @Column(name = "sex")
    private String sex;
    @Column(name = "birthday")
    private String birthday;
/*    @Column(name="company")
    private String company;*/

    @Transient
    private  Integer companyid;
    @Transient
    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }
    @Transient
    public Integer getCompanyid() {
        return companyid;
    }

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @Column(name = "dep")
    private String dep;
    @Column(name = "cpzj")
    private String cpzj;
    @Column(name = "homeZJ")
    private String homeZJ;
    @Column(name = "cpTel")
    private String cpTel;
    @Column(name = "tel")
    private String tel;
    @Column(name = "cpEmail")
    private String cpEmail;
    @Column(name = "email")
    private String email;
    @Column(name = "represent")
    private String represent;

    public void setCpzj(String cpzj) {
        this.cpzj = cpzj;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getCpzj() {
        return cpzj;
    }

    public String getRepresent() {
        return represent;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    @Column(name = "validSityData")
    private String validSityData;
    @Column(name = "state")
    private int state;

    @ManyToMany
    @JoinTable(name = "t_userrole",joinColumns = {@JoinColumn(name = "loginName")},inverseJoinColumns = {@JoinColumn(name = "roleid")})
    private Set<Role> roleSet;
    @Transient
    private List<Integer> roleIdList;
    @Transient
    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
    @Transient
    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    //private List<Integer> roleList;
    //roleList:[1,2,3]}

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setCpZJ(String cpZJ) {
        this.cpzj = cpZJ;
    }

    public void setHomeZJ(String homeZJ) {
        this.homeZJ = homeZJ;
    }

    public void setCpTel(String cpTel) {
        this.cpTel = cpTel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCpEmail(String cpEmail) {
        this.cpEmail = cpEmail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescribe(String represent) {
        this.represent = represent;
    }

    public void setValidSityData(String validSityData) {
        this.validSityData = validSityData;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDep() {
        return dep;
    }

    public String getCpZJ() {
        return cpzj;
    }

    public String getHomeZJ() {
        return homeZJ;
    }

    public String getCpTel() {
        return cpTel;
    }

    public String getTel() {
        return tel;
    }

    public String getCpEmail() {
        return cpEmail;
    }

    public String getEmail() {
        return email;
    }

    public String getDescribe() {
        return represent;
    }

    public String getValidSityData() {
        return validSityData;
    }

    public int getState() {
        return state;
    }


    @Override
    public String getId() {
        return loginName;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginname='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", companyid=" + companyid +
                ", company=" + company +
                ", dep='" + dep + '\'' +
                ", cpzj='" + cpzj + '\'' +
                ", homeZJ='" + homeZJ + '\'' +
                ", cpTel='" + cpTel + '\'' +
                ", tel='" + tel + '\'' +
                ", cpEmail='" + cpEmail + '\'' +
                ", email='" + email + '\'' +
                ", represent='" + represent + '\'' +
                ", validSityData='" + validSityData + '\'' +
                ", state=" + state +
                ", roleSet=" + roleSet +
                ", roleIdList=" + roleIdList +
                '}';
    }
}
