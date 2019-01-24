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
    private String name;  //空间资源名称
    @Column(name = "area")
    private String area; //面积（平方公里）
/*@Column(name = "companyid")
    private String companyID;*/

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;  //所属地市列表


    @Transient
    private  Integer cityId;  //所属地市ID
    @Transient
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    @Transient
    public Integer getCityId() {
        return cityId;
    }

    @Column(name = "code")
    private String code;    //行政区划编码
    @Column(name = "address")
    private String address;   //政府地址
    @Column(name = "charge")
    private String charge;   //负责人
    @Column(name = "tel")
    private String tel;     //联系方式
    @Column(name = "devnum")
    private Integer devNum;  //设备数量


    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
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
