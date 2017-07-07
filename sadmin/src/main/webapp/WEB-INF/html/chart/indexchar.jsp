<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta charset="utf-8">
<script src="<%=basePath %>js/echarts.min.js"></script>

<div id="chart" style="width: 95% ;height:500px;"></div>
<div id="chart2" style="width: 80% ;height:400px;"></div>
<script src="<%=basePath %>js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/util.js" type="text/javascript"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('chart'));
    var myChart2 = echarts.init(document.getElementById('chart2'));
    var result = new Map();
    <c:forEach items="${dto}" var="info" varStatus="i">
        var map = new Map();
        <c:forEach items="${info.models}" var="data" varStatus="j">
            map.put("${data.createdate}","${data.total}");
        </c:forEach>
        result.put("${info.cid}",map);
    </c:forEach>
    var datainfo = new Array();
    var datelist = tab(new Date().Format("yyyy-MM-dd"), 10);
    for(var i=0;i<result.keys.length;i++){
        var map = result.get(result.keys[i]);
        var dataserie = new serie();
        dataserie.name = result.keys[i];
        dataserie.type = "bar";
        for(var j = 0; j <datelist.length; j++){
            if(map.data[datelist[j]] == null){
                dataserie.data.push(0);
            }
            else{
                dataserie.data.push(map.get(datelist[j]));
            }
        }
        datainfo.push(dataserie);
    }
    // 指定图表的配置项和数据
    option = {
        title: {
            text: '牧场发货数据统计表',
            subtext: '汇总',
            x: 'center'
        },
        dataZoom: [
            {
                id: 'dataZoomX',
                type: 'slider',
                xAxisIndex: [0],
                filterMode: 'filter'
            },
            {
                id: 'dataZoomY',
                type: 'slider',
                yAxisIndex: [0],
                filterMode: 'none' // 不改变数据，只修改数据轴
            }
        ],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: result.keys
        },
        xAxis: [
            {
                type: 'category',
                data: datelist,
                axisPointer: {
                    type: 'shadow'
                },
                splitLine: {
                    show: true
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '发货量',
                axisLabel: {
                    formatter: '{value} 吨'
                }
            }
        ],
        series: datainfo
    };
    option2 = {
        title: {
            text: '某站点用户访问来源',
            subtext: '纯属虚构',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart2.setOption(option2);
</script>