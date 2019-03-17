package com.zl;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-01-17 15:11
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class JDBCTest {

    public static Connection getCon(){
        String URL = "jdbc:mysql://10.8.5.100:3306/acmtc?useSSL=false&amp;zeroDateTimeBehavior=convertToNull&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true";
        String USER = "root";
        String PASSWORD = "root";
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获得数据库链接
        try {
          return  DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Statement st = getCon().createStatement();
        String sql= "SELECT imu.id AS 主键,imu.state AS 状态 , imu.user_account AS 用户名称 FROM instation_message_user imu WHERE 1=1 LIMIT 2";
        ResultSet rs = st.executeQuery(sql);
        //4.处理数据库的返回结果(使用ResultSet类)
        ResultSetMetaData resultsetmetadata = rs.getMetaData();
        //获取查询结果列数
        int colums = resultsetmetadata.getColumnCount();
        for (int i = 1; i <= colums; i++) {
            //获取列别名
            System.out.println(resultsetmetadata.getColumnLabel(i));
        }
        while (rs.next()) {
            for (int i = 1; i <= colums; i++) {
                System.out.println(rs.getString(i));
            }
        }

        //关闭资源
        rs.close();
        st.close();
        getCon().close();
    }

    private  List<Map<String, Object>> queryList(int pageIndex, int pageSize, boolean attachTableName, String sql, Object... values) throws SQLException{
        Connection conn = null;
        PreparedStatement pStmt = null;
        List<Map<String, Object>> dataList = null;
        //校验参数
        if(pageIndex <= 0){
            pageIndex = 1;
        }
        if(pageSize <= 0){
            pageSize = 10;
        }
        conn = getCon();
        pStmt = conn.prepareStatement(sql);
        //设置参数
        if(pStmt != null && values != null && values.length > 0){
            for (int i = 0; i < values.length; i++) {
                pStmt.setObject(i+1, values[i]);
            }
        }
        //设置最大查询到第几条记录
        pStmt.setMaxRows(pageIndex*pageSize);
        ResultSet rs = pStmt.executeQuery();
        //游标移动到要输出的第一条记录
        rs.relative((pageIndex-1)*pageSize);
        if(rs != null){
            try {
                dataList = new ArrayList<Map<String,Object>>();
                ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
                //遍历结果集
                while(rs.next()){
                    Map<String, Object> map = new LinkedHashMap();
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        //map.put(attachTableName?(formatTableName(md.getTableName(i))+"."+md.getColumnLabel(i)):(md.getColumnLabel(i)), rs.getObject(i));
                    }
                    dataList.add(map);
                }
            }finally{
                if(rs != null){
                    rs.close();
                }
                if(pStmt != null){
                    pStmt.close();
                }
                if (conn != null) {
                    //this.closeConnection(conn);
                }
            }
        }
        return dataList;
    }

}
