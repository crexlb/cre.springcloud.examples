$(document).ready(function () {
    $('table.stripeable tr:odd').addClass('odd');
    $('table.stripeable tr:even').addClass('even');
    $('#generalInfo tr td').each(function () {
        switch ($(this).text()) {
            case 'total-avail-memory':
                $(this).text('总共可用的内存')
                break;
            case 'environment':
                $(this).text('环境名称')
                break;
            case 'num-of-cpus':
                $(this).text('CPU数量')
                break;
            case 'current-memory-usage':
                $(this).text('当前内存使用率')
                break;
            case 'server-uptime':
                $(this).text('服务在线时间')
                break;
            case 'registered-replicas':
                $(this).text('相邻集群复制节点')
                break;
            case 'unavailable-replicas':
                $(this).text('不可用的集群复制节点')
                break;
            case 'available-replicas':
                $(this).text('可用的相邻集群复制节点')
                break;
        }
    });
    $('#instanceInfo tr td').each(function () {
        switch ($(this).text()) {
            case'ipAddr':
                $(this).text('IP地址')
                break;
            case'status':
                $(this).text('状态信息')
                break;
        }
    });

});