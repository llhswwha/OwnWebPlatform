package com.shencode.ownwebplatform.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_spaceres")
public class SpaceRes extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "area")
    private String area;
/*@Column(name = "companyid")
    private String companyID;*/

    @ManyToOne
    @JoinColumn(name = "companyid")
    private Company company;

    @Column(name = "companyname")
    private String companyName;
    @Column(name = "code")
    private String code;
    @Column(name = "address")
    private String address;
    @Column(name = "charge")
    private String charge;
    @Column(name = "tel")
    private String tel;
    @Column(name = "devnum")
    private Integer devNum;


    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    //public void setCompanyID(String companyID) { this.companyID = companyID; }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setDevNum(Integer devNum) {
        this.devNum = devNum;
    }

//    public Integer getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    //public String getCompanyID() { return companyID; }

    public String getCompanyName() {
        return companyName;
    }

    public String getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getCharge() {
        return charge;
    }

    public String getTel() {
        return tel;
    }

    public Integer getDevNum() {
        return devNum;
    }

}
