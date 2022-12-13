package cn.tongdun.mobrisk;


/**
 * @description: Option
 * @author: wuzuchang
 * @date: 2022/12/6
 */
public class TDRiskOption {
    private String partnerCode; //td partner
    private TDRiskCallback callback;

    public TDRiskOption() {
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public TDRiskCallback getCallback() {
        return callback;
    }

    public void setCallback(TDRiskCallback callback) {
        this.callback = callback;
    }
}
