package local.zcw.doman;

/**
 * Created by zcw on 2017/02/24.
 */
public class Address {

    private String province;

    private String city;

    private String area;

    public Address(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
