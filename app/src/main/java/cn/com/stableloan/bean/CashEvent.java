package cn.com.stableloan.bean;

import java.io.Serializable;

import cn.com.stableloan.model.integarl.CashBean;

/**
 * Created by apple on 2017/7/3.
 */

public class CashEvent implements Serializable{
    public final String cash;

    public CashEvent(String cash) {
        this.cash = cash;
    }

}
