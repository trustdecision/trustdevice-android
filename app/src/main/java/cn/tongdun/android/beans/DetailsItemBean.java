package cn.tongdun.android.beans;

/**
 * @description: DetailsBean
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class DetailsItemBean {
    private String name;
    private String value;

    public DetailsItemBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
