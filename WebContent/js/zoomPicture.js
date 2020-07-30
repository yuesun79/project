class Elements {
        constructor() {
            //缩略图区域
            this.sourceImage = $(".little-img");
            //放大区域
            this.aimImage = $(".large-img");
            //视图图片,与图片实际尺寸比例
            this.sourceToAimRate = 0.01;
            //原图高度
            this.sourceHeight = 0;
            //原图宽度
            this.sourceWidth = 0;
            //视图高度,div的高度,本例是300px
            this.containerHeight = this.sourceImage.children().height();
            this.containerWidth = this.sourceImage.children().width();
            //鼠标在缩略图上的坐标 offsetX
            this.cursor_x = 0;
            this.cursor_y = 0;
            //标志被放大的区域
            this.region = $(".relative-region");

            this.mouseMove = this.mouseMove.bind(this);
            this.regionPosition = this.regionPosition.bind(this);
            this.regionMove = this.regionMove.bind(this);
            this.caculatorRate = this.caculatorRate.bind(this);
        }
        //计算原图尺寸,思路是内存加载原图,获得尺寸,并计算容器视图和原图的比例
        caculatorRate() {
            console.log(this.sourceImage.children().attr("src"));
            $("<img/>").attr("src", this.sourceImage.children().attr("src")).load((e) => {
                //let sourceImageWidth=e.target.width;
                this.sourceWidth = e.target.width;
                this.sourceHeight = e.target.height;
                //计算图片和容器的像素比例
                this.sourceToAimRate = this.sourceWidth / this.containerWidth;
            });
        }
        //鼠标在缩略图上移动时计算,放大图的背景位置,并且定位标识被放大的区域
        mouseMove(e) {
            //console.log(`x:${e.offsetX},y:${e.offsetY}`);
            //偏离region的位置
            //由于鼠标实际上是在标识被放大区域(网格区域)的div里面,所以通过e获取的实际上是缩略图内,网格标识的offsetX 要用网格区域的offsetX+offsetLeft-缩略图的offsetleft才是鼠标对应到缩略图上的位置
            let r_x = e.offsetX;
            let r_y = e.offsetY;

            let s_t = this.sourceImage.offset().top;
            let s_l = this.sourceImage.offset().left;

            let r_t = this.region.offset().top;
            let r_l = this.region.offset().left;

            let x = r_l - s_l + r_x;
            let y = r_t - s_t + r_y;

            //在原图上显示,被放大的区域
            let w = this.region.width();
            let h = this.region.height();

             //由于鼠标在网格区域的中心,所以在计算放大图的top和left的时候,实际是从网格的左上角位置
            this.cursor_x = (x - w / 2) * this.sourceToAimRate;
            this.cursor_y = (y - h / 2) * this.sourceToAimRate;
            if (this.cursor_x + this.containerWidth > this.sourceWidth) {
                this.cursor_x = this.sourceWidth - this.containerWidth;
            }
            if (this.cursor_y + this.containerHeight > this.sourceHeight) {
                this.cursor_y = this.sourceHeight - this.containerHeight;
            }
            if (this.cursor_y < 0) {
                this.cursor_y = 0;
            }
            if (this.cursor_x < 0) {
                this.cursor_x = 0;
            }
            this.aimImage.css({
                "background-position-x": -this.cursor_x + "px",
                "background-position-y": -this.cursor_y + "px"
            }); 
            this.regionMove(w, h, x, y); 
}
        regionPosition(r_w, r_h, e) {
            let left = e.offsetX - r_w / 2;
            let top = e.offsetY - r_h / 2; 
            if (left < 0) {
                left = 0;
            }
            if (left + r_w > this.containerWidth) {
                left = this.containerWidth - r_w;
            }
            if (top < 0) {
                top = 0;
            }
            if (top + r_h > this.containerHeight) {
                top = this.containerHeight - r_h;
            }
            this.region.css({
                "top": (top - this.containerHeight) + "px",
                "left": left+ "px",
                "cursor": "crosshair"
            });
        } 
        regionMove(r_w, r_h, x, y) {
            let left = x - r_w / 2;
            let top = y - r_h / 2;

            if (left < 0) {
                left = 0;
            }
            if (left + r_w > this.containerWidth) {
                left = this.containerWidth - r_w;
            }
            if (top < 0) {
                top = 0;
            }
            if (top + r_h > this.containerHeight) {
                top = this.containerHeight - r_h;
            }
            this.region.css({"top": (top - this.containerHeight) + "px", "left": left + "px"});
        }

        init() {
            this.caculatorRate();
            //鼠标移入缩略图区域,由缩略图区域的hover事件初始化,将鼠标放入网格区域的中心
            this.sourceImage.children().mouseover((e) => {
                this.aimImage.css({"display": "block", "opacity": "1"});
                let r_w = this.containerWidth / this.sourceToAimRate;
                let r_h = this.containerHeight / this.sourceToAimRate;
                let x = e.offsetX;
                let y = e.offsetY;
                this.regionPosition(r_w, r_h, e);
                this.region.css({"display": "block", "height": r_h + "px", "width": r_w + "px"});
            });
            //修复鼠标在region上,右侧放大区域闪动
            this.region.mousemove(this.mouseMove);
            this.region.mouseleave(() => {
                this.aimImage.css({"display": "none", "opacity": "0"});
                this.region.css({"display": "none"});
            });
        }
    } 
    $(function () {
        var e = new Elements();
        e.init();
    })
