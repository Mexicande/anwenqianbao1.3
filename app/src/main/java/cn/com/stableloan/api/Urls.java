package cn.com.stableloan.api;

/**
 * Created by apple on 2017/5/20.
 */

public interface Urls{

    String STATUS_WordLogin                 =           "1";
    String STATUS_MessageLogin              =           "3";


    String CardBack="http://www.shoujiweidai.com/Card/index.html";
    String puk_URL="http://test.api.shoujijiekuan.com/Home/ApiLogin/";

    String NEW_URL="http://test.api.shoujijiekuan.com/Home/";
    String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUsxpie3g+dTnPv6vjRuCOVB6h" +
            "c0V6qFXAbzjSEs1HZHCo/JRgUBHK+fCyc+LKUhcsYe00aTnzDRNuW0MAhG1lXIu+" +
            "Jo0En3yiFB4a4jcMUThVx1s7p/Cmqk+KUAmM9FLBrT6UoKzbLNKi99Yh6ybSMJuy" +
            "X4HrXK/lU7Q44b4fMwIDAQAB";

    String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANSzGmJ7eD51Oc+/" +
            "q+NG4I5UHqFzRXqoVcBvONISzUdkcKj8lGBQEcr58LJz4spSFyxh7TRpOfMNE25b" +
            "QwCEbWVci74mjQSffKIUHhriNwxROFXHWzun8KaqT4pQCYz0UsGtPpSgrNss0qL3" +
            "1iHrJtIwm7Jfgetcr+VTtDjhvh8zAgMBAAECgYEAs0fjzW7VA5A7kmi0sXVkgZNV" +
            "3jATODf7T6Bv/GHstWhrrYR4bFYRKU1THJehaXeYIMjJ74tiVQOIhVRXPXBh46ve" +
            "/jUBt/WUUB8Cc5OkX/ll293ZuUpedUlRi6jYNiXU7yjAfTIqFLMwKrpXjMnIMSwV" +
            "NebPk9qNs9VT3gENGgECQQD4e5Ozmmj2fBW/4aKA+LtObz/Hu75XaA5Kd9WV+30r" +
            "SGxxTh7QsHXl17xVXhMYrHcdJ9YhOWk69+sKQRlKLZWhAkEA2yJlmfzRT3J9L+2j" +
            "PzWM0o++hbuiEte2xs3brlvGUAO/Y69UZVzecx0E6bc8rWFakv8j7Ul1vSpJPeJB" +
            "7/kcUwJAPq1VNWGGhl4IUm1Ew0l6Xa98JBJ8UaniqPAPRRS5nvhWukHdTgCkzIQd" +
            "cl8XbArcxNLulVTY8VHlzKFdErPq4QJBAJ4lT8/2/hPpG2G4jcTzX6MibCxVgp04" +
            "oscNEArgXtmmKrzFbxIMGNpYyg/l1tuF0/kcOxBnoJoZZ2xK2q1WSdMCQQCQDV6/" +
            "y0puJ4EGhEa+jwiSi8rSD/vw09CNUNTieVGqx5DzxvPUzTyvRQ9y4dpU8zufqq5p" +
            "BI1hNZWOmpTyld2F";

    //
    interface  times{
        String MESSAGE_SEND="http://47.94.175.112:8081/v1/sms/getcode";
    }
    interface Login{


        //验证码发送
        String SEND_MESSAGE="http://test.api.shoujijiekuan.com/Home/ChuanglanSmsApi/sendSMS";
        String LOGIN="Login";
        String USER_INFOMATION="Mine/Locking";
        String GET_SIGNATURE="Mine/GetSignature";
        String Immunity="Mine/Immunity";



    }
    interface CreditrePort{
        String PRIVATE_KEY  = "MIICXAIBAAKBgQCRMcGMrZIKu956ubKD2kDFzRtSP13ycgRtPVz+MvseTje0YgGl" +
                "ht+zGuPgCD9KgW44FFdwGS2Wp7Ir/zzEpInTPlKWjXGZP06iwlu+7p04ktgK7TB6" +
                "NjF83gHB/T//OrliHrX1l/Jzh3LFHwCAxT6fJt1Ja6eziGvxtLPT94TkcwIDAQAB" +
                "AoGAKsYYugPsUUM1cLxCLfvfNyaMlPdcCu+yBCieu7hzKGNsn7R7vbL1NgOG/FoR" +
                "ozZsLRM5CyovtwFiSPnhgiDjBSnvugLBPLKcQJX3PUb9dHOpfwXZCOg/CMwbb8fg" +
                "9bPt36oBfJYIydQjP3/9iM9t1sdtIbd/fHCL6Ry/iGcsvyECQQDA1ezZ0DlV25QX" +
                "FbL1IvufITnvcfK2KwGc4By71cWZ/tnczxGh4/YiTym7V58IR+XW326UwPdW4Mi0" +
                "xtuJagmxAkEAwMDo8VzvysfxDs0hdwxFjksZ0htPEG9FLjhp2Vu5jm+swyPBt+eW" +
                "pFvstM6YIq570y6D3Mkq2zu20F5nJnm1YwJAdxur0F3tDDs0nY2pnACfqwq63ktj" +
                "v2GQ/XTwSpUgGJ5xsxGzsms7/LUo8a6NbG/8Z1xa0Ubff6oYTpEFyTrWAQJBAJPa" +
                "phSWpH2Y1yjyYswtxqD6rKjFN+W0ZI2qyk7nlDNVKGFbaTpHU/9pX+3lVz+rNeJt" +
                "GMrgKJaYfIfjEh6qV18CQAOSaCWwd26ZE5zed045dz01Yf9zPvxYXeFm4cqK584M" +
                "Z6J84qIslgp9fhOrLv/Q4mos1CZTWzDhFbDM73NlabQ=";
        String APP_ID=                                                                   "1000053";
        String APP_NAME=                                                                 "anwenqianbao";
    }

    interface  Identity{
        String GetIdentity="Identity/GetIdentity";
        String Getbank="Identity/Getbank ";
        String GetOccupation="Identity/GetOccupation";

        String AddIdentity="Identity/AddIdentity";

        String Addbank="Identity/Addbank";
        String AddOccupation="Identity/AddOccupation";
    }
    interface STATUS{
        String GetStatus="Status/GetStatus";

        String GetPictrueStatus="Status/GetPictrueStatus";
        String Getsetting="SaveSetting/Getsetting";
        String Save_Setting="SaveSetting/setting";


    }

    interface  HOME_FRAGMENT{
        String BANNER_HOT="GetBanner";
        String PRODUCT_LIST="GetProducts";
    }
    interface register{

        String REGSTER  = "Registered";

        String FORGETWORD="ForgetPassword";
    }

    interface product{
        //产品详情
        String Productdetail="GetProductDetail";
        String GetSlotdetail ="GetSlotdetail";
        String ClassProduct="ClassificationList";
        String ProductList="ProductList";
        String ProDuctScreening="Screening";

    }

    interface update{
        String UPDATE_NICK="Modify";
        String UPDATE_Word="Modify";
        String UPDATE_PROFRSSION="Modify";
    }
    interface user{
        String USERT_INFO="Personal";
        String USER_STATUS="Status/GetStatus";

    }
    interface  notice{
        String Announcement="Announcement";
    }

    interface  LOTTERY{
        String GetLottery="http://47.94.175.112:8081/v1/welfare/getwelfare";
    }

    interface Pictrue{
        String GET_QINIUTOKEN="Mine/GetToken";

        String UpLoadImage="Pictrue/AddIdcard";

        String Get_Pictrue="Pictrue/GetPictrue";
    }
}
