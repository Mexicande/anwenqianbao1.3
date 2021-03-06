package cn.com.stableloan.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2017/6/7.
 */

public class Class_NewBean implements Serializable {
    /**
     * product : [{"pname":"111","product_introduction":"321312312","id":"27","product_logo":"http://or2eh71ll.bkt.clouddn.com/149681810015188.jpg?e=1496821721&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:aAttKXByEGpqeL75exNiqAxTaGg=","labels":[{"id":"3","name":"22","font":"#ffff","background":"#fff","number":"0","status":"0","created_at":"2017-06-06 16:02:05","updated_at":"2017-06-06 16:02:05"}]},{"pname":"林志玲","product_introduction":"喏翻墙翻墙缫䗄启发启发 ","id":"28","product_logo":"http://or2eh71ll.bkt.clouddn.com/149681756348774.jpg?e=1496821167&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:pr9qT9M5O3bRT9zFBw_bq48Ss9E=","labels":[]}]
     * class : [{"home_image":"http://or2eh71ll.bkt.clouddn.com/149673705917725.jpg?e=1496740659&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:v9FZhjj2lo_-uhjYv865ZxYzBww=","name":"11","id":"1"},{"home_image":"http://or2eh71ll.bkt.clouddn.com/149673705917725.jpg?e=1496740659&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:v9FZhjj2lo_-uhjYv865ZxYzBww=","name":"11","id":"1"},{"home_image":"http://or2eh71ll.bkt.clouddn.com/149681166010964.jpg?e=1496815260&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:r6k9d2LNDnaj5zacp98BLfFDSPw=","name":"陈冠希","id":"2"},{"home_image":"http://or2eh71ll.bkt.clouddn.com/149673705917725.jpg?e=1496740659&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:v9FZhjj2lo_-uhjYv865ZxYzBww=","name":"11","id":"1"},{"home_image":"http://or2eh71ll.bkt.clouddn.com/149673705917725.jpg?e=1496740659&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:v9FZhjj2lo_-uhjYv865ZxYzBww=","name":"11","id":"1"}]
     * isSuccess : true
     */
    private boolean isSuccess;
    private List<ProductBean> product;
    @SerializedName("class")
    private List<ClassBean> classX;

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public List<ClassBean> getClassX() {
        return classX;
    }

    public void setClassX(List<ClassBean> classX) {
        this.classX = classX;
    }

    public static class ProductBean implements Serializable{
        /**
         * pname : 111
         * product_introduction : 321312312
         * id : 27
         * product_logo : http://or2eh71ll.bkt.clouddn.com/149681810015188.jpg?e=1496821721&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:aAttKXByEGpqeL75exNiqAxTaGg=
         * labels : [{"id":"3","name":"22","font":"#ffff","background":"#fff","number":"0","status":"0","created_at":"2017-06-06 16:02:05","updated_at":"2017-06-06 16:02:05"}]
         */

        private String pname;
        private String product_introduction;
        private String id;
        private String product_logo;
        private List<LabelsBean> labels;

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getProduct_introduction() {
            return product_introduction;
        }

        public void setProduct_introduction(String product_introduction) {
            this.product_introduction = product_introduction;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProduct_logo() {
            return product_logo;
        }

        public void setProduct_logo(String product_logo) {
            this.product_logo = product_logo;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class LabelsBean implements Serializable{
            /**
             * id : 3
             * name : 22
             * font : #ffff
             * background : #fff
             * number : 0
             * status : 0
             * created_at : 2017-06-06 16:02:05
             * updated_at : 2017-06-06 16:02:05
             */

            private String id;
            private String name;
            private String font;
            private String background;
            private String number;
            private String status;
            private String created_at;
            private String updated_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFont() {
                return font;
            }

            public void setFont(String font) {
                this.font = font;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }
    }

    public static class ClassBean implements Serializable{
        /**
         * home_image : http://or2eh71ll.bkt.clouddn.com/149673705917725.jpg?e=1496740659&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:v9FZhjj2lo_-uhjYv865ZxYzBww=
         * name : 11
         * id : 1
         */

        private String home_image;
        private String name;
        private String id;

        public String getHome_image() {
            return home_image;
        }

        public void setHome_image(String home_image) {
            this.home_image = home_image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
