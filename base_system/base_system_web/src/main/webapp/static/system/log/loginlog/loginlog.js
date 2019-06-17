layui.use(['table', 'laydate'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var nowTime = new Date().valueOf();
    var renderTable = function () {
        layer.load(3);
        table.render({
            elem: '#loginLogTable',
            even: true,//开启隔行背景
            url: ctx + '/log/login/list',
            limit: 10,
            limits: [10, 30, 50, 100],
            toolbar: '#toolbarDemo',
            page: true,//开启分页
            id: 'loginLogTablereload',
            cols: [[ //表头
                {type: 'checkbox'},
                {
                    field: 'cuserLoginName',
                    title: '用户名称',
                    align: 'center'
                }, {
                    field: 'chostIp',
                    title: '登陆IP',
                    align: 'center'
                }, {
                    field: 'dloginTime',
                    title: '登陆时间',
                    align: 'center',
                    sort: true,
                    templet: function (d) {
                        if (d.dloginTime != null) {
                            return formatterTime(d.dloginTime);
                        } else {
                            return "";
                        }

                    }
                }, {
                    field: 'cresult',
                    title: '结果',
                    align: 'center',
                    templet: function (d) {
                        if (d.cresult == "00") {
                            return "成功";
                        } else {
                            return "失败";
                        }
                    }
                }]],
            done: function () {
                layer.closeAll('loading');
            }
        });

    };
    renderTable();
    //监听工具条
    table.on('toolbar(loginLogFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'delLoginLog':
                var data = checkStatus.data;
                if (data == null || data == '' || data == undefined) {
                    layer.alert('请选择日志记录', function (index) {
                        layer.close(index);
                        renderTable();
                        return;
                    });
                } else {
                    var logId='';
                    $.each(data,function (index,param) {
                        logId+=param.id+',';
                    })
                    console.log(logId);
                    $.ajax({
                        url: ctx + '/log/login/delete',
                        type: 'post',
                        data: {"ids": logId},
                        dataType: "json",
                        success: function (res) {
                            console.log(res)
                            if (res.success) {
                                layer.msg("操作成功", {icon: 1,time: 2000});
                                renderTable();
                            }
                            else {
                                layer.msg("操作失败", {icon: 2,time: 2000});
                                renderTable();
                            }
                        }
                    });
                }
                break;
        }
    });
    var start = laydate.render({
        elem: '#startTime',
        type: 'datetime',
        max: nowTime,
        btns: ['clear', 'confirm'],
        done: function (value, date) {
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month - 1;
        }
    });
    var end = laydate.render({
        elem: '#endTime',
        type: 'datetime',
        max: nowTime,
        done: function (value, date) {
            if ($.trim(value) == '') {
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth() + 1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month - 1;
        }
    });

    //刷新
    $('#btn-refresh').click(function () {
        renderTable();
    });

    var $ = layui.$, active = {
        reload: function () {
            var cname = $('#cname').val();
            var startTime = $('#startTime').val();
            var endTime = $('#endTime').val();
            table.reload('loginLogTablereload', {
                where: {
                    cname: cname,
                    startTime: startTime,
                    endTime: endTime
                }
            });
        }
    };
    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

})
;