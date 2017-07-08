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
    map.put("${data.createdate}", "${data.total}");
    </c:forEach>
    result.put("${info.customerModel.name}", map);
    </c:forEach>
    var datainfo = new Array();
    var datelist = tab(new Date().Format("yyyy-MM-dd"), 365);
    //生成总计的图表对象
    var dataserieline = new serie();
    dataserieline.name = "总计";
    dataserieline.type = "line";
    for (var i = 0; i < result.keys.length; i++) {
        var map = result.get(result.keys[i]);
        var dataserie = new serie();
        dataserie.name = result.keys[i];
        dataserie.type = "bar";
        for (var j = 0; j < datelist.length; j++) {
            if (map.data[datelist[j]] == null) {
                dataserie.data.push(0);
            }
            else {
                dataserie.data.push(parseInt(map.get(datelist[j])));
            }
        }
        datainfo.push(dataserie);
    }
    for (var i = 0; i < datelist.length; i++) {
        var number = 0;
        for (var j = 0; j < datainfo.length; j++) {
            number += datainfo[j].data[i]
        }
        dataserieline.data.push(number);
    }
    var namelist = new Array();
    var datalist = new Array();
    for (var i = 0; i < datainfo.length; i++) {
        var dataserie = datainfo[i];
        namelist.push(dataserie.name);
        var itemdatainfo = {};
        itemdatainfo.name = dataserie.name;
        var number = 0;
        for (var j = 0; j < dataserie.data.length; j++) {
            number += dataserie.data[j];
        }
        itemdatainfo.value = number;
        datalist.push(itemdatainfo);
    }
    option2 = {
        title: {
            text: '各牧场发货量占比',
            subtext: '统计',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}吨 ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: namelist
        },
        series: [
            {
                name: '发货牧场',
                type: 'pie',
                radius: '55%',
                center: ['55%', '60%'],
                data: datalist,
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
    result.put("总计", "");
    datainfo.push(dataserieline);
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
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    myChart2.setOption(option2);
</script>