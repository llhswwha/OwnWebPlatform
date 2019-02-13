package com.shencode.ownwebplatform.entity;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="t_user")
public class User extends BaseEntity {

    //@Id
    // @GeneratedValue(strategy = GenerationType.) int 自增
    @Column(name = "loginName")
    private String loginName;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "gender")
    private String gender;//性别
    @Column(name = "birthday")
    private String birthday;
/*    @Column(name="city")
    private String city;*/

    @Transient
    private  Integer cityId;
    @Transient
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    @Transient
    public Integer getCityId() {
        return cityId;
    }

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;//公司
    @Column(name = "dep")
    private String dep;//部门
    @Column(name = "companyPhone")
    private String companyPhone;//公司座机号码
    @Column(name = "homePhone")
    private String homePhone;//家里座机号码
    @Column(name = "workPhone")
    private String workPhone;//工作手机号
    @Column(name = "privatePhone")
    private String privatePhone;//私人手机号
    @Column(name = "workEmail",nullable = false)
    private String workEmail;//工作邮箱
    @Column(name = "privateEmail")
    private String privateEmail;//私人邮箱
    @Column(name = "represent")
    private String represent;//???

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getRepresent() {
        return represent;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    @Column(name = "validSityData")
    private String validSityData;
    @Column(name = "state",nullable = false,columnDefinition = " INT  default 1")
    private int state;

    @ManyToMany
    @JoinTable(name = "t_userrole",joinColumns = {@JoinColumn(name = "userId")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
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

    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setCpZJ(String cpZJ) {
        this.companyPhone = cpZJ;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setPrivatePhone(String privatePhone) {
        this.privatePhone = privatePhone;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public void setPrivateEmail(String privateEmail) {
        this.privateEmail = privateEmail;
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

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDep() {
        return dep;
    }

    public String getCpZJ() {
        return companyPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getPrivatePhone() {
        return privatePhone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public String getPrivateEmail() {
        return privateEmail;
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


    /*@Override
    public String getId() {
        return loginName;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "loginname='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", cityId=" + cityId +
                ", city=" + city +
                ", dep='" + dep + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", privatePhone='" + privatePhone + '\'' +
                ", workEmail='" + workEmail + '\'' +
                ", privateEmail='" + privateEmail + '\'' +
                ", represent='" + represent + '\'' +
                ", validSityData='" + validSityData + '\'' +
                ", state=" + state +
                ", roleSet=" + roleSet +
                ", roleIdList=" + roleIdList +
                '}';
    }
}
