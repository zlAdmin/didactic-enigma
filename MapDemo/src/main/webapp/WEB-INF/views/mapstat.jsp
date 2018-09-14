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
    <title>高德地图api使用</title>
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
        .sel-btn{
            width: 60px;
            line-height: 30px;
            height: 22px;
            border: 1px solid #ccc;
            vertical-align: middle;
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

    <script type="text/javascript"  src="http://webapi.amap.com/maps?v=1.4.9&key=eaf8a3e26b15c81bb8f53a57be826e53"></script>
</head>
<body>
<h1>热力图统计</h1>
<h3>刷新时间
    <select class="sel-btn">
        <option>30秒</option>
        <option>60秒</option>
        <option>90秒</option>
    </select>
</h3>
<div id="container"></div>
<script type="text/javascript">
    var map = new AMap.Map("container", {
        resizeEnable: true,
        center: [116.418261, 39.921984],
        zoom: 11
    });
    // map.setFeatures('road','building');

    // 热力图
    var heatmap;
    var points =[
        {"lng":116.191031,"lat":39.988585,"count":1000},
        {"lng":116.389275,"lat":39.925818,"count":11},
        {"lng":116.287444,"lat":39.810742,"count":120},
        {"lng":116.481707,"lat":39.940089,"count":13},
        {"lng":116.410588,"lat":39.880172,"count":14},
        {"lng":116.394816,"lat":39.91181,"count":15},
        {"lng":116.416002,"lat":39.952917,"count":16}
    ];
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
</script>
</body>
</html>
