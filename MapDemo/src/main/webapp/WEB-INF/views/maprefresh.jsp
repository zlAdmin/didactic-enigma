<%--
  Created by IntelliJ IDEA.
  User: lz
  Date: 2018/8/30
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>show code</title>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
            border: 0;
        }
        select{
            style:none;
            background-color: transparent;
        }
        h1,h3{
            text-align: center;
            line-height: 50px;
        }
        h3{
            line-height: 30px;
        }
        #container{
            position: absolute;
            top: 80px;
            left: 0;
            right: 0;
            bortom: 0;
            width: 100%;
            height: 100%;
            mergin:0 auto;
        }
    </style>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript"  src="http://webapi.amap.com/maps?v=1.4.9&key=eaf8a3e26b15c81bb8f53a57be826e53"></script>
</head>
<body>
<h1>基于高德地图的热力图实时统计</h1>
<div id="container"></div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    //通过ajax长连接的方式动态像前台推送数据
    $(function(){
        // do something
        var points;
        var map = new AMap.Map("container", {
            resizeEnable: true,
            center: [116.418261, 39.921984],
            zoom: 11
        });
        // map.setFeatures('road','building');

        // 热力图
        var heatmap;
        var loopData = function() {
            $.ajax({
                url:'/zl/show/getPosition',
                type:'get',
                dataType:'json',
                success:function(data){
                    console.log(data);
                    points = data;
                    heatmap.setDataSet({data:points,max:100});
                    loopData();
                }
            });
        };
        loopData();
        map.plugin(["AMap.Heatmap"],function() {      //加载热力图插件
            heatmap = new AMap.Heatmap(map,{
                radius:50,
                apacity:[0,0.8]
            });    //在地图对象叠加热力图
            heatmap.setDataSet({data:points,max:100}); //设置热力图数据集
            //具体参数见接口文档
        });

        //测量工具
        map.plugin(["AMap.MouseTool"],function(){
            var mousetool = new AMap.MouseTool(map);
            mousetool.marker(); //使用鼠标工具，在地图上画标记点
        });
        //工具条
        AMap.plugin(['AMap.ToolBar']),function(){
            map.addControl(new AMap.ToolBar());
        }
    });

</script>
</body>
</html>
